package com.manuels.principal.dao;

import com.manuels.principal.models.Publication;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPublicationDao extends JpaRepository<Publication, Long>{
    @Query(value = "SELECT *FROM publication WHERE title LIKE %?1%", nativeQuery = true)
    public List<Publication> findByName(String title);
}