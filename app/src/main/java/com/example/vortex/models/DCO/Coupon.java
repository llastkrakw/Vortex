package com.example.vortex.models.DCO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;


@Entity(foreignKeys = @ForeignKey(entity = Account.class,
        parentColumns = "id",
        childColumns = "accountid"))
public class Coupon {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private Date dateExp;

    private boolean status;

    private double amount;

    @ColumnInfo(name = "accountid")
    private int accountid;

    private String methodPay;

    public Coupon(int id, Date dateExp, boolean status, double amount, int account, String methodPay) {
        this.id = id;
        this.dateExp = dateExp;
        this.status = status;
        this.amount = amount;
        this.accountid = account;
        this.methodPay = methodPay;
    }

    public Coupon() {

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


    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int account) {
        this.accountid = account;
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

}
