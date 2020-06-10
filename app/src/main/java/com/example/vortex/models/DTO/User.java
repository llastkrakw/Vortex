package com.example.vortex.models.DTO;

import java.util.Objects;

public class User {
    private int id;
    private String username;
    private String email;
    private String phone;
    private String Password;
    private String createAt;


    public User(String username, String email, String phone, String password) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        Password = password;
        this.createAt = createAt;
    }

    public User(String username, String email, String phone, String password, String createAt) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        Password = password;
        this.createAt = createAt;
    }

    public User(int id,String username, String email, String phone, String password, String createAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        Password = password;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
