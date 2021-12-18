package com.example.appjam2021;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class MyInfoCreatedParty extends Fragment {
    ListView listview;
    ArrayList<String> title = new ArrayList<>();
    ArrayList<Integer> allMember = new ArrayList<>();
    ArrayList<Integer> currentMember = new ArrayList<>();
    MyInfoPartyCreatedAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.tab_created_party, container, false);
        listview = view.findViewById(R.id.listview);


        title.add("어쩌구1");
        title.add("어쩌구2");

        allMember.add(5);
        allMember.add(4);

        currentMember.add(2);
        currentMember.add(2);

        adapter = new MyInfoPartyCreatedAdapter(getContext(), title, allMember, currentMember);
        listview.setAdapter(adapter);


        return view;
    }
}
