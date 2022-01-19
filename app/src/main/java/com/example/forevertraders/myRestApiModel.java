package com.example.forevertraders;

public class myRestApiModel {
    private float id;
    private String title;
    private String price;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    int quantity;

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


    public void setDescription(String description) {
        this.description = description;
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

    public myRestApiModel( float id, String title, String body, String image,String description,String catagory,int count) {
        this.quantity =count;
        this.category =catagory;
        this.id = id;
        this.title = title;
        this.price = body;
        this.image = image;
        this.description=description;
    }
// Getter Methods

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
