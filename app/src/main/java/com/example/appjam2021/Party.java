package com.example.appjam2021;

public class Party {
    String name;
    String title;
    int AllMember;
    int currentMember;
    int price;
    String id;
    String content;
    int category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String name) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String name) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAllMember() {
        return AllMember;
    }

    public void setAllMember(int allMember) {
        AllMember = allMember;
    }

    public int getCurrentMember() {
        return currentMember;
    }

    public void setCurrentMember(int currentMember) {
        this.currentMember = currentMember;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Party(String name, String title, int allMember, int currentMember, int price, String id, String content, int category) {
        this.name = name;
        this.title = title;
        AllMember = allMember;
        this.currentMember = currentMember;
        this.price = price;
        this.id = id;
        this.content = content;
        this.category = category;
    }
}
