package com.edmunds.ecoins.restservice.service.impl;

import com.edmunds.ecoins.restservice.model.PublicationVote;
import com.edmunds.ecoins.restservice.model.PublicationVoteId;
import com.edmunds.ecoins.restservice.repository.PublicationVoteRepository;
import com.edmunds.ecoins.restservice.service.PublicationService;
import com.edmunds.ecoins.restservice.service.PublicationVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("publicationVoteService")
public class PublicationVoteServiceImpl implements PublicationVoteService {
    @Autowired
    private PublicationVoteRepository publicationVoteRepository;
    @Autowired
    private PublicationService publicationService;

    @Override
    public boolean vote(PublicationVote vote) {
        Optional<PublicationVote> savedVoteOpt = publicationVoteRepository.findById(vote.getPublicationVoteId());
        if (savedVoteOpt.isPresent()) {
            return false;
        }
        publicationVoteRepository.save(vote);
        publicationService.incrementRating(vote.getPublicationVoteId().getPublicationId());
        return true;
    }

    @Override
    public boolean unvote(PublicationVote vote) {
        Optional<PublicationVote> savedVoteOpt = publicationVoteRepository.findById(vote.getPublicationVoteId());
        if (!savedVoteOpt.isPresent()) {
            return false;
        }
        publicationVoteRepository.delete(vote);
        publicationService.decrementRating(vote.getPublicationVoteId().getPublicationId());
        return true;
    }

    @Override
    public PublicationVote findVote(String userId, String publicationId) {
        PublicationVoteId voteId = new PublicationVoteId();
        voteId.setUserId(userId);
        voteId.setPublicationId(publicationId);
        return publicationVoteRepository.findById(voteId).orElse(null);
    }
}
