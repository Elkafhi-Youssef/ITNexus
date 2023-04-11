package com.itnexusglobal.itnexusglobal.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByTelIgnoreCase(String tel);
    Person findByEmail(String email);



}
