package com.edmunds.ecoins.restservice.repository.impl;

import com.edmunds.ecoins.restservice.model.Publication;
import com.edmunds.ecoins.restservice.repository.PublicationRepositoryCustom;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class PublicationRepositoryImpl implements PublicationRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Publication> findFilteredForUser(String userId, boolean published) {
        Query query = entityManager.createNativeQuery("SELECT pub.* FROM PUBLICATION as pub WHERE pub.user_id = :userid AND pub.is_published = :published", Publication.class);
        query.setParameter("userid", userId);
        query.setParameter("published", published);
        return query.getResultList();
    }

    @Override
    public List<Publication> getAllPublishedPublications() {
        Query query = entityManager.createNativeQuery("SELECT pub.* FROM PUBLICATION as pub WHERE pub.is_published=true", Publication.class);
        return query.getResultList();
    }

}
