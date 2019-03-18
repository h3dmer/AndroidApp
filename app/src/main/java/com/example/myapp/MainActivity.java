package com.example.myapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myapp.adapter.ListAdapter;
import com.example.myapp.api.ListService;
import com.example.myapp.api.RetrofitClientInstance;
import com.example.myapp.model.Item;
import com.example.myapp.model.ListApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private ListAdapter listAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout pullToRefresh;
    private LinearLayoutManager layoutManager;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private int pageNumber = 0;
    private int countItems = 10;


    private boolean isLoading = true;
    private int pastVisibleItems , visibleItemCount, totalItemCount, previuos_total = 0;
    private int view_threshold = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.mainRecyclerView);
        pullToRefresh = findViewById(R.id.pullToRefresh);

        preferences = getSharedPreferences("accessToken", MODE_PRIVATE);
        editor = preferences.edit();


        layoutManager = new LinearLayoutManager(MainActivity.this);
        

        callEnq();


        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listAdapter.clear();

                callEnq();

                pageNumber = 0;
                pastVisibleItems = 0;
                visibleItemCount = 0;
                totalItemCount = 0;
                previuos_total = 0;

                Toast.makeText(MainActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();

                pullToRefresh.setRefreshing(false);
            }
        });

        //callEnq();

       recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
           @Override
           public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
               super.onScrolled(recyclerView, dx, dy);

               visibleItemCount = layoutManager.getChildCount();
               totalItemCount = layoutManager.getItemCount();
               pastVisibleItems = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();


               if (dy > 0)
               {
                   if(isLoading) {
                       if (totalItemCount > previuos_total) {
                           isLoading = false;
                           previuos_total = totalItemCount;
                       }
                   }

                   if (!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleItems + view_threshold))
                   {
                       pageNumber++;
                       pagination();
                       //callEnq();
                       isLoading = true;
                   }

               }


           }
       });

    }


    private void callEnq() {

        Call<ListApiResponse> call =
                RetrofitClientInstance
                        .getRetrofitInstance()
                        .getListResponse()
                        .getItems(0, 10);

        call.enqueue(new Callback<ListApiResponse>() {
            @Override
            public void onResponse(Call<ListApiResponse> call, Response<ListApiResponse> response) {
                Log.e("Working fine", response.toString());
                //Log.e("Access Token", response.body().getAccessToken());

                List<Item> items = response.body().getItemsList();
                listAdapter = new ListAdapter(items, MainActivity.this, response.body());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(listAdapter);

                editor.putString("akces", response.body().getAccessToken()).apply();
                editor.commit();
            }

            @Override
            public void onFailure(Call<ListApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong....", Toast.LENGTH_SHORT).show();
                Log.e("Rest Response ->", call.toString());
                Log.e("Nie dziala -> ", t.toString());
            }
        });


    }


    private void pagination() {

        Call<ListApiResponse> call =
                RetrofitClientInstance
                        .getRetrofitInstance()
                        .getListResponse()
                        .getItems(pageNumber, countItems);

        call.enqueue(new Callback<ListApiResponse>() {
            @Override
            public void onResponse(Call<ListApiResponse> call, Response<ListApiResponse> response) {
                Log.e("Working fine", response.toString());

                List<Item> items = response.body().getItemsList();
                listAdapter.addItems(items);
                Toast.makeText(MainActivity.this, "Next page", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ListApiResponse> call, Throwable t) {
                Log.e("Pagination failed ", call.toString());
                Log.e("Here is message -> ", t.getMessage());
            }
        });

    }

}
