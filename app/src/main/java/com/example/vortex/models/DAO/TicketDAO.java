package com.example.vortex.models.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vortex.models.DTO.Agence;
import com.example.vortex.models.DTO.Coupon;
import com.example.vortex.models.DCO.Ticket;

import java.util.List;

@Dao
public interface TicketDAO {

    @Query("SELECT * FROM Ticket WHERE id = :ticketid")
    LiveData<List<Ticket>> getticket(int ticketid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createticket(Ticket ticket);

    @Update
    void updateticket(Ticket ticket);

    @Query("DELETE FROM Ticket WHERE id = :ticketid")
    void deleteticket(int ticketid);
}
