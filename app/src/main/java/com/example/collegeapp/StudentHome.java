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
                    MaterialCardView ViewNotice = (MaterialCardView) findViewById(R.id.Notice);
                    MaterialCardView ViewAttendance = (MaterialCardView) findViewById(R.id.ViewAttendance);

                    Mark.setOnClickListener(view -> openMark());
                    ViewNotice.setOnClickListener(view -> openNotice());
                    ViewAttendance.setOnClickListener(view -> openAttendance());
                }
                public void openMark() {
                    Intent intent = new Intent(this, viewMark.class);
                    startActivity(intent);
                }
                public void openAttendance() {
                    Intent intent = new Intent(this, ViewAttendance.class);
                    startActivity(intent);
                }
                public void openNotice() {
                    Intent intent = new Intent(this, ViewNotice.class);
                    startActivity(intent);
                }
            }

