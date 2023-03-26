package com.itnexusglobal.itnexusglobal.skills;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SkillsDTO {

    private Long skillId;

    @Size(max = 255)
    private String skillType;

    @Size(max = 255)
    private String skillName;

    private Long skillProfileId;

}
