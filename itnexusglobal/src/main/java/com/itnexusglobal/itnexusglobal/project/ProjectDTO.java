package com.itnexusglobal.itnexusglobal.project;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProjectDTO {

    private Long projectid;

    @NotNull
    @Size(max = 255)
    private String projectTitle;

    private String technologies;

    @Size(max = 255)
    private String projectRepo;

    private Long projectPrfileId;

}
