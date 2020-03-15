package com.example.vortex.models.DTO;

import java.util.ArrayList;
import java.util.Objects;

public class Account {
    private int id;
    private User user;
    private ArrayList<Coupon> coupons;
    private ArrayList<Agence> favorite;
    private ArrayList<Ticket> tickets;
    private ArrayList<Travel> travels;
    private ArrayList<Travel> favTravel;
    private int point = 200;

    public Account(int id, User user) {
        this.id = id;
        this.user = user;
        coupons = new ArrayList<Coupon>();
        favorite = new ArrayList<Agence>();
        tickets = new ArrayList<Ticket>();
        travels = new ArrayList<Travel>();
        favTravel = new ArrayList<Travel>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                '}';
    }
}
