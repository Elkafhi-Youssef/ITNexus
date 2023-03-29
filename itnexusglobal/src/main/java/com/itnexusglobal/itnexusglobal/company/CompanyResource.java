package com.itnexusglobal.itnexusglobal.company;

import com.itnexusglobal.itnexusglobal.person.PersonRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import java.security.Principal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/companys", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyResource {

    private final CompanyService companyService;
    private final PersonRepository personRepository;

    public CompanyResource(final CompanyService companyService, PersonRepository personRepository) {
        this.companyService = companyService;
        this.personRepository = personRepository;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanys() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDTO> getCompany(
            @PathVariable(name = "companyId") final Long companyId) {
        return ResponseEntity.ok(companyService.get(companyId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_RH')")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCompany(@RequestBody @Valid final CompanyDTO companyDTO,
            Principal principal) {
        return new ResponseEntity<>(companyService.create(companyDTO,principal), HttpStatus.CREATED);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<Void> updateCompany(
            @PathVariable(name = "companyId") final Long companyId,
            @RequestBody @Valid final CompanyDTO companyDTO) {
        companyService.update(companyId, companyDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{companyId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCompany(
            @PathVariable(name = "companyId") final Long companyId) {
        companyService.delete(companyId);
        return ResponseEntity.noContent().build();
    }

}
