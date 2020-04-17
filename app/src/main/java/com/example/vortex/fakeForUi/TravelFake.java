package com.example.vortex.fakeForUi;

public class TravelFake {
    private int Image;
    private int RatingImage;
    private String TravelDate;
    private int hr;
    private String depart;
    private String arrival;

    public TravelFake(int image, int ratingImage, String travelDate, int hr, String depart, String arrival) {
        Image = image;
        RatingImage = ratingImage;
        TravelDate = travelDate;
        this.hr = hr;
        this.depart = depart;
        this.arrival = arrival;
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
}
