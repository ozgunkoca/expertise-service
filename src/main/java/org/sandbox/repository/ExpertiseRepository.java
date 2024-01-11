package org.sandbox.repository;

import org.sandbox.model.Expertise;
import org.sandbox.model.ExpertiseAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertiseRepository extends JpaRepository<Expertise, Long> {

    Expertise findTop1ByCarIdOrderByCreatedDateDesc(final String carId);
}
