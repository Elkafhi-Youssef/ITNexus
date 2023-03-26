package com.itnexusglobal.itnexusglobal.project;

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
@RequestMapping(value = "/api/projects", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectResource {

    private final ProjectService projectService;

    public ProjectResource(final ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/{projectid}")
    public ResponseEntity<ProjectDTO> getProject(
            @PathVariable(name = "projectid") final Long projectid) {
        return ResponseEntity.ok(projectService.get(projectid));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createProject(@RequestBody @Valid final ProjectDTO projectDTO) {
        return new ResponseEntity<>(projectService.create(projectDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{projectid}")
    public ResponseEntity<Void> updateProject(
            @PathVariable(name = "projectid") final Long projectid,
            @RequestBody @Valid final ProjectDTO projectDTO) {
        projectService.update(projectid, projectDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{projectid}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteProject(
            @PathVariable(name = "projectid") final Long projectid) {
        projectService.delete(projectid);
        return ResponseEntity.noContent().build();
    }

}
