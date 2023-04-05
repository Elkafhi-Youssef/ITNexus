package com.itnexusglobal.itnexusglobal.workoffer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface WorkofferRepository extends PagingAndSortingRepository<Workoffer, Long>,JpaRepository<Workoffer, Long> {
}
