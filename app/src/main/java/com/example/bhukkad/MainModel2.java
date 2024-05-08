package com.example.bhukkad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
public class MainModel2 {
    String name,turl;
    String price;

    MainModel2(){

    }

    public MainModel2(String name, String turl, String price) {
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
