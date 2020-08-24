package com.abrar.mid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    TextView name, phone, email;
    ListView skills;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Log.i("debug","Display");
        db = new Database(this);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        skills = findViewById(R.id.skills);
        name.setText(db.getThatOneData("name"));
        phone.setText(db.getThatOneData("phone"));
        email.setText(db.getThatOneData("email"));
        skills.setAdapter(toAdapter(db.getThatOneData("skills")));
    }



    public String[] toArray(String string){
         return string.split(",");
    }
    public ArrayAdapter<String> toAdapter(String string){
        return new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toArray(string));
    }

    public void editName(View view) {
    }
}