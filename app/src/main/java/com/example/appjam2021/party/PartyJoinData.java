package com.example.appjam2021.party;

import com.google.gson.annotations.SerializedName;

public class PartyJoinData {
    @SerializedName("member_id")
    private String member_id;
    @SerializedName("room")
    private String room;

    public PartyJoinData( String member_id, String room){
        this.member_id = member_id;
        this.room = room;
    }
}
