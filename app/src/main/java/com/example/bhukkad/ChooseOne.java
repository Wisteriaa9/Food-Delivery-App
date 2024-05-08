package com.example.bhukkad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class ChooseOne extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);
        Button vk= findViewById(R.id.VigyanKunj);
        Button govind= findViewById(R.id.Govind);
        Button green= findViewById(R.id.GreenGala);
        Button cautley= findViewById(R.id.Cautley);
        govind.setOnClickListener(this);
        cautley.setOnClickListener(this);
        vk.setOnClickListener(this);
        green.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.Cautley:
                Intent intent=new Intent(ChooseOne.this,Student.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Govind:
                Intent intent1=new Intent(ChooseOne.this,Student.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.GreenGala:
                Intent intent2=new Intent(ChooseOne.this,Student.class);
                startActivity(intent2);
                finish();
            case R.id.VigyanKunj:

                Intent intent3=new Intent(ChooseOne.this,Student.class);
                startActivity(intent3);
                finish();
        }
    }
}