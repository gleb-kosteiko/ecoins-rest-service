package com.edmunds.ecoins.restservice.service.impl;

import com.edmunds.ecoins.restservice.model.Publication;
import com.edmunds.ecoins.restservice.repository.PublicationRepository;
import com.edmunds.ecoins.restservice.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service("publicationService")
public class PublicationServiceImpl implements PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public Publication save(Publication publication) {
        if(publication.getId() == null) {
            return publicationRepository.save(publication);
        }
        Publication oldPublication = findById(publication.getId());
        if (!Objects.isNull(publication.getTitle())) {
            oldPublication.setTitle(publication.getTitle());
        }
        if (!Objects.isNull(publication.getSubtitle())) {
            oldPublication.setSubtitle(publication.getSubtitle());
        }
        if (!Objects.isNull(publication.getCategory())) {
            oldPublication.setCategory(publication.getCategory());
        }
        if (!Objects.isNull(publication.getText())) {
            oldPublication.setText(publication.getText());
        }
        if (!Objects.isNull(publication.getUpdatedDate())) {
            oldPublication.setUpdatedDate(publication.getUpdatedDate());
        }
        if (!Objects.isNull(publication.getImageUrl())) {
            oldPublication.setImageUrl(publication.getImageUrl());
        }
        if (!Objects.isNull(publication.getIsPublished())) {
            oldPublication.setIsPublished(publication.getIsPublished());
        }
        return publicationRepository.save(oldPublication);
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
    public List<Publication> getPublishedPublicationsByCategory(String category) {
        return publicationRepository.getPublishedPublicationsByCategory(category);
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
