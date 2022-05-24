package com.example.collegeapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.bson.Document;

import java.util.ArrayList;

import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;


public class uploadNotice extends AppCompatActivity {

    String appId = "application-0-pbtso";
    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    User user;
    App app;
    MongoCollection<Document> mongoCollection;
    Button uploadBtn;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notice);
        app = new App(new AppConfiguration.Builder(appId).build());

        uploadBtn = (Button) findViewById(R.id.uploadBtn);
        text = (EditText) findViewById(R.id.editTextTextPersonName);

        user = app.currentUser();
        Log.v("AUTH", user + " " + user.getId());
        mongoClient = user.getMongoClient("mongodb-atlas");
        mongoDatabase = mongoClient.getDatabase("CollegeData");
        mongoCollection = mongoDatabase.getCollection("Attendance");

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String notice = text.getText().toString();
                // create a document for find query
                Document queryFilter = new Document("notice", notice);
                // init a mongo collection find task
                RealmResultTask<MongoCursor<Document>> findTask = mongoCollection.find(queryFilter).iterator();
                findTask.getAsync(task -> {
                    if (task.isSuccess()) {
                        MongoCursor<Document> results = task.get();
                        // check if the document already exists
                        if (results.hasNext()) {
                            Log.v("Find", "Found something");
                            Document result = results.next();
                            Toast.makeText(getApplicationContext(), "Already Exists", Toast.LENGTH_LONG).show();
                            // update the result
                            result.append("notice", notice);
                            mongoCollection.updateOne(queryFilter, result).getAsync(updateResult -> {
                                if (updateResult.isSuccess()) {
                                    Log.v("Update", "Update successful");
                                    Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_LONG).show();
                                } else {
                                    Log.v("Update", updateResult.getError().toString());
                                }
                            });
                        }
                        // if not then insert a new document into the mongodb collection
                        else {
                            // insert a document to the collection
                            mongoCollection.insertOne(
                                    new Document("notice", notice)
                            ).getAsync(result -> {
                                if (result.isSuccess()) {
                                    Log.v("Insert", "Insert successful");
                                    Toast.makeText(getApplicationContext(), "Insert Successful", Toast.LENGTH_LONG).show();
                                } else {
                                    Log.v("Insert", result.getError().toString());
                                }
                            });
                        }
                    } else {
                        Log.v("Error", task.getError().toString());
                    }
                });
            }
        });
    }
}