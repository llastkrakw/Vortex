package com.example.vortex.models.DTO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "City")
public class City {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "City_ID")
    private int Id;

    @ColumnInfo(name = "Code")
    private String code;

    @ColumnInfo(name = "Name")
    private String name;

    public City(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(code, city.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "City{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
