package com.abrar.mid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Database db;
    EditText nameInput, phoneInput, emailInput,skillsInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //INPUTS
        nameInput = findViewById(R.id.nameInput);
        phoneInput = findViewById(R.id.phoneInput);
        emailInput = findViewById(R.id.emailInput);
        skillsInput = findViewById(R.id.skillsInput);
        //DATABASE
        db = new Database(this);
        Log.i("debug","Main");
    }

    public void submit(View view) {
        insertAll();
        openDisplayActivity();
    }

    public void insertAll(){
        String name = nameInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String email = emailInput.getText().toString();
        String skills = skillsInput.getText().toString();

        if(!name.isEmpty() && !phone.isEmpty() && !email.isEmpty() && !skills.isEmpty()) {
            if (db.insert(name, phone, email, skills)) {
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "failed to Insert", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(MainActivity.this, "Fill out all the fields", Toast.LENGTH_LONG).show();
        }
    }

    public void openDisplayActivity(){
        Intent intent = new Intent(this, DisplayActivity.class);
        startActivity(intent);
    }
}