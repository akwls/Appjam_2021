package com.example.appjam2021.user;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
