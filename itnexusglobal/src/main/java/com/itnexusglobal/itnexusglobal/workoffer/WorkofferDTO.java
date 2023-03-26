package com.itnexusglobal.itnexusglobal.workoffer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WorkofferDTO {

    private Long workofferId;

    @NotNull
    @Size(max = 255)
    private String offerTitle;

    @NotNull
    @Size(max = 255)
    private String offerDescription;

    @NotNull
    @Size(max = 255)
    private String creationOfferDate;

    private Long companyworkofferid;

    @JsonProperty("rHworkofferId")
    private Long rHworkofferId;

}
