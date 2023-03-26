package com.itnexusglobal.itnexusglobal.laguage;

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
@RequestMapping(value = "/api/laguages", produces = MediaType.APPLICATION_JSON_VALUE)
public class LaguageResource {

    private final LaguageService laguageService;

    public LaguageResource(final LaguageService laguageService) {
        this.laguageService = laguageService;
    }

    @GetMapping
    public ResponseEntity<List<LaguageDTO>> getAllLaguages() {
        return ResponseEntity.ok(laguageService.findAll());
    }

    @GetMapping("/{laguageId}")
    public ResponseEntity<LaguageDTO> getLaguage(
            @PathVariable(name = "laguageId") final Long laguageId) {
        return ResponseEntity.ok(laguageService.get(laguageId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createLaguage(@RequestBody @Valid final LaguageDTO laguageDTO) {
        return new ResponseEntity<>(laguageService.create(laguageDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{laguageId}")
    public ResponseEntity<Void> updateLaguage(
            @PathVariable(name = "laguageId") final Long laguageId,
            @RequestBody @Valid final LaguageDTO laguageDTO) {
        laguageService.update(laguageId, laguageDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{laguageId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteLaguage(
            @PathVariable(name = "laguageId") final Long laguageId) {
        laguageService.delete(laguageId);
        return ResponseEntity.noContent().build();
    }

}
