package com.example.appjam2021.party;

import com.google.gson.annotations.SerializedName;

public class PostingData {
    @SerializedName("organizer")
    private String organizer;
    @SerializedName("category")
    private int category;
    @SerializedName("title")
    private String title;
    @SerializedName("price")
    private int price;
    @SerializedName("matching_num")
    private int matching_num;
    @SerializedName("content")
    private String content;

    public PostingData( String organizer, String title, int price, int matching_num, int category, String content){
        this.organizer = organizer;
        this.category = category;
        this.title = title;
        this.price = price;
        this.matching_num = matching_num;
        this.content = content;
    }
}
