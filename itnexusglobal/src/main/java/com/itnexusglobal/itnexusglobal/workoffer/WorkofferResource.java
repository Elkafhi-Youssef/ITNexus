package com.itnexusglobal.itnexusglobal.workoffer;

import com.itnexusglobal.itnexusglobal.person.Person;
import com.itnexusglobal.itnexusglobal.response.AuthResponse;
import com.itnexusglobal.itnexusglobal.response.DataResponse;
import com.itnexusglobal.itnexusglobal.response.Response;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import java.security.Principal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/workoffers", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkofferResource {

    private final WorkofferService workofferService;

    public WorkofferResource(final WorkofferService workofferService) {
        this.workofferService = workofferService;
    }

    @GetMapping
    public ResponseEntity<DataResponse>  getAllWorkoffers(@RequestParam(value = "page",defaultValue = "0") int page, @RequestParam(value = "size",defaultValue = "2") int size) {
        DataResponse response = new DataResponse("list of workoffers",200,workofferService.getAllOffers(page,size));
        return ResponseEntity.ok(response);
    }
    @PostMapping("/applyOffer")
    public ResponseEntity<String> applyOffer( @RequestBody @Valid final Applay applay, Principal principal){
        if (this.workofferService.applyOffer( applay.getIdoffer() , principal)){
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authentication failed!");
    }

    @GetMapping("/{workofferId}")
    public ResponseEntity<WorkofferDTO> getWorkoffer(
            @PathVariable(name = "workofferId") final Long workofferId) {
        return ResponseEntity.ok(workofferService.get(workofferId));
    }
    @GetMapping("/applayers/{workofferId}")
    public ResponseEntity<List<Person>> getAllApplayers(
            @PathVariable(name = "workofferId") final Long workofferId) {
        return ResponseEntity.ok(workofferService.getApplayersOffer(workofferId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_RH')")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<String>  createWorkoffer(
            @RequestBody @Valid final WorkofferDTO workofferDTO, Principal principal) {
        System.out.println(workofferDTO.getOfferTitle());
        System.out.println(workofferDTO.getOfferDescription());
        Response response;
        if (workofferService.create(workofferDTO,principal) > 0){
            System.out.println("dazt hhhhhhhh");
            return ResponseEntity.ok("the workoffer was successfully created");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error creating workoffer");
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
