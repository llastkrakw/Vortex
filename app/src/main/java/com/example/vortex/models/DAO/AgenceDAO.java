package com.example.vortex.models.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vortex.models.DCO.Agence;

import java.util.List;

@Dao
public interface AgenceDAO {

    @Query("SELECT * FROM Agence WHERE id = :agenceid")
    LiveData<List<Agence>> getagence(int agenceid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createagence(Agence agence);

    @Update
    void updateagence(Agence agence);

    @Query("DELETE FROM Agence WHERE id = :agenceid")
    void deleteagence(int agenceid);
}
