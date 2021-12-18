package com.example.appjam2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appjam2021.network.Api;
import com.example.appjam2021.network.RetrofitClient;
import com.example.appjam2021.party.PartyList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    LoginActivity la = null;
    MoreInfoActivity ma = null;
    RecyclerView recyclerView;
    MainCircleAdapter adapter;
    ListView mList;
    MainPartyAdapter partyAdapter;
    ArrayList<Party> partyList;
    Party party;
    TextView txtNewParty, txtMyInfo;
    ArrayList<Integer> imgId = new ArrayList<>();
    private Api service;
    static int k = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        la = (LoginActivity)LoginActivity.loginActivity;
        if(la != null) la.finish();
        ma = (MoreInfoActivity)MoreInfoActivity.moreInfoActivity;
        if(ma != null) ma.finish();
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

        txtNewParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewPartyActivity.class);
                startActivity(intent);
            }
        });

        int category = 0;
        Log.d("myapp", String.valueOf(category));
        getList(category);

    }
    public void getList(int category){
        service = RetrofitClient.getClient().create(Api.class);
        Call<List<PartyList>> call = service.listData(category);
        call.enqueue(new Callback<List<PartyList>>() {
            @Override
            public void onResponse(Call<List<PartyList>> call, Response<List<PartyList>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<PartyList> result = response.body();

                    for (PartyList info : result) {
                        party = new Party(info.getOrganizer(), info.getTitle(), info.getMatching_num(), 2, info.getPrice(), info.getId());

                        partyList.add(party);
                        Log.d("myapp", info.getOrganizer());
                    }
                    Log.d("myapp", "partyList : " + partyList);
                    partyAdapter = new MainPartyAdapter(getApplicationContext(), partyList);
                    mList.setAdapter(partyAdapter);

                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    adapter = new MainCircleAdapter(imgId, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.d("myapp", "question - else err");
                    Log.d("myapp", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<PartyList>> call, Throwable t) {
                Log.d("myapp", "question - Failure error");
                Log.e("myapp", "에러 : " + t.getMessage());
                Toast.makeText(getApplicationContext(), "인터넷 연결이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
