package com.example.vortex.models.DAO;

import androidx.annotation.RequiresPermission;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vortex.models.DCO.Account;

import java.util.List;

@Dao
public interface AccountDAO {

    @Query("SELECT * FROM Account WHERE id = :accountid")
    LiveData<List<Account>> getaccount(int accountid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createaccount(Account account);

    @Update
    void updateaccount(Account account);

    @Query("DELETE FROM Account WHERE id = :accountid")
    void deleteaccount(int accountid);

}
