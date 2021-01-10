package com.example.binus_ezyfoody.EzFood;

public class Store {
    private String StoreName;
    private double latitude;
    private double longitude;

    public Store(String name, double latitude, double longitude){
        this.StoreName = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getStoreName() {
        return StoreName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
