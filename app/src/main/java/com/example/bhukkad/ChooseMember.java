package com.example.bhukkad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ChooseMember extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_member);
        AppCompatButton Customer= findViewById(R.id.Customer);
        AppCompatButton Owner= findViewById(R.id.Owner);
//        Customer.setOnClickListener(this);
//        Owner.setOnClickListener(this);


    }
    public void loginCustomer(View view){
        Intent intent = new Intent(this,loginActivity.class);
        startActivity(intent);
    }
    public void loginOwner(View view){
        Intent intent = new Intent(this,BhawanLoginActivity.class);
        startActivity(intent);
    }


//
//
//    @Override
//    public void onClick(View view) {
//        switch(view.getId()){
//            case R.id.Customer:
//                Intent intent=new Intent(ChooseMember.this,loginActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.Owner:
//                Intent intent1=new Intent(ChooseMember.this,BhawanLoginActivity.class);
//                startActivity(intent1);
//                break;
//
//        }
//
//
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        System.gc();
//    }

}