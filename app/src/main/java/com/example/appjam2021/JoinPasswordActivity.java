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
import com.example.appjam2021.user.JoinData;
import com.example.appjam2021.user.JoinResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class JoinPasswordActivity extends AppCompatActivity {
    LoginActivity la = null;
    String email;
    public static JoinPasswordActivity joinPasswordActivity;
    ProgressBar mProgressView;
    EditText edtPassword, edtPasswordCheck;
    Button btnNext;
    Api service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_password);
        la = (LoginActivity)LoginActivity.loginActivity;
        if(la != null) la.finish();
        btnNext = findViewById(R.id.btnNext);
        edtPassword = findViewById(R.id.edtPassword);
        mProgressView = findViewById(R.id.progressbar);
        edtPasswordCheck = findViewById(R.id.edtPasswordCheck);
        Intent in = getIntent();
        email = in.getStringExtra("email");
        service = RetrofitClient.getClient().create(Api.class);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptJoin();
            }
        });
    }
    void attemptJoin() {
        edtPassword.setError(null);
        edtPasswordCheck.setError(null);

        String pw = edtPassword.getText().toString();
        String pw_check = edtPasswordCheck.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if(pw.isEmpty()) {
            edtPassword.setError("비밀번호를 입력해주세요");
            focusView = edtPassword;
            cancel = true;
        }
        else if(!pw.equals(pw_check)) {
            edtPasswordCheck.setError("비밀번호가 일치하지 않습니다.");
            focusView = edtPasswordCheck;
            cancel = true;
        }

        if(cancel) {
            focusView.requestFocus();
        }
        else {
            startJoin(new JoinData(email, pw));
            mProgressView.setVisibility(View.VISIBLE);
        }
    }
    void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressView.setVisibility(GONE);

                if(result.getCode() == 200) {
                    Intent intent = new Intent(getApplicationContext(), MoreInfoActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                mProgressView.setVisibility(GONE);
            }
        });
    }
}
