package com.manuels.principal.dao;

import com.manuels.principal.models.DateC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDateDao extends JpaRepository<DateC, Long>{  
}