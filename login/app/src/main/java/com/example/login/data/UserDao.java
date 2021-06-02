package com.example.login.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.login.model.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User WHERE username=:username AND password=:password")
    User getUser(String username, String password);

    @Query("SELECT * FROM User WHERE username=:username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM User WHERE id=:id")
    User getUserById(Integer id);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);
}
