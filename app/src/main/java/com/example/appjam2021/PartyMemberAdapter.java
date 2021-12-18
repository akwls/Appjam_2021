package com.example.appjam2021;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PartyMemberAdapter extends RecyclerView.Adapter<PartyMemberAdapter.ViewHolder> {
    int allMember;
    int currentMember;
    Integer[] imgId = {R.drawable.icon_laftel, R.drawable.icon_netflix};
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;

        ViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
        }
    }
    public PartyMemberAdapter(int allMember, int currentMember, Context context) {
        this.allMember = allMember;
        this.currentMember = currentMember;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_party_image, parent, false);
        PartyMemberAdapter.ViewHolder vh = new PartyMemberAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        if(position < currentMember) {
            holder.img.setVisibility(View.VISIBLE);
            holder.img.setClipToOutline(true);
            holder.img.setImageResource(imgId[position]);
        }
    }

    @Override
    public int getItemCount() {
        return allMember;
    }
}
