package com.example.bhukkad;

public class MainModel {
    String name,turl;
    String price;

    MainModel(){

    }

    public MainModel(String name, String turl, String price) {
        this.name = name;
        this.turl = turl;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTurl() {
        return turl;
    }

    public void setTurl(String turl) {
        this.turl = turl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
