package com.itnexusglobal.itnexusglobal.education;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EducationDTO {

    private Long educationId;

    @NotNull
    @Size(max = 255)
    private String eduName;

    @NotNull
    @Size(max = 255)
    private String startDate;

    @NotNull
    @Size(max = 255)
    private String endDate;

    private Long educationProfaileId;

}
