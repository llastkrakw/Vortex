package com.example.vortex.models.DTO;

import java.util.Date;
import java.util.Objects;

public class Travel {
    private int id;
    private Agence agence;
    private boolean isFinish;

    private enum travelStatus{
        finish,
        ready,
        wait
    }

    private enum classes{
        Vip,
        classic
    }

    private travelStatus status;
    private City cityDepart;
    private City cityArrival;
    private Date departureDate;
    private String departHr;
    private String arrivalHr;
    private int duration;
    private classes travelclass;
    private double amount;
    private int nbPlaces;
    private int nbPlacesRest;
    private int loveItems = 0;

    public Travel(int id, Agence agence, boolean isFinish, travelStatus status, City cityDepart,
                  City cityArrival, Date departureDate, String departHr, String arrivalHr,
                  int duration, classes travelclass, double amount, int nbPlaces, int nbPlacesRest) {

        this.id = id;
        this.agence = agence;
        this.isFinish = isFinish;
        this.status = status;
        this.cityDepart = cityDepart;
        this.cityArrival = cityArrival;
        this.departureDate = departureDate;
        this.departHr = departHr;
        this.arrivalHr = arrivalHr;
        this.duration = duration;
        this.travelclass = travelclass;
        this.amount = amount;
        this.nbPlaces = nbPlaces;
        this.nbPlacesRest = nbPlacesRest;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public travelStatus getStatus() {
        return status;
    }

    public void setStatus(travelStatus status) {
        this.status = status;
    }

    public City getCityDepart() {
        return cityDepart;
    }

    public void setCityDepart(City cityDepart) {
        this.cityDepart = cityDepart;
    }

    public City getCityArrival() {
        return cityArrival;
    }

    public void setCityArrival(City cityArrival) {
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

    public classes getTravelclass() {
        return travelclass;
    }

    public void setTravelclass(classes travelclass) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travel travel = (Travel) o;
        return id == travel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Travel{" +
                "id=" + id +
                ", agence=" + agence +
                ", status=" + status +
                ", cityDepart=" + cityDepart +
                ", cityArrival=" + cityArrival +
                ", departureDate=" + departureDate +
                ", travelclass=" + travelclass +
                ", amount=" + amount +
                ", nbPlaces=" + nbPlaces +
                ", nbPlacesRest=" + nbPlacesRest +
                '}';
    }
}
