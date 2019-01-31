package com.edmunds.ecoins.restservice.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PublicationVoteDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String publicationId;
    @NotNull
    private boolean isUpvote;

    public PublicationVoteDto() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public boolean getIsUpvote() {
        return isUpvote;
    }

    public void setIsUpvote(boolean isUpvote) {
        this.isUpvote = isUpvote;
    }
}
