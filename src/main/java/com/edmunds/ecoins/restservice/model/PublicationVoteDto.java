package com.edmunds.ecoins.restservice.model;

import javax.validation.constraints.NotBlank;

public class PublicationVoteDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String publicationId;

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
}
