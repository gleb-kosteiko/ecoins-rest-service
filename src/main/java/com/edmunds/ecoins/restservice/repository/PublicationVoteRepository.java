package com.edmunds.ecoins.restservice.repository;

import com.edmunds.ecoins.restservice.model.PublicationVote;
import com.edmunds.ecoins.restservice.model.PublicationVoteId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationVoteRepository  extends CrudRepository<PublicationVote, PublicationVoteId> {
}
