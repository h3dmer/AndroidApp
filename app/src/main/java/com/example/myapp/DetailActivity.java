package com.example.myapp;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapp.api.RetrofitClientInstance;
import com.example.myapp.model.ItemDetail;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Locale;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {


    private ImageView detailImg;
    private TextView titleTextView;
    private TextView bigText;
    private TextView timeText;
    private TextView subTitle;

    private int id;
    private String header;
    private String title;

    private ItemDetail detailItem;


    private final Handler handler = new Handler();
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailImg = findViewById(R.id.detailImageView);
        titleTextView = findViewById(R.id.titleDetailText);
        subTitle = findViewById(R.id.subtitleDetailText);
        timeText = findViewById(R.id.textClock);
        bigText = findViewById(R.id.scrollingTextView);


        Intent intent = getIntent();
        id = intent.getExtras().getInt("Id");
        header = intent.getExtras().getString("AccessToken");
        title = intent.getExtras().getString("Title");
        titleTextView.setText(title);
        subTitle.setText(intent.getExtras().getString("Subtitle"));

        callEnq();

    }

    private void callEnq() {

        Call<ItemDetail> call =
                RetrofitClientInstance
                        .getRetrofitInstance()
                        //.setRetrofitInstanceDetailsConnection()
                        .getListResponse()
                        .getItem(header, id);


        call.enqueue(new Callback<ItemDetail>() {
            @Override
            public void onResponse(Call<ItemDetail> call, Response<ItemDetail> response) {
                Log.e("Working fine -> ", call.toString());
                Log.e("Working fine -> ", response.message());

                generateDetailDatas(response.body());
                detailItem = response.body();


            }

            @Override
            public void onFailure(Call<ItemDetail> call, Throwable t) {

                Log.e("Something bad -> ", call.toString());
                Log.e("Something bad -> ", t.getMessage());

            }
        });


    }


    private void generateDetailDatas(ItemDetail itemDetail) {

        String color = itemDetail.getColor();


        if (color == null)
            color = "green";



        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this));
        builder.build().load(itemDetail.getThumb())
                .fit()
                //.centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(detailImg);

        titleTextView.setTextColor(Color.parseColor(color));

        bigText.setText(itemDetail.getInfo());
        timeText.setText(getDate(itemDetail.getTime()));

        if (subTitle.length() > 30)
            subTitle.setText(itemDetail.getSubtitle());

    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed( runnable = new Runnable() {
            @Override
            public void run() {
                callEnq();
                handler.postDelayed(runnable, 30000);
                Toast.makeText(getApplicationContext(),"Refresh every 30 sec", Toast.LENGTH_SHORT).show();
            }
        }, 30000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.share, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.buttonShare:
                if(detailItem.getCover() != null) {

                    Picasso.Builder builder = new Picasso.Builder(this);
                    builder.downloader(new OkHttp3Downloader(this));
                    builder.build().load(detailItem.getCover())
                            .fit()
                            .error(R.drawable.ic_launcher_background)
                            .into(detailImg);

                } else {
                    item.setVisible(false);
                    Toast.makeText(getApplicationContext(), "Cover link doesn't work now. Try later...", Toast.LENGTH_SHORT).show();
                }

        }

        return super.onOptionsItemSelected(item);
    }

    private String getDate(int number) {

        Calendar cal = Calendar.getInstance(Locale.GERMANY);
        cal.setTimeInMillis(number);
        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();

        return date;
    }

}
