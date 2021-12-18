package com.example.appjam2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appjam2021.network.Api;
import com.example.appjam2021.network.RetrofitClient;
import com.example.appjam2021.party.PostingData;
import com.example.appjam2021.party.PostingResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPartyActivity extends AppCompatActivity {
    ImageButton btnBack;
    TextView toolbar_title;
    String inputTitle;
    int inputPrice;
    int inputMemNum;
    int inputCategory;
    String inputContent;
    EditText viewTitle;
    EditText viewPrice;
    EditText viewMemNum;
    Spinner viewCategory;
    EditText viewContent;
    String[] items = {"넷플릭스", "왓챠", "어도비", "디즈니+", "MS365"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_party);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("파티 생성");

        viewTitle = findViewById(R.id.title);
        viewPrice = findViewById(R.id.price);
        viewMemNum = findViewById(R.id.mem_num);
        viewCategory = findViewById(R.id.category);
        viewContent = findViewById(R.id.content);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewCategory.setAdapter(adapter);

        viewCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("category", String.valueOf(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

    }

    private void getData(){
        inputTitle = viewTitle.getText().toString();
        inputPrice = Integer.valueOf(viewPrice.getText().toString());
        inputMemNum = Integer.valueOf(viewMemNum.getText().toString());
        inputCategory = Integer.valueOf(viewCategory.getSelectedItem().toString());
        inputContent = viewContent.getText().toString();
        Log.d("myapp", inputTitle);
        createParty(new PostingData("j", inputTitle, inputPrice, inputMemNum, inputCategory, inputContent));
    }

    private void createParty(PostingData data) {
        Api service = RetrofitClient.getClient().create(Api.class);
        service.postingData(data).enqueue(new Callback<PostingResponse>() {
            @Override
            public void onResponse(Call<PostingResponse> call, Response<PostingResponse> response) {
                PostingResponse result = response.body();
                Toast.makeText(NewPartyActivity.this, result.getMessage(), Toast.LENGTH_LONG).show();
                if (result.getCode() == 200) {
                    //액티비티 종료
                    Log.d("myapp", result.getMessage());
                    finish();

                }
            }

            @Override
            public void onFailure(Call<PostingResponse> call, Throwable t) {
                Toast.makeText(NewPartyActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("파티 생성 에러 발생", t.getMessage());
                t.printStackTrace();
            }

        });
    }
}
