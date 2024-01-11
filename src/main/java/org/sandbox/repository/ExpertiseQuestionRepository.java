package org.sandbox.repository;

import org.sandbox.model.ExpertiseQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertiseQuestionRepository extends JpaRepository<ExpertiseQuestion, Long> {
}
