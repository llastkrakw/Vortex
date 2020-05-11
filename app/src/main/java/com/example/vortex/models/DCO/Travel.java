package com.example.vortex.models.DCO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;

@Entity(foreignKeys = {@ForeignKey(entity = Agence.class, parentColumns = "id", childColumns = "agenceid") } )
public class Travel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "agenceid")
    private int agenceid;
    private boolean isFinish;
    private String status;
    private String cityDepart;
    private String cityArrival;
    private Date departureDate;
    private String departHr;
    private String arrivalHr;
    private int duration;
    private String travelclass;
    private double amount;
    private int nbPlaces;
    private int nbPlacesRest;
    private int loveItems = 0;

    public Travel(int id, int agence, boolean isFinish, String status, String cityDepart,
                  String cityArrival, Date departureDate, String departHr, String arrivalHr,
                  int duration, String travelclass, double amount, int nbPlaces, int nbPlacesRest) {

        this.id = id;
        this.agenceid = agence;
        this.isFinish = isFinish;
        this.status = status;
        this.cityDepart = cityDepart;
        this.cityArrival = cityArrival;
        this.departureDate = departureDate;
        this.departHr = departHr;
        this.arrivalHr = arrivalHr;
        this.duration = duration;
        //this.travelclass = travelclass;
        this.amount = amount;
        this.nbPlaces = nbPlaces;
        this.nbPlacesRest = nbPlacesRest;

    }

    public Travel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgenceid() {
        return agenceid;
    }

    public void setAgenceid(int agence) {
        this.agenceid = agence;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCityDepart() {
        return cityDepart;
    }

    public void setCityDepart(String cityDepart) {
        this.cityDepart = cityDepart;
    }

    public String getCityArrival() {
        return cityArrival;
    }

    public void setCityArrival(String cityArrival) {
        this.cityArrival = cityArrival;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartHr() {
        return departHr;
    }

    public void setDepartHr(String departHr) {
        this.departHr = departHr;
    }

    public String getArrivalHr() {
        return arrivalHr;
    }

    public void setArrivalHr(String arrivalHr) {
        this.arrivalHr = arrivalHr;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTravelclass() {
        return travelclass;
    }

    public void setTravelclass(String travelclass) {
        this.travelclass = travelclass;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public int getNbPlacesRest() {
        return nbPlacesRest;
    }

    public void setNbPlacesRest(int nbPlacesRest) {
        this.nbPlacesRest = nbPlacesRest;
    }

    public int getLoveItems() {
        return loveItems;
    }

    public void setLoveItems(int loveItems) {
        this.loveItems = loveItems;
    }

}
