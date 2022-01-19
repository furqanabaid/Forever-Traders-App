package com.example.forevertraders;

public class productDetail {
    public productDetail() {
    }

    String title;
    String price;
    String description;
    String image;
    String Catagory;

    public productDetail(String title, String price, String description, String image, String catagory) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.image = image;
        Catagory = catagory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCatagory() {
        return Catagory;
    }

    public void setCatagory(String catagory) {
        Catagory = catagory;
    }
}
