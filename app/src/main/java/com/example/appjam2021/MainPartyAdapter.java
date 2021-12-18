package com.example.appjam2021;

import android.content.Context;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainPartyAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Party> party;

    public MainPartyAdapter(Context mContext, ArrayList<Party> party) {
        this.mContext = mContext;
        this.party = party;
    }

    @Override
    public int getCount() {
        return party.size();

    }

    @Override
    public Object getItem(int i) {
        return party.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       if(view == null) {
           view = View.inflate(mContext, R.layout.item_main_party, null);
       }
       PartyMemberAdapter adapter;
        TextView name = view.findViewById(R.id.txtName);
       TextView title = view.findViewById(R.id.txtTitle);
       TextView price = view.findViewById(R.id.txtPrice);
        TextView member = view.findViewById(R.id.txtMember);

        name.setText(party.get(i).name +"님의 파티");
        title.setText(party.get(i).title);
        price.setText("월 " +party.get(i).price+"원");
        member.setText(party.get(i).currentMember + "/" + party.get(i).AllMember);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new PartyMemberAdapter(party.get(i).AllMember, party.get(i).currentMember, view.getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
}