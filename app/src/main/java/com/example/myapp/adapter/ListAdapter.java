package com.example.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapp.DetailActivity;
import com.example.myapp.R;
import com.example.myapp.model.Item;
import com.example.myapp.model.ListApiResponse;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ResponseViewHolder> {


    private List<Item> dataList;
    private Context context;
    private ListApiResponse apiRes;


    public ListAdapter(List<Item> dataList, Context context, ListApiResponse apiRes) {

        this.dataList = dataList;
        this.context = context;
        this.apiRes = apiRes;
    }


    @NonNull
    @Override
    public ListAdapter.ResponseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.single_row, viewGroup, false);
        return new ResponseViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ResponseViewHolder viewHolder, final int i) {


        String jsonColor;

        if (dataList.get(i).getColor() == null)
            jsonColor = "null";
        else
            jsonColor = dataList.get(i).getColor();

        //Log.e("Color ->>>>> ", jsonColor);

        viewHolder.txtTitle.setText(dataList.get(i).getTitle());
        viewHolder.txtSubtitle.setText(dataList.get(i).getSubtitle());



        switch (jsonColor) {
            case "green": viewHolder.rl.setBackgroundColor(Color.GREEN); break;
            case "blue":  viewHolder.rl.setBackgroundColor(Color.BLUE); break;
            case "null":  viewHolder.rl.setBackgroundColor(Color.WHITE); break;
            case "grey":  viewHolder.rl.setBackgroundColor(Color.GRAY); break;
            case "red":  viewHolder.rl.setBackgroundColor(Color.RED); break;
            case "yellow":  viewHolder.rl.setBackgroundColor(Color.YELLOW); break;

            default: viewHolder.rl.setBackgroundColor(Color.WHITE);
        }

        Glide
                .with(context)
                .load(dataList.get(i).getThumbURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //.fitCenter()
                //.centerCrop()
                .into(viewHolder.image);


        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailActivity.class);

                intent.putExtra("Id", dataList.get(i).getId());
                intent.putExtra("Title", dataList.get(i).getTitle());
                intent.putExtra("Subtitle", dataList.get(i).getSubtitle());
                intent.putExtra("AccessToken", apiRes.getAccessToken());
                Log.e("Access Token -> ", apiRes.getAccessToken());

                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void clear() {
        dataList.clear();
        notifyDataSetChanged();
    }

    public void addItems(List<Item> items) {

        for (Item i : items) {
            dataList.add(i);
        }

        notifyDataSetChanged();

    }



    class ResponseViewHolder extends RecyclerView.ViewHolder {

        private View mView;

        private ImageView image;
        private TextView txtTitle;
        private TextView txtSubtitle;
        private CardView cardView;
        private RelativeLayout rl;



        public ResponseViewHolder(View rowView) {

            super(rowView);

            mView = rowView;

            rl = mView.findViewById(R.id.relativeLayout);
            image = mView.findViewById(R.id.thumbImage);
            txtTitle = mView.findViewById(R.id.title);
            txtSubtitle = mView.findViewById(R.id.subtitle);
            cardView = mView.findViewById(R.id.mainCardView);

        }
    }



}
