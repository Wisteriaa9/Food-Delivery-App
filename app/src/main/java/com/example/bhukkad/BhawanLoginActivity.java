package com.example.bhukkad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.onesignal.OneSignal;

public class BhawanLoginActivity extends AppCompatActivity {
    AppCompatButton blogin;
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    TextInputEditText bpassword,bname;
    private static final String ONESIGNAL_APP_ID = "3402e9f9-581c-4695-a0d1-c9e7cc18e458";
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhawan_login);
        bname = (TextInputEditText) findViewById(R.id.Bhawan);
        bpassword = (TextInputEditText) findViewById(R.id.bhawan_password);
        blogin = (AppCompatButton) findViewById(R.id.bhawan_login);
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        mAuth = FirebaseAuth.getInstance();}
        public void login(View view) {
            //Check if we can log in the user
            bname = (TextInputEditText) findViewById(R.id.Bhawan);
            bpassword = (TextInputEditText) findViewById(R.id.bhawan_password);
            blogin = (AppCompatButton) findViewById(R.id.bhawan_login);
            String Name= bname.getText().toString();
            String Pass = bpassword.getText().toString();
            String Email = Name+"@bhawan.com";

            mAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        sexylogin();
                    }  else{

                        mAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(com.example.bhukkad.BhawanLoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseApp.initializeApp(com.example.bhukkad.BhawanLoginActivity.this);
                                    FirebaseMessaging.getInstance().getToken()
                                            .addOnCompleteListener(new OnCompleteListener<String>() {
                                                @Override
                                                public void onComplete(@NonNull Task<String> task) {
                                                    if (!task.isSuccessful()) {
                                                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());

                                                    }

                                                    // Get new FCM registration token
                                                    token = task.getResult();

                                                    // Log and toast


                                                }
                                            });
                                    // Sign in success, update UI with the signed-in user's information

                                    DatabaseReference mDataBase = FirebaseDatabase.getInstance().getReference().child("users").child(task.getResult().getUser().getUid());

//                                    mDataBase.child("token").setValue(token);
                                    mDataBase.child("Token").setValue(OneSignal.getDeviceState().getUserId().toString());
                                    mDataBase.child("uid").setValue(task.getResult().getUser().getUid());
                                    mDataBase.child("name").setValue(Name);




                                    Log.d(TAG, "createUserWithEmail:success");



                                    FirebaseUser user = mAuth.getCurrentUser();

                                    sexylogin();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(com.example.bhukkad.BhawanLoginActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();

                                }

                            }
                        });

                    }
                }

                public void sexylogin() {
                    //Move to next Activity
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent intent = new Intent(BhawanLoginActivity.this,Menu.class);
                    startActivity(intent);


                }});
        }}



