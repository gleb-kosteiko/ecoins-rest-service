package com.edmunds.ecoins.restservice.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class PublicationVote {
    @EmbeddedId
    private PublicationVoteId publicationVoteId;

    public PublicationVote() {}

    public PublicationVote(String userId, String publicationId) {
        PublicationVoteId publicationVoteId = new PublicationVoteId();
        publicationVoteId.setUserId(userId);
        publicationVoteId.setPublicationId(publicationId);
        this.publicationVoteId = publicationVoteId;
    }

    public PublicationVoteId getPublicationVoteId() {
        return publicationVoteId;
    }

    public void setPublicationVoteId(PublicationVoteId publicationVoteId) {
        this.publicationVoteId = publicationVoteId;
    }
}
