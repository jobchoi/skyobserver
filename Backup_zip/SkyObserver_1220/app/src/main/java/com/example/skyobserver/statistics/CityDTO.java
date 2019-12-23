package com.example.skyobserver.statistics;

public class CityDTO {
    private String cityName;
    private String pm10avg;
    private String pm25avg;
    private String datatime;

    public CityDTO(){}

    public CityDTO(String cityName, String pm10avg, String pm25avg, String datatime) {
        this.cityName = cityName;
        this.pm10avg = pm10avg;
        this.pm25avg = pm25avg;
        this.datatime = datatime;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPm10avg() {
        return pm10avg;
    }

    public void setPm10avg(String pm10avg) {
        this.pm10avg = pm10avg;
    }

    public String getPm25avg() {
        return pm25avg;
    }

    public void setPm25avg(String pm25avg) {
        this.pm25avg = pm25avg;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "cityName='" + cityName + '\'' +
                ", pm10avg='" + pm10avg + '\'' +
                ", pm25avg='" + pm25avg + '\'' +
                ", datatime='" + datatime + '\'' +
                '}';
    }
}
