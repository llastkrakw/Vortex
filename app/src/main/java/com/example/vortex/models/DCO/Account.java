package com.example.vortex.models.DCO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Objects;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "userid"))
public class Account {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "userid")
    private int userid;
    //private ArrayList<Coupon> coupons;
    //private ArrayList<Agence> favorite;
    //private ArrayList<Ticket> tickets;
    //private ArrayList<Travel> travels;
    //private ArrayList<Travel> favTravel;
    //private int point = 200;

    public Account(int id, int user) {
        this.id = id;
        this.userid = user;
        //coupons = new ArrayList<Coupon>();
        //favorite = new ArrayList<Agence>();
        //tickets = new ArrayList<Ticket>();
        //travels = new ArrayList<Travel>();
        //favTravel = new ArrayList<Travel>();
    }

    public Account() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int user) {
        this.userid = user;
    }/*

    public ArrayList<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(ArrayList<Coupon> coupons) {
        this.coupons = coupons;
    }

    public ArrayList<Agence> getFavorite() {
        return favorite;
    }

    public void setFavorite(ArrayList<Agence> favorite) {
        this.favorite = favorite;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public ArrayList<Travel> getTravels() {
        return travels;
    }

    public void setTravels(ArrayList<Travel> travels) {
        this.travels = travels;
    }

    public ArrayList<Travel> getFavTravel() {
        return favTravel;
    }

    public void setFavTravel(ArrayList<Travel> favTravel) {
        this.favTravel = favTravel;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }*/

}
