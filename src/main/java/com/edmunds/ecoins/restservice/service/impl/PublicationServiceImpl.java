package com.edmunds.ecoins.restservice.service.impl;

import com.edmunds.ecoins.restservice.model.Publication;
import com.edmunds.ecoins.restservice.repository.PublicationRepository;
import com.edmunds.ecoins.restservice.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "publicationService")
public class PublicationServiceImpl implements PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public Publication save(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public List<Publication> findAll() {
        return (List) publicationRepository.findAll();
    }

    @Override
    public List<Publication> getAllPublishedPublications() {
        return publicationRepository.getAllPublishedPublications();
    }

    @Override
    public List<Publication> findAllForUser(String userId) {
        return publicationRepository.findByUserId(userId);
    }

    @Override
    public List<Publication> findFilteredForUser(String userId, boolean published) {
        return publicationRepository.findFilteredForUser(userId, published);
    }

    @Override
    public Publication findById(String id) {
        return publicationRepository.findById(id).orElse(null);
    }

    @Override
    public void incrementRating(String publicationId) {
        Publication publication = findById(publicationId);
        publication.setRating(publication.getRating() + 1);
        save(publication);
    }

    @Override
    public void decrementRating(String publicationId) {
        Publication publication = findById(publicationId);
        publication.setRating(publication.getRating() - 1);
        save(publication);
    }

    @Override
    public void delete(String id) {
        publicationRepository.deleteById(id);
    }
}
