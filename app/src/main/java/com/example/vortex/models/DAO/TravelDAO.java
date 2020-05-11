package com.example.vortex.models.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vortex.models.DTO.Ticket;
import com.example.vortex.models.DCO.Travel;

import java.util.List;

@Dao
public interface TravelDAO {

    @Query("SELECT * FROM Travel WHERE id = :travelid")
    LiveData<List<Travel>> gettravel(int travelid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createtravel(Travel travel);

    @Update
    void updatetravel(Travel travel);

    @Query("DELETE FROM Travel WHERE id = :travelid")
    void deletetravel(int travelid);

}
