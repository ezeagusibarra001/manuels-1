package com.manuels.principal.dao;

import com.manuels.principal.models.Payment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPaymentDao extends JpaRepository<Payment, Long>{    
    @Query(value = "SELECT *FROM payment WHERE name LIKE %?1%", nativeQuery = true)
    public List<Payment> findByName(String name);
}