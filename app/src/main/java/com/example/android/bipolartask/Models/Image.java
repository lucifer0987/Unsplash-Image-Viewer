package com.example.android.bipolartask.Models;

public class Image {
    private String title, imageURL;

    public Image(String title, String imageURL) {
        this.title = title;
        this.imageURL = imageURL;
    }

    public Image() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
