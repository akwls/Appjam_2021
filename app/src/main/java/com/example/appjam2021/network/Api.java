package com.example.appjam2021.network;

import com.example.appjam2021.party.PartyJoinData;
import com.example.appjam2021.party.PartyJoinResponse;
import com.example.appjam2021.party.PartyList;
import com.example.appjam2021.user.JoinData;
import com.example.appjam2021.user.JoinInfoData;
import com.example.appjam2021.user.JoinInfoResponse;
import com.example.appjam2021.user.JoinResponse;
import com.example.appjam2021.user.LoginData;
import com.example.appjam2021.user.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    // 회원가입
    @POST("/user/join")
    public Call<JoinResponse> userJoin(@Body JoinData data);
    // 회원가입 - 정보
    @POST("/user/join")
    public Call<JoinInfoResponse> userJoinInfo(@Body JoinInfoData data);
    // 로그인
    @POST("/user/login")
    public Call<LoginResponse> userLogin(@Body LoginData data);

    //파티 목록
    @GET("/list/category/{category}")
    public Call<List<PartyList>> listData(@Path("category") int category);

    // 파티 가입
    @POST("/party-user/applicant")
    public Call<PartyJoinResponse> partyJoin(@Body PartyJoinData data);
}
