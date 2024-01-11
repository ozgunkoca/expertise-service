package org.sandbox.service;

import org.sandbox.dto.ExpertiseAnswerDto;
import org.sandbox.dto.ExpertiseDto;

import java.util.List;

public interface ExpertiseService {

    ExpertiseDto get(final String carId);

    boolean save(final String carId, final List<ExpertiseAnswerDto> answers);
}
