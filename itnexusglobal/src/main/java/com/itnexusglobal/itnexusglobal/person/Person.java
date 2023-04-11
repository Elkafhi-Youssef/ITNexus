package com.itnexusglobal.itnexusglobal.person;

import com.fasterxml.jackson.annotation.*;
import com.itnexusglobal.itnexusglobal.company.Company;
import com.itnexusglobal.itnexusglobal.profile.Profile;
import com.itnexusglobal.itnexusglobal.role.Role;
import com.itnexusglobal.itnexusglobal.workoffer.Workoffer;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Transactional;



@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Person {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false, unique = true)
    private String tel;

    @Column
    private String linkedIn;

    @Column
    private String github;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE} , fetch = FetchType.EAGER)
    @JoinTable(
            name = "person_role",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> personRoleRoles;

    @OneToOne(
            mappedBy = "personProfileId",
            fetch = FetchType.EAGER
    )
    private Profile profilePerson;
    @JsonIgnore
    @OneToOne(
            mappedBy = "companyPersonId"

    )
    private Company company;
@JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "person_workoffer",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "workoffer_id")
    )
    private Set<Workoffer> workoffers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "rHperson")
    private Set<Workoffer> rHworkoffes;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
