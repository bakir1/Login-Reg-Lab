package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.login.data.UserDao;
import com.example.login.data.UserDatabase;
import com.example.login.model.User;

public class HomeActivity extends AppCompatActivity {

    private TextView userInfo;
    private Button logoutButton, deleteButton;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logoutButton = (Button) findViewById(R.id.logoutButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        userInfo = (TextView) findViewById(R.id.userInfo);

        userDao = Room.databaseBuilder(this, UserDatabase.class, "user.db")
                .allowMainThreadQueries()
                .build()
                .getUserDao();

        Integer id = (Integer) getIntent().getSerializableExtra("id");

        User user = (User) userDao.getUserById(id);

        if (user != null) {
            userInfo.setText("Welcome " + user.getName() + " " + user.getSurname());

            logoutButton.setOnClickListener(view -> {
                toLogin();
            });

            deleteButton.setOnClickListener(view -> {
                userDao.delete(user);
                toLogin();
            });
        }
    }

    private void toLogin() {
        Intent i = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(i);
    }
}