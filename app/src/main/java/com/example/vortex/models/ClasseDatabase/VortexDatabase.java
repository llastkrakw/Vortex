package com.example.vortex.models.ClasseDatabase;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.vortex.models.AllConverters.Converters;
import com.example.vortex.models.DAO.AccountDAO;
import com.example.vortex.models.DAO.AgenceDAO;
import com.example.vortex.models.DAO.CityDAO;
import com.example.vortex.models.DAO.CouponDAO;
import com.example.vortex.models.DAO.TicketDAO;
import com.example.vortex.models.DAO.TravelDAO;
import com.example.vortex.models.DAO.UserDAO;
import com.example.vortex.models.DCO.Account;
import com.example.vortex.models.DCO.Agence;
import com.example.vortex.models.DCO.City;
import com.example.vortex.models.DCO.Coupon;
import com.example.vortex.models.DCO.Ticket;
import com.example.vortex.models.DCO.Travel;
import com.example.vortex.models.DCO.User;


@Database(
        entities = {
                Account.class, Agence.class, City.class, Coupon.class,
                Ticket.class, Travel.class, User.class
        }, version = 1, exportSchema = false)@TypeConverters({Converters.class})

public abstract class VortexDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile VortexDatabase INSTANCE;

    // --- DAO ---
    public abstract AccountDAO accountdao();
    public abstract AgenceDAO agencedao();
    public abstract CityDAO citydao();
    public abstract CouponDAO coupondao();
    public abstract TicketDAO ticketdao();
    public abstract TravelDAO traveldao();
    public abstract UserDAO userdao();

    // --- INSTANCE ---
    public static VortexDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (VortexDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            VortexDatabase.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // ---


    private static Callback prepopulateDatabase(){
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase VortexDb) {
                super.onCreate(VortexDb);

                ContentValues contentValues = new ContentValues();
                contentValues.put("id", 1);
                contentValues.put("username", "Manefae8");
                contentValues.put("email", "Manefae8@vortex.com");
                contentValues.put("phone", "685767202");
                contentValues.put("password", "685767202");

                VortexDb.insert("User", OnConflictStrategy.IGNORE, contentValues);
            }
        };
    }
}
