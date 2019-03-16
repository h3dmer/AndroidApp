package com.example.myapp.adapter;

import android.content.Context;
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
import com.example.myapp.R;
import com.example.myapp.model.ListApiResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ResponseViewHolder> {


    private ListApiResponse dataList;
    private Context context;


    public ListAdapter(ListApiResponse dataList, Context context) {

        this.dataList = dataList;
        this.context = context;

    }


    @NonNull
    @Override
    public ListAdapter.ResponseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.single_row, viewGroup, false);
        return new ResponseViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ResponseViewHolder viewHolder, int i) {


        String jsonColor;

        if (dataList.getItemsList().get(i).getColor() == null)
            jsonColor = "null";
        else
            jsonColor = dataList.getItemsList().get(i).getColor();

        Log.e("Color ->>>>> ", jsonColor);

        viewHolder.txtTitle.setText(dataList.getItemsList().get(i).getTitle());
        viewHolder.txtSubtitle.setText(dataList.getItemsList().get(i).getSubtitle());



        switch (jsonColor) {
            case "green": viewHolder.rl.setBackgroundColor(Color.GREEN); break;
            case "blue":  viewHolder.rl.setBackgroundColor(Color.BLUE); break;
            case "null":  viewHolder.rl.setBackgroundColor(Color.WHITE); break;
            case "grey":  viewHolder.rl.setBackgroundColor(Color.GREEN); break;
            case "red":  viewHolder.rl.setBackgroundColor(Color.RED); break;
            case "yellow":  viewHolder.rl.setBackgroundColor(Color.YELLOW); break;

            //default: viewHolder.rl.setBackgroundColor(Color.WHITE);
        }


        Glide
                .with(context)
                .load(dataList.getItemsList().get(i).getThumbURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //.fitCenter()
                //.centerCrop()
                .into(viewHolder.image);


    }

    @Override
    public int getItemCount() {
        return dataList.getItemsList().size();
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
