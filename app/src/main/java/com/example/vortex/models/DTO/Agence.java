package com.example.vortex.models.DTO;

import java.util.Objects;

public class Agence {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agence agence = (Agence) o;
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
