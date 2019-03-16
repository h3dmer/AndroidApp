package com.example.myapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListApiResponse {

    @SerializedName("access_token")
    @Expose
    private String accessToken;

    @SerializedName("paginator")
    @Expose
    private Paginator paginatorList;

    @SerializedName("items")
    @Expose
    private List<Item> itemsList = new ArrayList<>();

    public ListApiResponse(String accessToken, List<Item> itemsList) {
        this.accessToken = accessToken;
        this.itemsList = itemsList;
    }

    public ListApiResponse(String accessToken, Paginator paginatorList, List<Item> itemsList) {
        this.accessToken = accessToken;
        this.paginatorList = paginatorList;
        this.itemsList = itemsList;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Paginator getPaginatorList() {
        return paginatorList;
    }

    public void setPaginatorList(Paginator paginatorList) {
        this.paginatorList = paginatorList;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }
}
