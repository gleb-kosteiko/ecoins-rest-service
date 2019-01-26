package com.edmunds.ecoins.restservice.service;

import com.edmunds.ecoins.restservice.model.Publication;

import java.util.List;

public interface PublicationService {

    Publication save(Publication publication);
    List<Publication> findAll();
    List<Publication> findAllForUser(String userId);
    Publication findById(String id);
    void delete(String id);
}
