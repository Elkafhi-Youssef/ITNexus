package com.itnexusglobal.itnexusglobal.skills;

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
@RequestMapping(value = "/api/skillss", produces = MediaType.APPLICATION_JSON_VALUE)
public class SkillsResource {

    private final SkillsService skillsService;

    public SkillsResource(final SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @GetMapping
    public ResponseEntity<List<SkillsDTO>> getAllSkillss() {
        return ResponseEntity.ok(skillsService.findAll());
    }

    @GetMapping("/{skillId}")
    public ResponseEntity<SkillsDTO> getSkills(@PathVariable(name = "skillId") final Long skillId) {
        return ResponseEntity.ok(skillsService.get(skillId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createSkills(@RequestBody @Valid final SkillsDTO skillsDTO) {
        return new ResponseEntity<>(skillsService.create(skillsDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<Void> updateSkills(@PathVariable(name = "skillId") final Long skillId,
            @RequestBody @Valid final SkillsDTO skillsDTO) {
        skillsService.update(skillId, skillsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{skillId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSkills(@PathVariable(name = "skillId") final Long skillId) {
        skillsService.delete(skillId);
        return ResponseEntity.noContent().build();
    }

}
