package com.itnexusglobal.itnexusglobal.person;

import com.itnexusglobal.itnexusglobal.company.Company;
import com.itnexusglobal.itnexusglobal.role.Role;
import com.itnexusglobal.itnexusglobal.workoffer.Workoffer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PersonDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String firstName;

    @NotNull
    @Size(max = 255)
    private String lastName;

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 255)
    private String address;

    @NotNull
    @Size(max = 255)
    private String password;

    @NotNull
    @Size(max = 255)
    private String image;

    @NotNull
    @Size(max = 255)
    private String tel;

    @Size(max = 255)
    private String linkedIn;

    @Size(max = 255)
    private String github;
    private Company company;
    private List<Workoffer> workofferListOfRH;

    private List<Long> personRoleRoles;

    private List<Long> workoffers;

    private String role;



}
