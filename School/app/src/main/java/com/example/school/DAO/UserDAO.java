package com.example.school.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.school.models.User;

import java.util.List;
@Dao
public interface UserDAO
{ @Insert
    void createUser(User user );
    @Delete
    void DeleteUser(User user);
@Update
     void updateUser(User user);
@Query("SELECT * FROM user")
     List <User> getAllUser();
    @Query("SELECT * FROM user WHERE id = :id")
     User getUserById(int id);
}
