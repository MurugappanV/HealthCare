package com.fine_fettle.models;

/**
 * Created by murugappanviswanathan on 11/08/18.
 */

public class PresModel {
    public String p_name;
    public String p_disease;
    public String p_condition;
    public String prescription;
    public String entry_date;

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_disease() {
        return p_disease;
    }

    public void setP_disease(String p_disease) {
        this.p_disease = p_disease;
    }

    public String getP_condition() {
        return p_condition;
    }

    public void setP_condition(String p_condition) {
        this.p_condition = p_condition;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }
}
