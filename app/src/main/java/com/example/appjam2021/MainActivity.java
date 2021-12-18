package com.example.appjam2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LoginActivity la = null;
    RecyclerView recyclerView;
    MainCircleAdapter adapter;
    ListView mList;
    MainPartyAdapter partyAdapter;
    ArrayList<Party> partyList;
    Party party;
    TextView txtNewParty, txtMyInfo;
    ArrayList<Integer> imgId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        la = (LoginActivity)LoginActivity.loginActivity;
        if(la != null) la.finish();
        recyclerView = findViewById(R.id.recyclerView);
        imgId.add(R.drawable.icon_netflix);
        imgId.add(R.drawable.icon_watcha);
        imgId.add(R.drawable.icon_laftel);
        imgId.add(R.drawable.icon_disney);
        imgId.add(R.drawable.icon_wavve);
        mList = findViewById(R.id.list);
        partyList = new ArrayList<>();
        txtNewParty = findViewById(R.id.txtNewParty);
        txtMyInfo = findViewById(R.id.txtMyinfo);

        txtMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyInfoActivity.class);
                startActivity(intent);
            }
        });

        for(int i=0; i<5; i++) {
            party = new Party("홍길동", "넷플릭스 파티원 찾아요!~", 5, 2, 4900);
            partyList.add(party);
        }
        partyAdapter = new MainPartyAdapter(getApplicationContext(), partyList);
        mList.setAdapter(partyAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new MainCircleAdapter(imgId, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}
