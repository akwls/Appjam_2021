package com.example.appjam2021.user;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public JoinData( String userEmail, String userPwd){
        this.email = userEmail;
        this.password = userPwd;
    }
}
