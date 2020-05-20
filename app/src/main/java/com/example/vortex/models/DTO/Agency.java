package com.example.vortex.models.DTO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity (tableName = "Agency")
public class Agency {
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "Agence_ID")
    private int id;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Image")
    private String image;

    @ColumnInfo(name = "Status")
    private boolean status;

    public Agency(int id, String name, String image, boolean status) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agency agence = (Agency) o;
        return id == agence.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Agence{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
