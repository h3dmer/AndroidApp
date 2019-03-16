package com.example.myapp.api;

import com.example.myapp.model.ListApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ListService {


    @GET("index.php")
    Call<ListApiResponse> getItems(
            @Query("page") int numberOfPage,
            @Query("count") int showItemsInPage
    );

    @GET("index.php")
    Call<ListApiResponse> getWholeList();

}
