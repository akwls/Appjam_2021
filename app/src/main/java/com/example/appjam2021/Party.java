package com.example.appjam2021;

public class Party {
    String name;
    String title;
    int AllMember;
    int currentMember;
    int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Party(String name, String title, int allMember, int currentMember, int price) {
        this.name = name;
        this.title = title;
        AllMember = allMember;
        this.currentMember = currentMember;
        this.price = price;
    }
}
