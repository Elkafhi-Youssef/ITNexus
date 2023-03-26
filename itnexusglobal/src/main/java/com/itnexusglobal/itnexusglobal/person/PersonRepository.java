package com.itnexusglobal.itnexusglobal.person;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByTelIgnoreCase(String tel);

}
