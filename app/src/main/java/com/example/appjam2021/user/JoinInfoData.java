package com.example.appjam2021.user;

import com.google.gson.annotations.SerializedName;

public class JoinInfoData {
    @SerializedName("name")
    private String name;
    @SerializedName("account")
    private String account;
    @SerializedName("tel")
    private String tel;


    public JoinInfoData( String name, String account, String tel){
        this.name = name;
        this.account = account;
        this.tel = tel;
    }
}
