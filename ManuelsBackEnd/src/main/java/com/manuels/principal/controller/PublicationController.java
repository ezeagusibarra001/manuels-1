package com.manuels.principal.controller;

import com.manuels.principal.exceptions.NotFoundException;
import com.manuels.principal.models.Publication;
import com.manuels.principal.service.PublicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/publications")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PublicationController {
    //@RequestParam recupera por parameter
    @Autowired
    private PublicationService publicationService;
    
    @PostMapping
    public ResponseEntity<Publication> create(@RequestBody Publication publication){
        
        return ResponseEntity.status(HttpStatus.CREATED).body(publicationService.create(publication));
    }
    
    @GetMapping
    public List<Publication> listAll(){
        return publicationService.listPublications();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Publication> readBy(@PathVariable(value = "id") Long idReview) throws NotFoundException{

        Publication publication = publicationService.find(idReview);

        if (publication == null) {
            
            throw new NotFoundException("Not found publication");
            //return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(publication);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Publication> update(@RequestBody Publication publication,
           @PathVariable(value = "id") Long idPublication){
        return ResponseEntity.status(HttpStatus.CREATED).body(publicationService.update(publication));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Publication> delete(@PathVariable(value = "id") Long idPublication) {

        Publication publication = publicationService.find(idPublication);

        if (publication == null) {
            return ResponseEntity.notFound().build();
        }

        publicationService.delete(publication);
        return ResponseEntity.ok().build();
    }
}
