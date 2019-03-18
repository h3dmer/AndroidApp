package com.example.myapp.api;

import com.example.myapp.model.ItemDetail;
import com.example.myapp.model.ListApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ListService {


    @GET("index.php")
    Call<ListApiResponse> getItems(
            @Query("page") int numberOfPage,
            @Query("count") int showItemsInPage
    );

    @GET("index.php")
    Call<ListApiResponse> getWholeList();


    @GET("item.php")
    Call<ItemDetail> getItem(@Header("Access-Token") String token,
                             @Query("id") int id);


}
