package org.sandbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sandbox.dto.ExpertiseAnswerDto;
import org.sandbox.dto.ExpertiseDto;
import org.sandbox.model.Expertise;
import org.sandbox.model.ExpertiseAnswer;
import org.sandbox.model.ExpertiseAnswerPhoto;
import org.sandbox.model.ExpertiseQuestion;
import org.sandbox.repository.ExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExpertiseServiceImpl implements ExpertiseService {

    private final ExpertiseRepository repository;
    private final ExpertiseQuestionService expertiseQuestionService;

    @Override
    public ExpertiseDto get(final String carId) {
        Expertise expertise = findLatestByCarId(carId);
        if (expertise != null && !CollectionUtils.isEmpty(expertise.getExpertiseAnswers())) {
            return createByExpertise(expertise);
        }
        List<ExpertiseQuestion> questions = expertiseQuestionService.findAll();
        return createByExpertiseQuestions(carId, questions);
    }

    @Override
    @Transactional
    public boolean save(final String carId, final List<ExpertiseAnswerDto> answers) {
        if (StringUtils.isEmpty(carId) || CollectionUtils.isEmpty(answers)) {
            return false;
        }
        Expertise expertise = Expertise.builder()
                .carId(carId)
                .createdDate(new Date())
                .build();

        List<ExpertiseAnswer> expertiseAnswers = new ArrayList<>();
        answers.stream()
                .forEach(answer -> expertiseQuestionService.findById(answer.getQuestionId()).ifPresent(expertiseQuestion -> {
                    ExpertiseAnswer expertiseAnswer = ExpertiseAnswer.builder()
                            .expertise(expertise)
                            .answer(answer.getAnswer())
                            .description(answer.getDescription())
                            .question(expertiseQuestion)
                            .build();
                    if (!CollectionUtils.isEmpty(answer.getPhotos())) {
                        expertiseAnswer.setPhotos(answer.getPhotos().stream()
                                .map(url -> ExpertiseAnswerPhoto.builder()
                                        .expertiseAnswer(expertiseAnswer)
                                        .photoUrl(url)
                                        .build())
                                .collect(Collectors.toList()));
                    }
                    expertiseAnswers.add(expertiseAnswer);
                }));
        expertise.setExpertiseAnswers(expertiseAnswers);
        repository.save(expertise);
        return true;
    }

    private Expertise findLatestByCarId(final String carId) {
        return repository.findTop1ByCarIdOrderByCreatedDateDesc(carId);
    }

    private ExpertiseDto createByExpertiseQuestions(final String carId, final List<ExpertiseQuestion> questions) {
        if (CollectionUtils.isEmpty(questions)) {
            return ExpertiseDto.builder().carId(carId).build();
        }
        List<ExpertiseAnswerDto> expertiseAnswers = questions.stream()
                .map(this::createByQuestion)
                .collect(Collectors.toList());

        return ExpertiseDto.builder()
                .carId(carId)
                .answers(expertiseAnswers)
                .build();
    }

    private ExpertiseDto createByExpertise(final Expertise expertise) {

        List<ExpertiseAnswerDto> expertiseAnswers = expertise.getExpertiseAnswers().stream()
                .map(answer -> ExpertiseAnswerDto.builder()
                        .questionId(answer.getQuestion().getId())
                        .question(answer.getQuestion().getLabel())
                        .answer(answer.getAnswer())
                        .description(answer.getDescription())
                        .photos(!CollectionUtils.isEmpty(answer.getPhotos()) ? answer.getPhotos().stream().map(ExpertiseAnswerPhoto::getPhotoUrl).collect(Collectors.toList()) : null)
                        .build())
                .collect(Collectors.toList());

        List<ExpertiseQuestion> allQuestions = expertiseQuestionService.findAll();
        allQuestions.stream()
                .filter(question -> !expertiseAnswers.stream().anyMatch(answer -> answer.getQuestionId().equals(question.getId())))
                .map(this::createByQuestion)
                .forEach(expertiseAnswers::add);

        return ExpertiseDto.builder()
                .carId(expertise.getCarId())
                .answers(expertiseAnswers)
                .build();
    }

    private ExpertiseAnswerDto createByQuestion(final ExpertiseQuestion question) {
        return ExpertiseAnswerDto.builder()
                .questionId(question.getId())
                .question(question.getLabel())
                .build();
    }
}
