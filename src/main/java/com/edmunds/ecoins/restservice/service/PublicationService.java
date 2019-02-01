package com.edmunds.ecoins.restservice.service;

import com.edmunds.ecoins.restservice.model.Publication;

import java.util.List;

public interface PublicationService {

    Publication save(Publication publication);
    List<Publication> findAll();
    List<Publication> getAllPublishedPublications();
    List<Publication> findAllForUser(String userId);
    List<Publication> findFilteredForUser(String userId, boolean published);
    Publication findById(String id);
    void incrementRating(String publicationId);
    void decrementRating(String publicationId);
    void delete(String id);
}
