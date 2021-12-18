package com.example.appjam2021;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyInfoPartyCreatedAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<String> title;
    ArrayList<Integer> allMember;
    ArrayList<Integer> currentMember;

    public MyInfoPartyCreatedAdapter(Context mContext, ArrayList<String> title, ArrayList<Integer> allMember, ArrayList<Integer> currentMember) {
        this.mContext = mContext;
        this.title = title;
        this.allMember = allMember;
        this.currentMember = currentMember;
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int i) {
        return title.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = View.inflate(mContext, R.layout.item_myinfo_party, null);
        }
        PartyMemberAdapter adapter;
        TextView txtTitle = view.findViewById(R.id.txtTitle);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new PartyMemberAdapter(allMember.get(i), currentMember.get(i), view.getContext());
        recyclerView.setAdapter(adapter);

        txtTitle.setText(title.get(i));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MyInfoPartyInfoActivity.class);
                view.getContext().startActivity(intent);
            }
        });


        return view;
    }
}
