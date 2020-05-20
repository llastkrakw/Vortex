package com.example.vortex.fakeForUi;

public class TravelListFake {
    private int Image;
    private int RatingImage;
    private String TravelDate;
    private int hr;
    private String depart;
    private String arrival;
    private String classe;
    private double price;
    private boolean IsLike;
    private String name;
    private int number;

    public TravelListFake(int image, int ratingImage, String travelDate, int hr, String depart,
                          String arrival, String classe, double price, boolean isLike, String name, int number) {
        Image = image;
        RatingImage = ratingImage;
        TravelDate = travelDate;
        this.hr = hr;
        this.depart = depart;
        this.arrival = arrival;
        this.classe = classe;
        this.price = price;
        IsLike = isLike;
        this.name = name;
        this.number = number;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public int getRatingImage() {
        return RatingImage;
    }

    public void setRatingImage(int ratingImage) {
        RatingImage = ratingImage;
    }

    public String getTravelDate() {
        return TravelDate;
    }

    public void setTravelDate(String travelDate) {
        TravelDate = travelDate;
    }

    public int getHr() {
        return hr;
    }

    public void setHr(int hr) {
        this.hr = hr;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isLike() {
        return IsLike;
    }

    public void setLike(boolean like) {
        IsLike = like;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
