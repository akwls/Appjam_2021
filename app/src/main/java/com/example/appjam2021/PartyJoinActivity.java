package com.example.appjam2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appjam2021.network.Api;
import com.example.appjam2021.network.RetrofitClient;
import com.example.appjam2021.party.PartyJoinData;
import com.example.appjam2021.party.PartyJoinResponse;
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
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_join);
        toolbar_title = findViewById(R.id.toolbar_title);
        btnBack = findViewById(R.id.btnBack);
        name = findViewById(R.id.txtName);
        title = findViewById(R.id.txtTitle);
        price = findViewById(R.id.txtPrice);
        member = findViewById(R.id.txtMember);
        txtDescription = findViewById(R.id.txtDescription);
        final Intent intent = new Intent(this.getIntent());
        name.setText(intent.getStringExtra("name") +"님의 파티");
        title.setText(intent.getStringExtra("title"));
        price.setText("월 " + String.valueOf(intent.getIntExtra("price", 1)) +"원");
        member.setText(intent.getIntExtra("currentMember", 1)+ "/" + intent.getIntExtra("AllMember", 1));
        txtDescription.setText(intent.getStringExtra("content"));

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
        final String room = intent.getStringExtra("name");
        btn = findViewById(R.id.join_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                partyJoin(new PartyJoinData("1", room));
            }
        });
    }

    private void partyJoin(PartyJoinData data){
       Api service = RetrofitClient.getClient().create(Api.class);
        service.partyJoin(data).enqueue(new Callback<PartyJoinResponse>() {
            @Override
            public void onResponse(Call<PartyJoinResponse> call, Response<PartyJoinResponse> response) {
                PartyJoinResponse result = response.body();
                Toast.makeText(PartyJoinActivity.this, result.getMessage(), Toast.LENGTH_LONG).show();
                if (result.getCode() == 200) {
                    //액티비티 종료
                    finish();

                }
            }

            @Override
            public void onFailure(Call<PartyJoinResponse> call, Throwable t) {
                Toast.makeText(PartyJoinActivity.this, "파티 가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("파티 가입 에러 발생", t.getMessage());
                t.printStackTrace();
            }

        });
    }
}
