package com.example.vortex.models.DTO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity (tableName = "Ticket",
        foreignKeys = {
                @ForeignKey(
                        entity = Account.class,
                        parentColumns = "Account ID",
                        childColumns = "Account_ID"),
                @ForeignKey(
                        entity = Travel.class,
                        parentColumns = "Travel ID",
                        childColumns = "Travel_ID") })

public class Ticket {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Ticket_ID")
    private int id;

    @ColumnInfo(name = "Account_ID")
    private int account;

    @ColumnInfo(name = "Travel_ID")
    private int travel;

    @ColumnInfo(name = "Place")
    private String place;

    public Ticket(int id, int account, int travel, String place) {
        this.id = id;
        this.account = account;
        this.travel = travel;
        this.place = place;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getTravel() {
        return travel;
    }

    public void setTravel(int travel) {
        this.travel = travel;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                Objects.equals(account, ticket.account) &&
                Objects.equals(travel, ticket.travel) &&
                Objects.equals(place, ticket.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /*@Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", account=" + account.getUser().getUsername() +
                ", travel=" + travel.getAmount() +
                ", place='" + place + '\'' +
                '}';
    }*/
}
