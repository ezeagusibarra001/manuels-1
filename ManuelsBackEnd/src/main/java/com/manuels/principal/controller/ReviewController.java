package com.manuels.principal.controller;

import com.manuels.principal.exceptions.NotFoundException;
import com.manuels.principal.models.Review;
import com.manuels.principal.service.ReviewService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/reviews")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
    @PostMapping
    public ResponseEntity<Review> create(@RequestBody Review review){
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.create(review));
    }
    
    @GetMapping
    public List<Review> listAll(){
        return reviewService.listReviews();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Review> readBy(@PathVariable(value = "id") Long idReview) throws NotFoundException{

        Review review = reviewService.find(idReview);

        if (review == null) {
            throw new NotFoundException("Not found review");
        }
        return ResponseEntity.ok(review);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/accept/{id}")
    public ResponseEntity<Review> setTrue(@PathVariable(value = "id") Long idReview){
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.setTrue(idReview));
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Review> update(@RequestBody Review review,
           @PathVariable(value = "id") Long idReview){
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.update(review));
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Review> delete(@PathVariable(value = "id") Long idReview) {

        Review review = reviewService.find(idReview);

        if (review == null) {
            return ResponseEntity.notFound().build();
        }

        reviewService.delete(review);
        return ResponseEntity.ok().build();
    }
}
