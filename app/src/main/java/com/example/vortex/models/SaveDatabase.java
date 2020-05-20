package com.example.vortex.models;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.vortex.models.DAO.TicketDAO;
import com.example.vortex.models.DAO.UserDAO;
import com.example.vortex.models.DTO.User;

@Database(entities = {User.class}, version =1, exportSchema = false)
public abstract class SaveDatabase extends RoomDatabase {

    private static volatile SaveDatabase INSTANCE;

    public abstract UserDAO userDao();

    public abstract TicketDAO ticketDAO();

    public static SaveDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), SaveDatabase.class, "Users-DB")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @NonNull

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }


}
