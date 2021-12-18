package com.example.appjam2021;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainCircleAdapter extends RecyclerView.Adapter<MainCircleAdapter.ViewHolder> {
    ArrayList<Integer> imgid;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public LinearLayout linear;

        ViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            linear = itemView.findViewById(R.id.linear);
        }
    }

    public MainCircleAdapter(ArrayList<Integer> imgid, Context context) {
        this.imgid = imgid;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_main_circle ,parent, false);
        MainCircleAdapter.ViewHolder vh = new MainCircleAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        holder.img.setImageResource(imgid.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("main Circle", position+"번째 클릭됐어요~~");
                MainActivity.category = position-1;
                MainActivity.mainActivity.onRefresh();
                // holder.linear.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#C4C4C4")));
            }
        });
    }

    @Override
    public int getItemCount() {
        return imgid.size();
    }
}
