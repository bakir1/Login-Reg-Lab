package com.example.login.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private int year;

    public User(String username, String password, String name, String surname, int year) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @NonNull
    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", username=" + username + '\'' +
                ", password=" + password + '\'' +
                ", name=" + name + '\'' +
                ", surname=" + surname + '\'' +
                ", birth_year=" + year + '\'' +
                "}";
    }
}
