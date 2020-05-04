package com.example.vortex.models;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.vortex.models.DAO.UserDAO;
import com.example.vortex.models.DTO.User;

@Database(entities = {User.class}, version =1, exportSchema = false)
public abstract class SaveDatabase extends RoomDatabase {

    private static volatile SaveDatabase INSTANCE;

    public abstract UserDAO userDAO();

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
