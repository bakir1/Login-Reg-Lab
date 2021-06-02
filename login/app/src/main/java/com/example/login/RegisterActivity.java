package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.login.data.UserDao;
import com.example.login.data.UserDatabase;
import com.example.login.model.User;
import com.google.android.material.button.MaterialButton;

public class RegisterActivity extends AppCompatActivity {

    EditText name, surname, year, username, password;
    Button registerButton;
    ImageView goBack;

    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        year = (EditText) findViewById(R.id.birth_year);

        registerButton = (Button) findViewById(R.id.registerButton);
        goBack = (ImageView) findViewById(R.id.go_back);

        userDao = Room.databaseBuilder(this, UserDatabase.class, "user.db")
                .allowMainThreadQueries()
                .build()
                .getUserDao();

        goBack.setOnClickListener(view -> {
            toLogin();
        });

        registerButton.setOnClickListener(view -> {
            String usernameText = username.getText().toString().trim();
            String passwordText = password.getText().toString().trim();
            String nameText = name.getText().toString().trim();
            String surnameText = surname.getText().toString().trim();
            Integer birthYear = Integer.parseInt(year.getText().toString());

            if (usernameText.length() == 0 || passwordText.length() == 0) {
                Toast.makeText(RegisterActivity.this, "Username and password are required", Toast.LENGTH_SHORT).show();
                return;
            }

            if(nameText.length() == 0) {
                nameText = "John";
            }

            if(surnameText.length() == 0) {
                nameText = "Doe";
            }

            if(birthYear < 1900) {
                birthYear = 1900;
            }

            User existing = userDao.getUserByUsername(usernameText);

            if (existing == null) {
                User user = new User(usernameText, passwordText, nameText, surnameText, birthYear);
                userDao.insert(user);
                toLogin();
            } else {
                Toast.makeText(RegisterActivity.this, "User "+usernameText+" already exist in db.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void toLogin() {
        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(i);
    }
}