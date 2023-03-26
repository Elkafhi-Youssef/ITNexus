package com.itnexusglobal.itnexusglobal.experience;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/experiences", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExperienceResource {

    private final ExperienceService experienceService;

    public ExperienceResource(final ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public ResponseEntity<List<ExperienceDTO>> getAllExperiences() {
        return ResponseEntity.ok(experienceService.findAll());
    }

    @GetMapping("/{experienceId}")
    public ResponseEntity<ExperienceDTO> getExperience(
            @PathVariable(name = "experienceId") final Long experienceId) {
        return ResponseEntity.ok(experienceService.get(experienceId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createExperience(
            @RequestBody @Valid final ExperienceDTO experienceDTO) {
        return new ResponseEntity<>(experienceService.create(experienceDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{experienceId}")
    public ResponseEntity<Void> updateExperience(
            @PathVariable(name = "experienceId") final Long experienceId,
            @RequestBody @Valid final ExperienceDTO experienceDTO) {
        experienceService.update(experienceId, experienceDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{experienceId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteExperience(
            @PathVariable(name = "experienceId") final Long experienceId) {
        experienceService.delete(experienceId);
        return ResponseEntity.noContent().build();
    }

}
