package com.edmunds.ecoins.restservice.service;

import com.edmunds.ecoins.restservice.model.PublicationVote;

public interface PublicationVoteService {

    boolean vote(PublicationVote vote);

    PublicationVote findVote(String userId, String publicationId);
}
