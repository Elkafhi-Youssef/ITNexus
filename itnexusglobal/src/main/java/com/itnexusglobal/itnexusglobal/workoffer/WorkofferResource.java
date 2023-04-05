package com.itnexusglobal.itnexusglobal.workoffer;

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
    public DataResponse getAllWorkoffers(@RequestParam(value = "page",defaultValue = "0") int page, @RequestParam(value = "size",defaultValue = "2") int size) {
        DataResponse response = new DataResponse("list of workoffers",200,workofferService.getAllOffers(page,size));
        return response;
    }

    @GetMapping("/{workofferId}")
    public ResponseEntity<WorkofferDTO> getWorkoffer(
            @PathVariable(name = "workofferId") final Long workofferId) {
        return ResponseEntity.ok(workofferService.get(workofferId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_RH')")
    @ApiResponse(responseCode = "201")
    public Response createWorkoffer(
            @RequestBody @Valid final WorkofferDTO workofferDTO, Principal principal) {
        Response response;
        if (workofferService.create(workofferDTO,principal) > 0){
            return response = new Response("workoffer created successfully",201);
        }
        return  response = new Response("workoffer doen't created successfully",422);
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
