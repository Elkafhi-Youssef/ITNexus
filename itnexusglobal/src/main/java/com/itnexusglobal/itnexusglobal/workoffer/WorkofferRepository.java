package com.itnexusglobal.itnexusglobal.workoffer;

import com.itnexusglobal.itnexusglobal.person.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface WorkofferRepository extends PagingAndSortingRepository<Workoffer, Long>,JpaRepository<Workoffer, Long> {
    @Query("SELECT p FROM Person p JOIN p.workoffers o WHERE o.workofferId = :offerId")
    List<Person> findPersonsByOfferId(Long offerId);

    @Query("SELECT w, w.companyworkofferid, w.rHperson FROM Workoffer w JOIN  w.companyworkofferid JOIN  w.rHperson")
    Page<Workoffer> findAllWithCompanyAndPerson(Pageable pageable);


}
