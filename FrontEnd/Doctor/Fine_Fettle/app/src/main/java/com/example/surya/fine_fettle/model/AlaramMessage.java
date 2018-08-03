package com.example.surya.fine_fettle.model;


public class AlaramMessage {

    String title, message, date;


    public AlaramMessage(String mtitle, String mMessage, String mDate) {
        this.title = mtitle;
        this.message = mMessage;
        this.date = mDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
