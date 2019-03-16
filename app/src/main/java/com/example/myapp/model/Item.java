package com.example.myapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("subtitle")
    @Expose
    private String subtitle;

    @SerializedName("thumb")
    @Expose
    private String thumbURL;

    @SerializedName("color")
    @Expose
    private String color;

    public Item(int id, String title, String subtitle, String thumbURL, String color) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.thumbURL = thumbURL;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getThumbURL() {
        return thumbURL;
    }

    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
