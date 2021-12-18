package com.example.appjam2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.appjam2021.network.Api;
import com.example.appjam2021.network.RetrofitClient;
import com.example.appjam2021.user.JoinInfoData;
import com.example.appjam2021.user.JoinInfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreInfoActivity extends AppCompatActivity {
    Button btnComplete;
    ProgressBar progressBar;
    JoinPasswordActivity pa = null;
    EditText edtName, edtTel, edtAccount;
    Api service;
    public static MoreInfoActivity moreInfoActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        pa = (JoinPasswordActivity) JoinPasswordActivity.joinPasswordActivity;
        if(pa != null) pa.finish();
        btnComplete = findViewById(R.id.btnComplete);
        progressBar = findViewById(R.id.progressbar);
        edtName = findViewById(R.id.edtName);
        edtTel = findViewById(R.id.edtTel);
        edtAccount = findViewById(R.id.edtAccount);
        service = RetrofitClient.getClient().create(Api.class);

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptMoreInfo();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    void attemptMoreInfo() {
        edtName.setError(null);
        edtTel.setError(null);
        edtAccount.setError(null);

        boolean cancel = false;
        View focusView = null;

        String name = edtName.getText().toString();
        String tel = edtTel.getText().toString();
        String account = edtAccount.getText().toString();

        if(name.isEmpty()) {
            edtName.setError("이름을 입력하세요");
            focusView = edtName;
            cancel = true;
        }
        else if(tel.isEmpty()) {
            edtTel.setError("전화번호를 입력하세요");
            focusView = edtTel;
            cancel = true;
        }
        else if(account.isEmpty()) {
            edtAccount.setError("계좌번호를 입력하세요");
            focusView = edtAccount;
            cancel = true;
        }

        if(cancel) {
            focusView.requestFocus();
        }
        else {
            startJoinInfo(new JoinInfoData(name, tel, account));
            progressBar.setVisibility(View.VISIBLE);
        }
    }
    void startJoinInfo(JoinInfoData data) {
        service.userJoinInfo(data).enqueue(new Callback<JoinInfoResponse>() {
            @Override
            public void onResponse(Call<JoinInfoResponse> call, Response<JoinInfoResponse> response) {
                JoinInfoResponse result = response.body();
                Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

                if(result.getCode() == 200) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<JoinInfoResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "추가 정보 입력에 실패했습니다.", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });
    }
}
