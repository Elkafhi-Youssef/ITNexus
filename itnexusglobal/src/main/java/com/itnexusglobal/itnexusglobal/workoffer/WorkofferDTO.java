package com.itnexusglobal.itnexusglobal.workoffer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itnexusglobal.itnexusglobal.company.Company;
import com.itnexusglobal.itnexusglobal.person.Person;
import jakarta.persistence.ManyToOne;
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

    private Company companyworkofferid;


    private Person rHworkofferId;

}
