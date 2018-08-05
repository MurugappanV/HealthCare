package com.fine_fettle.models;

/**
 * Created by murugappanviswanathan on 06/08/18.
 */

public class DoctorModal {
    public String name;
    public String gender;
    public String hospital_name;
    public String specialization;
    public String mobile_number;

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
}
