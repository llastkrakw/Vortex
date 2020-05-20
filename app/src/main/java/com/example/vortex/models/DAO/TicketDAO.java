package com.example.vortex.models.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vortex.models.DTO.Ticket;
import com.example.vortex.models.DTO.User;

import java.util.List;

@Dao
public interface TicketDAO {

    /*@Query("SELECT * FROM Ticket")
    List<Ticket> getAll();

    @Query("SELECT * FROM Ticket where id LIKE  :Id")
    User findById(int Id);

    @Query("SELECT COUNT(*) from Ticket")
    int countTickets();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertTicket(Ticket ticket);

    @Update
    void updateTicket(Ticket ticket);

    @Delete
    void deleteTicket(Ticket ticket);*/

}
