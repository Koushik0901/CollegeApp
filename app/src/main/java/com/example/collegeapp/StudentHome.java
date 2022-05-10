package com.example.collegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class StudentHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        MaterialCardView Mark = (MaterialCardView) findViewById(R.id.Mark);

        Mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMark();
            }
        });
    }
    public void openMark() {
        Intent intent = new Intent(this, viewMark.class);
        startActivity(intent);
    }
}