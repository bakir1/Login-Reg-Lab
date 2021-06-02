package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.data.UserDao;
import com.example.login.data.UserDatabase;
import com.example.login.model.User;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button loginButton, registerButton;
    UserDao db;
    UserDatabase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.loginUsername);
        password = (EditText) findViewById(R.id.loginPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);

        dataBase = Room.databaseBuilder(this, UserDatabase.class, "user.db")
                .allowMainThreadQueries()
                .build();

        db = dataBase.getUserDao();


        registerButton.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(i);
        });

        loginButton.setOnClickListener(view -> {
            String usernameText = username.getText().toString().trim();
            String passwordText = password.getText().toString().trim();

            User user = db.getUser(usernameText, passwordText);

            if (user != null) {
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                i.putExtra("id", user.getId());
                startActivity(i);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Info is not correct", Toast.LENGTH_SHORT).show();
            }
        });
    }
}