package com.example.myapp.model;

import com.google.gson.annotations.SerializedName;

public class Paginator {

    @SerializedName("current_page")
    private int currentPage;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("items_per_page")
    private int itemsPerPage;

    public Paginator(int currentPage, int totalPages, int itemsPerPage) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.itemsPerPage = itemsPerPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
}
