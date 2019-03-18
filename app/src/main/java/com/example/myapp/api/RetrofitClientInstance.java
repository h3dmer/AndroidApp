package com.example.myapp.api;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String URL = "http://test.goapps.vipserv.org/";
    private static RetrofitClientInstance retrofitInstance;

    private OkHttpClient okHttpClient;



    private RetrofitClientInstance() {

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private RetrofitClientInstance(int time_in_second) {

        okHttpClient = new OkHttpClient.Builder()
                .callTimeout(time_in_second, TimeUnit.SECONDS)
                .readTimeout(time_in_second, TimeUnit.SECONDS)
                //.pingInterval(time_in_second, TimeUnit.SECONDS)
                //.connectTimeout(time_in_second, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

    }

    public static synchronized RetrofitClientInstance getRetrofitInstance() {

        if (retrofitInstance == null) {
            retrofitInstance = new RetrofitClientInstance();
        }

        return retrofitInstance;

    }

    public static synchronized RetrofitClientInstance getRetrofitInstance(int time) {

        if (retrofitInstance == null) {
            retrofitInstance = new RetrofitClientInstance(time);
        }

        return retrofitInstance;

    }


    public ListService getListResponse() {
        return retrofit.create(ListService.class);
    }

}
