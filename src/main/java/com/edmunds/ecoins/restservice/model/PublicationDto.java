package com.edmunds.ecoins.restservice.model;

import java.time.LocalDateTime;

public class PublicationDto {
    private String id;
    private String userId;
    private String text;
    private String title;
    private boolean isPublished;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private int rating;
    private String category;
    private String imageUrl;
    private String userName;
    private String userImageUrl;

    public PublicationDto(Publication publication, User user) {
        this.setId(publication.getId());
        this.setTitle(publication.getTitle());
        this.setText(publication.getText());
        this.setCategory(publication.getCategory());
        this.setCreatedDate(publication.getCreatedDate());
        this.setUpdatedDate(publication.getUpdatedDate());
        this.setImageUrl(publication.getImageUrl());
        this.setUserId(publication.getUserId());
        this.setIsPublished(publication.isPublished());
        this.setRating(publication.getRating());
        this.setUserName(user.getUsername());
        this.setUserImageUrl(user.getImageUrl());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean published) {
        this.isPublished = published;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }
}
