package com.edmunds.ecoins.restservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static com.edmunds.ecoins.restservice.util.Constants.DEFAULT_DATE_TIME_FORMAT;

@Entity
public class Publication {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(columnDefinition = "char(32)")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    @Column
    private String userId;
    @Column
    @NotBlank
    @Size (min = 1)
    private String text;
    @Column
    @NotBlank
    @Size (min = 1)
    private String title;
    @Column
    private boolean isPublished;
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = DEFAULT_DATE_TIME_FORMAT)
    private LocalDateTime createdDate;
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = DEFAULT_DATE_TIME_FORMAT)
    private LocalDateTime updatedDate;
    @Column
    private int rating;

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

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
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
}
