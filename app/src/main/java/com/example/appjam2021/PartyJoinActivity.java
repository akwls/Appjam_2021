package com.example.appjam2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appjam2021.network.Api;
import com.example.appjam2021.network.RetrofitClient;
import com.example.appjam2021.party.PartyList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartyJoinActivity extends AppCompatActivity {
    TextView toolbar_title;
    ImageButton btnBack;
    TextView name;
    TextView title;
    TextView price;
    TextView member;
    TextView txtDescription;
    PartyMemberAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_join);
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("파티 가입");
        btnBack = findViewById(R.id.btnBack);
        name = findViewById(R.id.txtName);
        title = findViewById(R.id.txtTitle);
        price = findViewById(R.id.txtPrice);
        member = findViewById(R.id.txtMember);
        txtDescription = findViewById(R.id.txtDescription);

        Api service = RetrofitClient.getClient().create(Api.class);
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

        name.setText("홍길동" +"님의 파티");
        title.setText("어쩌구");
        price.setText("월 " +4900+"원");
        member.setText(2+ "/" + 5);
        txtDescription.setText("어쩌구어쩌구");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new PartyMemberAdapter(5, 2, getApplicationContext());
        recyclerView.setAdapter(adapter);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar_title.setText("파티 가입");

    }
}
