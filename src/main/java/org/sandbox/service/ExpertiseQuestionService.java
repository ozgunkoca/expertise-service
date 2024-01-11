package org.sandbox.service;

import org.sandbox.model.ExpertiseQuestion;

import java.util.List;
import java.util.Optional;

public interface ExpertiseQuestionService {

    List<ExpertiseQuestion> findAll();

    Optional<ExpertiseQuestion> findById(final Long id);
}
