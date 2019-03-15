package com.example.myapp.api;

import com.example.myapp.model.ListApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ListService {

    @GET("/index.php")
    Call<ListApiResponse> getListApiResponse(
            @Query("page") int numberOfPage,
            @Query("count") int showItemsInPage
    );

}
