package com.edmunds.ecoins.restservice.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class PublicationVote {
    @EmbeddedId
    private PublicationVoteId publicationVoteId;
    @Column
    private boolean isUpvote;

    public PublicationVote() {}

    public PublicationVote(String userId, String publicationId, boolean isUpvote) {
        PublicationVoteId publicationVoteId = new PublicationVoteId();
        publicationVoteId.setUserId(userId);
        publicationVoteId.setPublicationId(publicationId);
        this.publicationVoteId = publicationVoteId;
        this.isUpvote = isUpvote;
    }

    public PublicationVoteId getPublicationVoteId() {
        return publicationVoteId;
    }

    public void setPublicationVoteId(PublicationVoteId publicationVoteId) {
        this.publicationVoteId = publicationVoteId;
    }

    public boolean getIsUpvote() {
        return isUpvote;
    }

    public void setIsUpvote(boolean isUpvote) {
        this.isUpvote = isUpvote;
    }
}
