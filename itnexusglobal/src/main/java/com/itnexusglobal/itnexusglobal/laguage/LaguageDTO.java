package com.itnexusglobal.itnexusglobal.laguage;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LaguageDTO {

    private Long laguageId;

    @Size(max = 255)
    private String languageName;

    @Size(max = 255)
    private String languageLevel;

    private Long laguageProfileId;

}
