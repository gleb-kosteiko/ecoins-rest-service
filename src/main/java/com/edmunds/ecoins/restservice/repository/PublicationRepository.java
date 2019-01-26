package com.edmunds.ecoins.restservice.repository;

import com.edmunds.ecoins.restservice.model.Publication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends CrudRepository<Publication, String> {

    List<Publication> findByUserId(String userId);
}
