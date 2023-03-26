package com.itnexusglobal.itnexusglobal.profile;

import com.itnexusglobal.itnexusglobal.education.Education;
import com.itnexusglobal.itnexusglobal.experience.Experience;
import com.itnexusglobal.itnexusglobal.hobbie.Hobbie;
import com.itnexusglobal.itnexusglobal.laguage.Laguage;
import com.itnexusglobal.itnexusglobal.person.Person;
import com.itnexusglobal.itnexusglobal.project.Project;
import com.itnexusglobal.itnexusglobal.skills.Skills;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import java.time.OffsetDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Profile {

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
    private Long profileId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_profile_id_id", unique = true)
    private Person personProfileId;

    @OneToMany(mappedBy = "experienceProfileId")
    private Set<Experience> experiences;

    @OneToMany(mappedBy = "educationProfaileId")
    private Set<Education> educations;

    @OneToMany(mappedBy = "projectPrfileId")
    private Set<Project> projects;

    @OneToMany(mappedBy = "skillProfileId")
    private Set<Skills> skills;

    @OneToMany(mappedBy = "laguageProfileId")
    private Set<Laguage> laguages;

    @OneToMany(mappedBy = "hobbieProfileId")
    private Set<Hobbie> hobbies;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
