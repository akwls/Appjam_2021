package com.example.appjam2021;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyInfoPartyInfoWishingAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<String> name;
    ArrayList<Integer> imgId;

    public MyInfoPartyInfoWishingAdapter(Context mContext, ArrayList<String> name, ArrayList<Integer> imgId) {
        this.mContext = mContext;
        this.name = name;
        this.imgId = imgId;
    }

    @Override
    public int getCount() {
        return name.size();
    }

    @Override
    public Object getItem(int i) {
        return name.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = View.inflate(mContext, R.layout.item_party_info_wishing, null);
        }
        TextView txtName = view.findViewById(R.id.txtName);
        ImageView imgProfile = view.findViewById(R.id.imgProfile);
        TextView txtAccept = view.findViewById(R.id.txtAccept);
        TextView txtRefuse = view.findViewById(R.id.txtRefuse);

        txtAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("파티 정보", "수락하기");
            }
        });

        txtRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("파티 정보", "거절하기");
            }
        });

        txtName.setText(name.get(i));
        imgProfile.setImageResource(imgId.get(i));


        return view;
    }
}
