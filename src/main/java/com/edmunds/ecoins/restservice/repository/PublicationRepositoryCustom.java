package com.edmunds.ecoins.restservice.repository;

import com.edmunds.ecoins.restservice.model.Publication;

import java.util.List;

public interface PublicationRepositoryCustom {

    List<Publication> findFilteredForUser(String userId, boolean published);

    List<Publication> getAllPublishedPublications();
}
