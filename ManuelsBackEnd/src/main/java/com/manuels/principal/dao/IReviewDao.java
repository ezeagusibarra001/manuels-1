package com.manuels.principal.dao;

import com.manuels.principal.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewDao extends JpaRepository<Review, Long>{
}