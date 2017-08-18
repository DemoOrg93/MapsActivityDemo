package com.sonika.project_who.pojo;

/**
 * Created by sonika on 8/11/2017.
 */

public class HospitalsPojo {

    private Double latitude;
    private Double longitude;
    private String name;

    public HospitalsPojo(Double latitude, Double longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public HospitalsPojo() {
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}