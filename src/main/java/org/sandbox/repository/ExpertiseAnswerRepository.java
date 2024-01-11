package org.sandbox.repository;

import org.sandbox.model.ExpertiseAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertiseAnswerRepository extends JpaRepository<ExpertiseAnswer, Long> {

}
