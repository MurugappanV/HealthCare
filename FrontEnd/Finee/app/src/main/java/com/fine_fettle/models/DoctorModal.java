package com.fine_fettle.models;

/**
 * Created by murugappanviswanathan on 06/08/18.
 */

public class DoctorModal {

    public String id;
    public String name;
    public String gender;
    public String hospital_name;
    public String specialization;
    public String mobile_number;
    public String rating;
    public String degree;
    public String exp;
    public String img;

    public DoctorModal() {
    }

    public DoctorModal(String name, String gender, String hospital_name, String specialization, String mobile_number) {
        this.name = name;
        this.gender = gender;
        this.hospital_name = hospital_name;
        this.specialization = specialization;
        this.mobile_number = mobile_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
