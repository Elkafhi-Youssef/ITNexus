package com.itnexusglobal.itnexusglobal.hobbie;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HobbieDTO {

    private Long hobbieId;

    @Size(max = 255)
    private String hobbieName;

    private Long hobbieProfileId;

}
