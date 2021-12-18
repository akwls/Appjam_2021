package com.example.appjam2021;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
    public View getView(final int i, View view, final ViewGroup viewGroup) {
       if(view == null) {
           view = View.inflate(mContext, R.layout.item_main_party, null);
       }
       PartyMemberAdapter adapter;
        final TextView name = view.findViewById(R.id.txtName);
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

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PartyJoinActivity.class);
                intent.putExtra("name", party.get(i).name);
                intent.putExtra("title", party.get(i).title);
                intent.putExtra("price", party.get(i).price);
                intent.putExtra("currentMember", party.get(i).currentMember);
                intent.putExtra("AllMember", party.get(i).AllMember);
                intent.putExtra("content", party.get(i).content);
                viewGroup.getContext().startActivity(intent);
            }
        });

        return view;
    }
}
