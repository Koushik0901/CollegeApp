package com.example.collegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class AdminLogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // taking FirebaseAuth instance
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
    }
}