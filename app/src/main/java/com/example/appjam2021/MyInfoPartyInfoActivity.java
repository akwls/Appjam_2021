package com.example.appjam2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyInfoPartyInfoActivity extends AppCompatActivity {
    ListView joining, wishing;
    ArrayList<String> joining_name = new ArrayList<>();
    ArrayList<String> wishing_name = new ArrayList<>();
    ArrayList<Integer> joining_imgId = new ArrayList<>();
    ArrayList<Integer> wishing_imgId = new ArrayList<>();
    MyInfoPartyInfoJoiningAdapter joining_adapter;
    MyInfoPartyInfoWishingAdapter wishing_adapter;
    TextView toolbar_title;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_party_info);
        joining = findViewById(R.id.joining);
        wishing = findViewById(R.id.wishing);
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("파티 정보");
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        joining_name.add("김정우");
        joining_name.add("홍길동");
        joining_imgId.add(R.drawable.icon_disney);
        joining_imgId.add(R.drawable.icon_netflix);

        wishing_name.add("김정우");
        wishing_name.add("홍길동");
        wishing_imgId.add(R.drawable.icon_disney);
        wishing_imgId.add(R.drawable.icon_netflix);

        joining_adapter = new MyInfoPartyInfoJoiningAdapter(getApplicationContext(), joining_name, joining_imgId);
        joining.setAdapter(joining_adapter);

        wishing_adapter = new MyInfoPartyInfoWishingAdapter(getApplicationContext(), wishing_name, wishing_imgId);
        wishing.setAdapter(wishing_adapter);


    }
}
