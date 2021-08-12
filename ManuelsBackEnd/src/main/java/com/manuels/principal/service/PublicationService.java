package com.manuels.principal.service;

import com.manuels.principal.dao.IPublicationDao;
import com.manuels.principal.models.Publication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class PublicationService implements IPublicationService {
    
    @Autowired
    private ImageService imageService;
    
    @Autowired
    private IPublicationDao publicationDao;
    
    @Override
    public List<Publication> listPublications() {
        List<Publication> publications = publicationDao.findAll();

        return publications;
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public Publication create(Publication publication) {
        
        return publicationDao.save(publication);
    }

    @Override
    public void delete(Publication publication) {
        publicationDao.delete(publication);
    }

    @Override
    public Publication update(Publication publication) {
        
        Publication existingPublication = publicationDao.findById(publication.getIdPublication()).orElse(null);

        existingPublication.setTitle(publication.getTitle());
        existingPublication.setDescription(publication.getDescription());
        existingPublication.setDescription(publication.getDescription1());
        existingPublication.setDescription(publication.getDescription2());
        existingPublication.setDescription(publication.getDescription3());
        existingPublication.setSubtitle(publication.getSubtitle());
        existingPublication.setSubtitle(publication.getSubtitle2());
        existingPublication.setImage(publication.getImage());

        return publicationDao.save(publication);
    }

    @Override
    public Publication find(Long idPublication) {
        return publicationDao.findById(idPublication).orElse(null);
    }

    @Override
    public List<Publication> findByName(String publication) {
        return publicationDao.findByName(publication);
    }
}