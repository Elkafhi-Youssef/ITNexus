package com.itnexusglobal.itnexusglobal.experience;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExperienceDTO {

    private Long experienceId;

    @NotNull
    @Size(max = 255)
    private String expeName;

    @NotNull
    @Size(max = 255)
    private String position;

    @NotNull
    @Size(max = 255)
    private String startDate;

    @NotNull
    @Size(max = 255)
    private String endDate;

    @NotNull
    private String descreption;

    private Long experienceProfileId;

}
