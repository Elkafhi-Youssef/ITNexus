package com.itnexusglobal.itnexusglobal.workoffer;

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
@RequestMapping(value = "/api/workoffers", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkofferResource {

    private final WorkofferService workofferService;

    public WorkofferResource(final WorkofferService workofferService) {
        this.workofferService = workofferService;
    }

    @GetMapping
    public ResponseEntity<List<WorkofferDTO>> getAllWorkoffers() {
        return ResponseEntity.ok(workofferService.findAll());
    }

    @GetMapping("/{workofferId}")
    public ResponseEntity<WorkofferDTO> getWorkoffer(
            @PathVariable(name = "workofferId") final Long workofferId) {
        return ResponseEntity.ok(workofferService.get(workofferId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createWorkoffer(
            @RequestBody @Valid final WorkofferDTO workofferDTO) {
        return new ResponseEntity<>(workofferService.create(workofferDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{workofferId}")
    public ResponseEntity<Void> updateWorkoffer(
            @PathVariable(name = "workofferId") final Long workofferId,
            @RequestBody @Valid final WorkofferDTO workofferDTO) {
        workofferService.update(workofferId, workofferDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{workofferId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteWorkoffer(
            @PathVariable(name = "workofferId") final Long workofferId) {
        workofferService.delete(workofferId);
        return ResponseEntity.noContent().build();
    }

}
