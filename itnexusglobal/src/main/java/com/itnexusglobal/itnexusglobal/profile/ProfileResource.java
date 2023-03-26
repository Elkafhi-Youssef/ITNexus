package com.itnexusglobal.itnexusglobal.profile;

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
@RequestMapping(value = "/api/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileResource {

    private final ProfileService profileService;

    public ProfileResource(final ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> getAllProfiles() {
        return ResponseEntity.ok(profileService.findAll());
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileDTO> getProfile(
            @PathVariable(name = "profileId") final Long profileId) {
        return ResponseEntity.ok(profileService.get(profileId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createProfile(@RequestBody @Valid final ProfileDTO profileDTO) {
        return new ResponseEntity<>(profileService.create(profileDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<Void> updateProfile(
            @PathVariable(name = "profileId") final Long profileId,
            @RequestBody @Valid final ProfileDTO profileDTO) {
        profileService.update(profileId, profileDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{profileId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteProfile(
            @PathVariable(name = "profileId") final Long profileId) {
        profileService.delete(profileId);
        return ResponseEntity.noContent().build();
    }

}
