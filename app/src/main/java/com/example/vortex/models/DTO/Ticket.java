package com.example.vortex.models.DTO;

import java.util.Objects;

public class Ticket {
    private int id;
    private Account account;
    private Travel travel;
    private String place;

    public Ticket(int id, Account account, Travel travel, String place) {
        this.id = id;
        this.account = account;
        this.travel = travel;
        this.place = place;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
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

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", account=" + account.getUser().getUsername() +
                ", travel=" + travel.getAmount() +
                ", place='" + place + '\'' +
                '}';
    }
}
