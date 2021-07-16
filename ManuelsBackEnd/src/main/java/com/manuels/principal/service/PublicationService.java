package com.manuels.principal.service;

import com.manuels.principal.dao.IPublicationDao;
import com.manuels.principal.models.Publication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationService implements IPublicationService {

    @Autowired
    private IPublicationDao publicationDao;
    
    @Override
    public List<Publication> listPublications() {
        return publicationDao.findAll();
    }

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
        existingPublication.setDescripcion(publication.getDescripcion());
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