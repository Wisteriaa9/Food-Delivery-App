package com.example.bhukkad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.TokenWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;


import com.onesignal.OSNotification;
import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OSNotificationReceivedEvent;
import com.onesignal.OneSignal;




import com.example.bhukkad.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;

import java.io.IOException;

public class loginActivity extends AppCompatActivity {
    ImageView bgimage;
    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";
    Button login;
    private static final String ONESIGNAL_APP_ID = "3402e9f9-581c-4695-a0d1-c9e7cc18e458";
    TextInputEditText password,email,phone_number,name;

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bgimage=findViewById(R.id.login_image);
        login=(Button)findViewById(R.id.login_button);
        password=(TextInputEditText) findViewById(R.id.password);
        email=(TextInputEditText) findViewById(R.id.email);
        phone_number=(TextInputEditText)findViewById(R.id.phone_number);
        name=(TextInputEditText)findViewById(R.id.name);
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        mAuth = FirebaseAuth.getInstance();



    }
    public void LogIN(View view) {
        //Check if we can log in the user
        password=(TextInputEditText) findViewById(R.id.password);
        email=(TextInputEditText) findViewById(R.id.email);
        phone_number=(TextInputEditText)findViewById(R.id.phone_number);
        name=(TextInputEditText)findViewById(R.id.name);
        String Email = email.getText().toString();
        String Pass = password.getText().toString();
        String Phone = phone_number.getText().toString();
        mAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    sexylogin();
                }  else{

                    mAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(com.example.bhukkad.loginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseApp.initializeApp(com.example.bhukkad.loginActivity.this);
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

                                DatabaseReference mDataBase =FirebaseDatabase.getInstance().getReference().child("users").child(task.getResult().getUser().getUid());

                                mDataBase.child("token").setValue(token);
                                mDataBase.child("Token").setValue(OneSignal.getDeviceState().getUserId().toString());
                                mDataBase.child("uid").setValue(task.getResult().getUser().getUid());
                                mDataBase.child("email").setValue(Email);
                                mDataBase.child("phone").setValue(Phone);



                                Log.d(TAG, "createUserWithEmail:success");



                                FirebaseUser user = mAuth.getCurrentUser();

                                sexylogin();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(com.example.bhukkad.loginActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();

                            }

                        }
                    });

                }
            }

            public void sexylogin() {
                //Move to next Activity
                FirebaseUser user = mAuth.getCurrentUser();
                Intent intent = new Intent(loginActivity.this,ChooseOne.class);
                startActivity(intent);


            }});
}}