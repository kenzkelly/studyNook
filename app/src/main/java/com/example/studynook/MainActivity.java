package com.example.studynook;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.provider.FirebaseInitProvider;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button log_in;
    Button create_account;

    EditText username;
    EditText password;

    UserOpenHelper helper;
    FirebaseDatabase firebase;
    DatabaseReference dbr;
        DatabaseReference userDB;

    ActivityResultLauncher<Intent> launcher;

    static final String TAG = "MainActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sn_entrance);

        FirebaseApp.initializeApp(this);
        firebase = FirebaseDatabase.getInstance();
        dbr = firebase.getReference();
        userDB = dbr.child("users");

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>(){

                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Log.d(TAG, "onActivityResult: ");
                        ActivityResult r = result;
                        String usernameStr = r.getData().getStringExtra("username").toString();
                        String passwordStr = r.getData().getStringExtra("password").toString();
                        User user = new User(usernameStr, passwordStr);
                        DatabaseReference newUserDB = userDB.push();
                        newUserDB.setValue(user);
                    }
                });
        log_in = findViewById(R.id.logIn);
        create_account = findViewById((R.id.createAccount));

        helper = new UserOpenHelper(this);

        create_account.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick: create_account Button");
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                launcher.launch(intent);
            }
        });

//        userDatabase.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        })

    }

}