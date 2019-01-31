package com.edmunds.ecoins.restservice.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    private String message;
    @Column
    @NotBlank
    @Size (min = 1)
    private String title;
    @Column
    private boolean isPublished;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}
