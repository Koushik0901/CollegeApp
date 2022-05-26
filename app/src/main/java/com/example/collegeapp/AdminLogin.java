package com.example.collegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicReference;

import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;


public class AdminLogin extends AppCompatActivity {
    String appId = "application-0-pbtso";
    EditText reg_input, passwd_input;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reg_input = (EditText) findViewById(R.id.editTextNumber);
        passwd_input = (EditText) findViewById(R.id.editTextDate);
        login = (Button) findViewById(R.id.loginbtn);
        App app = new App(new AppConfiguration.Builder(appId).build());

//        app.getEmailPassword().registerUserAsync("211419104143", "09-11-2001", it -> {
//            if (it.isSuccess()) {
//                Log.v("user", "registration done successfully");
//            }
//            else {
//                Log.v("user", "error registering");
//            }
//        });

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String reg_no = reg_input.getText().toString();
                String passwd = passwd_input.getText().toString();


                Credentials emailPasswordCredentials = Credentials.emailPassword(reg_no, passwd);
                AtomicReference<User> user = new AtomicReference<User>();
                app.loginAsync(emailPasswordCredentials, it -> {
                    if (it.isSuccess()) {
                        Log.v("AUTH", "Successfully authenticated using an register number and DOB.");
                        user.set(app.currentUser());
                        openAdminHome();
                    } else {
                        Log.e("AUTH", it.getError().toString());
                        Log.e("AUTH", it.getError().toString());
                        Toast.makeText(getApplicationContext(),
                                "Please enter valid details!!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
        });
    }

    public void openAdminHome() {
        Intent intent = new Intent(this, AdminHome.class);
        startActivity(intent);
    }
}