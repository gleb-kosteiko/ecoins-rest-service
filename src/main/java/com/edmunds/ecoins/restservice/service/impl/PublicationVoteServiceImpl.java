package com.edmunds.ecoins.restservice.service.impl;

import com.edmunds.ecoins.restservice.model.PublicationVote;
import com.edmunds.ecoins.restservice.model.PublicationVoteId;
import com.edmunds.ecoins.restservice.repository.PublicationVoteRepository;
import com.edmunds.ecoins.restservice.service.PublicationService;
import com.edmunds.ecoins.restservice.service.PublicationVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "publicationVoteService")
public class PublicationVoteServiceImpl implements PublicationVoteService {
    @Autowired
    private PublicationVoteRepository publicationVoteRepository;
    @Autowired
    private PublicationService publicationService;

    @Override
    public boolean vote(PublicationVote vote) {
        Optional<PublicationVote> savedVoteOpt = publicationVoteRepository.findById(vote.getPublicationVoteId());
        if (savedVoteOpt.isPresent()) {
            PublicationVote savedVote = savedVoteOpt.get();
            if (vote.getIsUpvote() == savedVote.getIsUpvote()) {
                return false;
            } else {
                if (savedVote.getIsUpvote()) {
                    publicationService.decrementRating(vote.getPublicationVoteId().getPublicationId());
                } else {
                    publicationService.incrementRating(vote.getPublicationVoteId().getPublicationId());
                }
                publicationVoteRepository.delete(savedVote);
            }
        } else {
            publicationVoteRepository.save(vote);
            if (vote.getIsUpvote()) {
                publicationService.incrementRating(vote.getPublicationVoteId().getPublicationId());
            } else {
                publicationService.decrementRating(vote.getPublicationVoteId().getPublicationId());
            }
        }
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
