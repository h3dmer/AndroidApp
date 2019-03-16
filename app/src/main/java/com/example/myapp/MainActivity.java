package com.example.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.myapp.adapter.ListAdapter;
import com.example.myapp.api.ListService;
import com.example.myapp.api.RetrofitClientInstance;
import com.example.myapp.model.ListApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private ListAdapter listAdapter;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



     Call<ListApiResponse> call =
             RetrofitClientInstance
             .getRetrofitInstance()
             .getListResponse()
             .getWholeList();
             //.getListApiResponse(10,10);



     call.enqueue(new Callback<ListApiResponse>() {
         @Override
         public void onResponse(Call<ListApiResponse> call, Response<ListApiResponse> response) {
             Toast.makeText(MainActivity.this, "Dzialaa", Toast.LENGTH_SHORT).show();
             generateList(response.body());
         }

         @Override
         public void onFailure(Call<ListApiResponse> call, Throwable t) {
             Toast.makeText(MainActivity.this, "Something went wrong....", Toast.LENGTH_SHORT).show();
            Log.e("Rest Response ->", call.toString());
            Log.e("Nie dziala -> ", t.toString());
         }
     });


    }


    private void generateList(ListApiResponse apiResponses) {

        recyclerView = findViewById(R.id.mainRecyclerView);
        listAdapter = new ListAdapter(apiResponses, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listAdapter);

    }

}
