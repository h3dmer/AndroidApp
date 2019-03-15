package com.example.myapp.model;

import com.google.gson.annotations.SerializedName;

public class Paginator {

    @SerializedName("current_page")
    private int currentPage;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("items_per_page")
    private int itemsPerPage;


}
