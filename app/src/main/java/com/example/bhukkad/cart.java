package com.example.bhukkad;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class cart extends AppCompatActivity {
    public static ArrayList<String>items=new ArrayList<>();
    public static ArrayList<String>prices=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent=getIntent();

        items.add(intent.getStringExtra("item"));
        prices.add(intent.getStringExtra("price"));
        Log.w("price",prices.get(0).toString());
        TextView tV = findViewById(R.id.textView);
        String s="";
        for(int i=0;i<items.size();i++){
            s+=items.get(i)+" ;";
        }
        tV.setText(s);
        TextView tV2 = findViewById(R.id.textView2);
        int k=0;
        for(int i=0;i<prices.size();i++){
            k = k + Integer.parseInt(prices.get(i));
        }

        tV2.setText(Integer.toString(k));



        Button checkout = findViewById(R.id.checkout);
        Button addmore = findViewById(R.id.addmore);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(cart.this,checkout.class);
                startActivity(intent1);

            }
        });
        addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cart.this,Student.class);
                startActivity(intent);
            }
        });


    }
}