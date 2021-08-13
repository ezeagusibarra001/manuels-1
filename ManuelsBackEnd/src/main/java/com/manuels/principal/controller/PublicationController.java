package com.manuels.principal.controller;

import com.manuels.principal.exceptions.NotFoundException;
import com.manuels.principal.models.Publication;
import com.manuels.principal.models.Image;
import com.manuels.principal.service.ImageService;
import com.manuels.principal.service.PublicationService;
import java.io.IOException;

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
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/publications")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;
    
    @Autowired
    private ImageService imageService;
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Publication> create(@RequestBody Publication publication,
                                              @RequestBody MultipartFile image) throws NotFoundException, IOException{
        
        System.out.println("IMAGEN DEL FRONT ---------"+image);
        
        Image img = new Image(image.getOriginalFilename(),
                              image.getContentType(),
                              imageService.compressBytes(image.getBytes()));
        
        System.out.println("IMAGEN CONVERTIDA ---------"+img);
        
        publication.setImage(img);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(publicationService.create(publication));
    }
    
    /*@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Publication> create(@RequestBody Publication publication) throws NotFoundException{
        
        return ResponseEntity.status(HttpStatus.CREATED).body(publicationService.create(publication));
    }*/
    
    @GetMapping
    public List<Publication> listAll(){
        return publicationService.listPublications();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Publication> readBy(@PathVariable(value = "id") Long idReview) throws NotFoundException{

        Publication publication = publicationService.find(idReview);

        if (publication == null) {
            
            throw new NotFoundException("Not found publication");
        }
        return ResponseEntity.ok(publication);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Publication> update(@RequestBody Publication publication,
           @PathVariable(value = "id") Long idPublication){
        return ResponseEntity.status(HttpStatus.CREATED).body(publicationService.update(publication));
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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