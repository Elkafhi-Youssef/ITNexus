package com.itnexusglobal.itnexusglobal.workoffer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itnexusglobal.itnexusglobal.company.Company;
import com.itnexusglobal.itnexusglobal.person.Person;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Getter
@Setter
public class WorkofferDTO {

    private Long workofferId;

    @NotNull
    @Size(max = 255)
    private String offerTitle;

    @NotNull
    private String offerDescription;

    @Size(max = 255)
    private String creationOfferDate;

    private Company companyworkofferid;
    private Set<Person> applayers;


    private Person rHworkofferId;

}
