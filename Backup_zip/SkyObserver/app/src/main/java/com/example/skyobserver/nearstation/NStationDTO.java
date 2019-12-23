package com.example.skyobserver.nearstation;

public class NStationDTO {

    private String stationName;
    private String addr;



    private String  pm10Value ;
    private String  pm10Value24;
    private String  pm10Grade;
    private String  pm10Grade1h;
    private String  pm25Value;
    private String  pm25Value24 ;
    private String  pm25Grade;
    private String pm25Grade1h ;





    public  NStationDTO() {}

    /*public NStationDTO(String stationName, String addr) {
        this.stationName = stationName;
        this.addr = addr;
    }*/

    public NStationDTO(String stationName, String addr, String pm10Value, String pm10Value24, String pm10Grade, String pm10Grade1h, String pm25Value, String pm25Value24, String pm25Grade, String pm25Grade1h) {
        this.stationName = stationName;
        this.addr = addr;
        this.pm10Value = pm10Value;
        this.pm10Value24 = pm10Value24;
        this.pm10Grade = pm10Grade;
        this.pm10Grade1h = pm10Grade1h;
        this.pm25Value = pm25Value;
        this.pm25Value24 = pm25Value24;
        this.pm25Grade = pm25Grade;
        this.pm25Grade1h = pm25Grade1h;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPm10Value() {
        return pm10Value;
    }

    public void setPm10Value(String pm10Value) {
        this.pm10Value = pm10Value;
    }

    public String getPm10Value24() {
        return pm10Value24;
    }

    public void setPm10Value24(String pm10Value24) {
        this.pm10Value24 = pm10Value24;
    }

    public String getPm10Grade() {
        return pm10Grade;
    }

    public void setPm10Grade(String pm10Grade) {
        this.pm10Grade = pm10Grade;
    }

    public String getPm10Grade1h() {
        return pm10Grade1h;
    }

    public void setPm10Grade1h(String pm10Grade1h) {
        this.pm10Grade1h = pm10Grade1h;
    }

    public String getPm25Value() {
        return pm25Value;
    }

    public void setPm25Value(String pm25Value) {
        this.pm25Value = pm25Value;
    }

    public String getPm25Value24() {
        return pm25Value24;
    }

    public void setPm25Value24(String pm25Value24) {
        this.pm25Value24 = pm25Value24;
    }

    public String getPm25Grade() {
        return pm25Grade;
    }

    public void setPm25Grade(String pm25Grade) {
        this.pm25Grade = pm25Grade;
    }

    public String getPm25Grade1h() {
        return pm25Grade1h;
    }

    public void setPm25Grade1h(String pm25Grade1h) {
        this.pm25Grade1h = pm25Grade1h;
    }

/*@Override
    public String toString() {
        return "NStationDTO{" +
                "stationName='" + stationName + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }*/

    @Override
    public String toString() {
        return "NStationDTO{" +
                "stationName='" + stationName + '\'' +
                ", addr='" + addr + '\'' +
                ", pm10Value='" + pm10Value + '\'' +
                ", pm10Value24='" + pm10Value24 + '\'' +
                ", pm10Grade='" + pm10Grade + '\'' +
                ", pm10Grade1h='" + pm10Grade1h + '\'' +
                ", pm25Value='" + pm25Value + '\'' +
                ", pm25Value24='" + pm25Value24 + '\'' +
                ", pm25Grade='" + pm25Grade + '\'' +
                ", pm25Grade1h='" + pm25Grade1h + '\'' +
                '}';
    }
}
