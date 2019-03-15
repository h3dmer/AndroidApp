package com.example.myapp.api;

import com.example.myapp.model.ListApiResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private Retrofit retrofit;
    private static final String URL = "http://test.goapps.vipserv.org";
    private static RetrofitClientInstance retrofitInstance;


    private RetrofitClientInstance() {

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized RetrofitClientInstance getRetrofitInstance() {

        if (retrofitInstance == null) {
            retrofitInstance = new RetrofitClientInstance();
        }
        return retrofitInstance;

    }

    public ListApiResponse getListResponse() {
        return retrofit.create(ListApiResponse.class);
    }

}
