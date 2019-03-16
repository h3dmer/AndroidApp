package com.example.myapp.api;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String URL = "http://test.goapps.vipserv.org/";
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

    public ListService getListResponse() {
        return retrofit.create(ListService.class);
    }

}
