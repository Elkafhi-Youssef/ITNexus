package com.itnexusglobal.itnexusglobal.hobbie;

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
@RequestMapping(value = "/api/hobbies", produces = MediaType.APPLICATION_JSON_VALUE)
public class HobbieResource {

    private final HobbieService hobbieService;

    public HobbieResource(final HobbieService hobbieService) {
        this.hobbieService = hobbieService;
    }

    @GetMapping
    public ResponseEntity<List<HobbieDTO>> getAllHobbies() {
        return ResponseEntity.ok(hobbieService.findAll());
    }

    @GetMapping("/{hobbieId}")
    public ResponseEntity<HobbieDTO> getHobbie(
            @PathVariable(name = "hobbieId") final Long hobbieId) {
        return ResponseEntity.ok(hobbieService.get(hobbieId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createHobbie(@RequestBody @Valid final HobbieDTO hobbieDTO) {
        return new ResponseEntity<>(hobbieService.create(hobbieDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{hobbieId}")
    public ResponseEntity<Void> updateHobbie(@PathVariable(name = "hobbieId") final Long hobbieId,
            @RequestBody @Valid final HobbieDTO hobbieDTO) {
        hobbieService.update(hobbieId, hobbieDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{hobbieId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteHobbie(@PathVariable(name = "hobbieId") final Long hobbieId) {
        hobbieService.delete(hobbieId);
        return ResponseEntity.noContent().build();
    }

}
