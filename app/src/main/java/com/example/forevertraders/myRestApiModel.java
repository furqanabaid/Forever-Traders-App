package com.example.forevertraders;

public class myRestApiModel {
    private float userId;
    private float id;
    private String title;
    private String price;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String category;

    public String getDescription() {
        return description;
    }


    private String description;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;

    public myRestApiModel() {
    }

    public myRestApiModel(float userId, float id, String title, String body, String image,String description,String catagory) {
        this.userId = userId;
        this.category =catagory;
        this.id = id;
        this.title = title;
        this.price = body;
        this.image = image;
        this.description=description;
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

    public String getPrice() {
        return price;
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

    public void setPrice(String price) {
        this.price = price;
    }
}
