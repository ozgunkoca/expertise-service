package org.sandbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sandbox.model.ExpertiseAnswer;
import org.sandbox.repository.ExpertiseAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExpertiseAnswerServiceImpl implements ExpertiseAnswerService {

    private final ExpertiseAnswerRepository repository;

}
