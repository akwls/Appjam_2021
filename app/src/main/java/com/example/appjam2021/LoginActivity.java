package com.example.appjam2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appjam2021.network.Api;
import com.example.appjam2021.user.LoginData;
import com.example.appjam2021.user.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public static LoginActivity loginActivity;
    Button btnLogin;
    TextView txtJoin;
    EditText edtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        txtJoin = findViewById(R.id.txtJoin);
        edtEmail = findViewById(R.id.edtEmail);

        txtJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinPasswordActivity.class);
                intent.putExtra("email", edtEmail.getText().toString());
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    void attemptLogin() {
        edtEmail.setError(null);

        String email = edtEmail.getText().toString();
        View focusView = null;

        if(email.isEmpty()) {
            edtEmail.setError("이메일을 입력해주세요.");
            focusView = edtEmail;
            focusView.requestFocus();
        }
        else {
            Intent intent = new Intent(getApplicationContext(), PasswordActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
        }
    }

}
