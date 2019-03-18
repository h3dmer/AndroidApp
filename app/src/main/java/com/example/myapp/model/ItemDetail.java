package com.example.myapp.model;

import com.google.gson.annotations.SerializedName;

public class ItemDetail {


    @SerializedName("title")
    private String title;

    @SerializedName("subtitle")
    private String subtitle;

    @SerializedName("info")
    private String info;

    @SerializedName("thumb")
    private String thumb;

    @SerializedName("cover")
    private String cover;

    @SerializedName("time")
    private int time;

    @SerializedName("color")
    private String color;

    public ItemDetail(String title, String subtitle, String info, String thumb, String cover, int time, String color) {
        this.title = title;
        this.subtitle = subtitle;
        this.info = info;
        this.thumb = thumb;
        this.cover = cover;
        this.time = time;
        this.color = color;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
