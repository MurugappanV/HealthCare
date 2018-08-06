package com.tinmegali.mylocation;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 9DOTS04 on 3/1/2017.
 */

public class latlng {
    @SerializedName("status")
    private int status_code;
    @SerializedName("address")
    private String address;
    @SerializedName("latitude")
    private String lat;
    @SerializedName("longitude")
    private String lng;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}

