package com.fine_fettle.models;

import java.io.Serializable;

/**
 * Created by murugappanviswanathan on 05/08/18.
 */

public class HospitalModel  implements Serializable{
    public String hospital_name;
    public String hospital_address;
    public String hospital_city;
    public String hospital_pincode;
    public String rating;
    public String longg;
    public String lat;
    public String img;
    public String dist;


    public HospitalModel() {
    }

    public HospitalModel(String hospital_name, String hospital_address, String hospital_city, String hospital_pincode) {
        this.hospital_name = hospital_name;
        this.hospital_address = hospital_address;
        this.hospital_city = hospital_city;
        this.hospital_pincode = hospital_pincode;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getHospital_address() {
        return hospital_address;
    }

    public void setHospital_address(String hospital_address) {
        this.hospital_address = hospital_address;
    }

    public String getHospital_city() {
        return hospital_city;
    }

    public void setHospital_city(String hospital_city) {
        this.hospital_city = hospital_city;
    }

    public String getHospital_pincode() {
        return hospital_pincode;
    }

    public void setHospital_pincode(String hospital_pincode) {
        this.hospital_pincode = hospital_pincode;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLongg() {
        return longg;
    }

    public void setLongg(String longg) {
        this.longg = longg;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }
}
