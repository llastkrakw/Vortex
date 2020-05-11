package com.example.vortex.models.DCO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Agence {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String image;
    private boolean status;

    public Agence(int id, String name, String image, boolean status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
