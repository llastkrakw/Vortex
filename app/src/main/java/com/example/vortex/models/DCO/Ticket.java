package com.example.vortex.models.DCO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(foreignKeys = {@ForeignKey(entity = Account.class, parentColumns = "id", childColumns = "accountid"), @ForeignKey(entity = Travel.class, parentColumns = "id", childColumns = "travelid")} )
public class Ticket {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "accountid")
    private int accountid;

    @ColumnInfo(name = "travelid")
    private int travelid;

    private String place;

    public Ticket(int id, int account, int travel, String place) {
        this.id = id;
        this.accountid = account;
        this.travelid = travel;
        this.place = place;
    }

    public Ticket() {

    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int account) {
        this.accountid = account;
    }

    public int getTravelid() {
        return travelid;
    }

    public void setTravelid(int travel) {
        this.travelid = travel;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
