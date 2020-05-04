package com.example.vortex.models.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.example.vortex.models.DTO.User;

@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);


}
