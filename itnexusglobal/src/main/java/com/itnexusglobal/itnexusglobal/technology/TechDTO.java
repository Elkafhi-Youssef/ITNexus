package com.itnexusglobal.itnexusglobal.technology;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechDTO {

    @NotNull
    @Size(max = 255)
    private String technologyName;
}
