package org.sandbox.controller;

import lombok.RequiredArgsConstructor;
import org.sandbox.dto.ExpertiseAnswerDto;
import org.sandbox.dto.ExpertiseDto;
import org.sandbox.model.Expertise;
import org.sandbox.service.ExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expertise")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExpertiseController {

    private final ExpertiseService expertiseService;

    @GetMapping("/{carId}")
    public ExpertiseDto get(@PathVariable("carId") String carId) {
        return expertiseService.get(carId);
    }

    @PostMapping("/save/{carId}")
    public Boolean save(@PathVariable("carId") String carId,
                        @RequestBody List<ExpertiseAnswerDto> answers) {
        return expertiseService.save(carId, answers);
    }


}
