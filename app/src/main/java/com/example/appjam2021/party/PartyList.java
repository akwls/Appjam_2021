package com.example.appjam2021.party;

import com.google.gson.annotations.SerializedName;

public class PartyList {
    @SerializedName("organizer")
    private String organizer;
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("price")
    private int price;
    @SerializedName("matching_num")
    private int matching_num;

    public String getOrganizer() {
        return organizer;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getMatching_num() {
        return matching_num;
    }
}
