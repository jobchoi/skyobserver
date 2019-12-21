package com.example.skyobserver.msmap;

import java.io.Serializable;

public class MStation implements Serializable {


    private String addr;
    // private String map;
    // private String oper;
    //private String photo;
    private String stationName;
    private String dmX;
    private String dmY;
    private String pm10value;
    private String pm25value;


/*

    private String  pm10Value ;
    private String  pm10Value24;
    private String  pm25Value;
    private String  pm25Value24 ;
    private String  pm10Grade;
    private String  pm25Grade;
    */

    private String  pm10Grade1h;
    private String pm25Grade1h ;

    public String getPm10Grade1h() {
        return pm10Grade1h;
    }

    public void setPm10Grade1h(String pm10Grade1h) {
        this.pm10Grade1h = pm10Grade1h;
    }

    public String getPm25Grade1h() {
        return pm25Grade1h;
    }

    public void setPm25Grade1h(String pm25Grade1h) {
        this.pm25Grade1h = pm25Grade1h;
    }

    public MStation() {
    }

    public MStation(String addr, String stationName, String dmX, String dmY, String pm10value, String pm25value, String pm10Grade1h, String pm25Grade1h) {
        this.addr = addr;
        this.stationName = stationName;
        this.dmX = dmX;
        this.dmY = dmY;
        this.pm10value = pm10value;
        this.pm25value = pm25value;
        this.pm10Grade1h = pm10Grade1h;
        this.pm25Grade1h = pm25Grade1h;
    }

    public String getPm10value() {
        return pm10value;
    }

    public void setPm10value(String pm10value) {
        this.pm10value = pm10value;
    }

    public String getPm25value() {
        return pm25value;
    }

    public void setPm25value(String pm25value) {
        this.pm25value = pm25value;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

//    public String getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(String photo) {
//        this.photo = photo;
//    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getDmX() {
        return dmX;
    }

    public void setDmX(String dmX) {
        this.dmX = dmX;
    }

    public String getDmY() {
        return dmY;
    }

    public void setDmY(String dmY) {
        this.dmY = dmY;
    }

    @Override
    public String toString() {
        return "MStation{" +
                "addr='" + addr + '\'' +
                ", stationName='" + stationName + '\'' +
                ", dmX='" + dmX + '\'' +
                ", dmY='" + dmY + '\'' +
                ", pm10value='" + pm10value + '\'' +
                ", pm25value='" + pm25value + '\'' +
                ", pm10Grade1h='" + pm10Grade1h + '\'' +
                ", pm25Grade1h='" + pm25Grade1h + '\'' +
                '}';
    }
}
