package com.example.vortex.models.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vortex.models.DTO.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM User")
    List<User> readAllUser();

    @Query("SELECT * FROM User where email LIKE  :email AND password LIKE :password")
    User findByName(String email, String password);
    //List<User> findByName(String email, String password);

    @Query("SELECT * FROM User where email LIKE  :Email")
    User findByEmail(String Email);
    //List<User> findByEmail(String Email);

    @Query("SELECT COUNT(*) from User")
    int countUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertUser(User user);

    @Update
    int updateUser(User user);

    @Delete
    int deleteUser(User user);


}
