package com.example.vortex.models.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vortex.models.DCO.Coupon;

import java.util.List;

@Dao
public interface CouponDAO {

    @Query("SELECT * FROM Coupon WHERE id = :couponid")
    LiveData<List<Coupon>> getcoupon(int couponid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createcoupon(Coupon coupon);

    @Update
    void updatecoupon(Coupon coupon);

    @Query("DELETE FROM Coupon WHERE id = :couponid")
    void deletecoupon(int couponid);
}
