package com.example.appjam2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

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
        btnBack = findViewById(R.id.btnBack);
        name = findViewById(R.id.txtName);
        title = findViewById(R.id.txtTitle);
        price = findViewById(R.id.txtPrice);
        member = findViewById(R.id.txtMember);
        txtDescription = findViewById(R.id.txtDescription);

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
