package com.example.vortex.models.DTO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity (tableName = "User",
            indices ={ @Index(name="Mobile_Number",
                              value = "Phone", unique = true),
                        @Index(value = "Email", unique = true),
                        @Index(value = "UserName", unique = true)
            })
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "UserName")
    private String Username;

    @ColumnInfo(name = "Email")
    private String Email;

    @ColumnInfo(name = "Code")
    private String Code;

    @ColumnInfo(name = "Phone")
    private String Phone;

    @ColumnInfo(name = "Password")
    private String Password;

    @ColumnInfo(name = "CoPassword")
    private String CoPassword;

    public User( String phone,String username, String email, String password) {

        this.Phone = phone;
        this.Username = username;
        this.Email = email;
        Password = password;
    }

    public User(String username, String email,String code, String phone, String password) {
        this.Username = username;
        this.Email = email;
        this.Code=code;
        this.Phone = phone;
        Password = password;
    }
    public User(String username, String email,String code, String phone, String password,  String copassword) {
        this.Username = username;
        this.Email = email;
        this.Code=code;
        this.Phone = phone;
        Password = password;
        CoPassword = copassword;
    }

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getCoPassword() {
        return CoPassword;
    }

    public void setCoPassword(String coPassword) {
        CoPassword = coPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(Email, user.Email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Email);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + Username + '\'' +
                ", email='" + Email + '\'' +
                ", phone='" + Phone + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
