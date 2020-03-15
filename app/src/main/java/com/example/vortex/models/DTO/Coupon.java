package com.example.vortex.models.DTO;

import java.util.Date;
import java.util.Objects;

public class Coupon {
    private int id;
    private Date dateExp;
    private boolean status;
    private double amount;
    private Account account;
    private String methodPay;

    public Coupon(int id, Date dateExp, boolean status, double amount, Account account, String methodPay) {
        this.id = id;
        this.dateExp = dateExp;
        this.status = status;
        this.amount = amount;
        this.account = account;
        this.methodPay = methodPay;
    }

    public Date getDateExp() {
        return dateExp;
    }

    public void setDateExp(Date dateExp) {
        this.dateExp = dateExp;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getMethodPay() {
        return methodPay;
    }

    public void setMethodPay(String methodPay) {
        this.methodPay = methodPay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return id == coupon.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", dateExp=" + dateExp +
                ", amount=" + amount +
                '}';
    }
}
