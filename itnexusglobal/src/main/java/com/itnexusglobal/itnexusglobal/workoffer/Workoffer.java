package com.itnexusglobal.itnexusglobal.workoffer;

import com.fasterxml.jackson.annotation.*;
import com.itnexusglobal.itnexusglobal.company.Company;
import com.itnexusglobal.itnexusglobal.person.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "workofferId")
public class Workoffer implements Serializable {

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
    private Long workofferId;

    @Column(nullable = false)
    private String offerTitle;

    @Column(nullable = false)
    private String offerDescription;

//    @Column(nullable = false)
//    private String creationOfferDate;

    @ManyToOne()
    @JoinColumn(name = "companyworkofferid_id")
    private Company companyworkofferid;
    @JsonIgnore
    @ManyToMany(mappedBy = "workoffers" ,fetch = FetchType.EAGER)
    private Set<Person> persons = new HashSet<>();


    @ManyToOne()
    @JoinColumn(name = "r_hperson_id")
    private Person rHperson;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
