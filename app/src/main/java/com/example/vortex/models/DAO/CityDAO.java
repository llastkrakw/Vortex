package com.example.vortex.models.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vortex.models.DCO.City;

import java.util.List;

@Dao
public interface CityDAO {

    @Query("SELECT * FROM City WHERE code = :citycode ")
    LiveData<List<City>> getcity(String citycode);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createcity(City city);

    @Update
    void updatecity(City city);

    @Query("DELETE FROM City WHERE code = :citycode")
    void deletecity(String citycode);
}
