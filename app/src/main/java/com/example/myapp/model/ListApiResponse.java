package com.example.myapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListApiResponse {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("paginator")
    private List<Paginator> paginatorList = new ArrayList<>();

    @SerializedName("items")
    private List<Item> itemsList = new ArrayList<>();

}
