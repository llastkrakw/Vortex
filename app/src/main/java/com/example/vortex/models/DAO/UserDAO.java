package com.example.vortex.models.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vortex.models.DTO.Travel;
import com.example.vortex.models.DCO.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM User WHERE id = :userid")
    LiveData<User> getuser(int userid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createuser(User user);

    @Update
    void updateuser(User user);

    @Query("DELETE FROM User WHERE id = :userid")
    void deleteuser(int userid);

}
