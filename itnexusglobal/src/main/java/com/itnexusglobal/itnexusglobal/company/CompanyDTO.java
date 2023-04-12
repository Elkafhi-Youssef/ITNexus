package com.itnexusglobal.itnexusglobal.company;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CompanyDTO {

    private Long companyId;

    @NotNull
    @Size(max = 255)
    private String comName;

    @Size(max = 255)
    private String website;

    @NotNull
    private Long employiesNumber;

    @Size(max = 255)
    private String creationDate;

    @NotNull
    @Size(max = 255)
    private String address;

    @NotNull
    @Size(max = 255)
    private String description;

    @NotNull
    @Size(max = 255)
    private String linkedIn;

    private Long companyPersonId;

}
