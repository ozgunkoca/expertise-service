package org.sandbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sandbox.model.ExpertiseQuestion;
import org.sandbox.repository.ExpertiseQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExpertiseQuestionServiceImpl implements ExpertiseQuestionService {

    private final ExpertiseQuestionRepository repository;

    @Override
    public List<ExpertiseQuestion> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ExpertiseQuestion> findById(final Long id) {
        return repository.findById(id);
    }
}
