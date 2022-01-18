package com.example.forevertraders;

public class myRestApiModel {
    private float userId;
    private float id;
    private String title;
    private String body;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;

    public myRestApiModel() {
    }

    public myRestApiModel(float userId, float id, String title, String body, String image) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
        this.image = image;
    }
// Getter Methods

    public float getUserId() {
        return userId;
    }

    public float getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    // Setter Methods

    public void setUserId(float userId) {
        this.userId = userId;
    }

    public void setId(float id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
