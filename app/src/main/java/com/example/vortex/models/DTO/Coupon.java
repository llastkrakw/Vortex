package com.example.vortex.models.DTO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "Coupon",
        foreignKeys = {
                @ForeignKey(
                        entity = Account.class,
                        parentColumns = "Account ID",
                        childColumns = "Account_ID")})
public class Coupon {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Coupon_ID")
    private int id;

    @ColumnInfo(name = "Expiration_Date")
    private Date dateExp;

    @ColumnInfo(name = "Status")
    private boolean status;

    @ColumnInfo(name = "Amount")
    private double amount;

    @ColumnInfo(name = "Account_ID")
    private int account;

    @ColumnInfo(name = "Method_Pay")
    private String methodPay;

    public Coupon(int id, Date dateExp, boolean status, double amount, int account, String methodPay) {
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


    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
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
