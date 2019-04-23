package com.webxert.customenetworkdf.data;

/**
 * Created by hp on 4/22/2019.
 */

public class Geo {
    public String lat ;
    public String lng ;

    public Geo() {
    }

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
