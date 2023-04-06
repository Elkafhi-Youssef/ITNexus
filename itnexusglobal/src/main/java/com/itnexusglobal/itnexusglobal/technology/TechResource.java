package com.itnexusglobal.itnexusglobal.technology;


import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/techs", produces = MediaType.APPLICATION_JSON_VALUE)
public class TechResource {

    private final  TechService techService;

    public TechResource(TechService techService) {
        this.techService = techService;
    }

    @GetMapping
    public ResponseEntity<List<TechDTO>> getAllTechs() {
        return ResponseEntity.ok(techService.findAll());
    }

    @GetMapping("/{techId}")
    public ResponseEntity<TechDTO> getTechs(@PathVariable(name = "techId") final Long techId) {
        return ResponseEntity.ok(techService.get(techId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createtechs(@RequestBody @Valid final TechDTO techDTO) {
        System.out.println(techDTO.getTechnologyName());
        return new ResponseEntity<>(techService.create(techDTO), HttpStatus.CREATED);
    }
}
