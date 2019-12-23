package com.example.skyobserver;

import java.io.Serializable;

public class MainfragementDTO implements Serializable{

    private String  datatime    ;
    private String  seoul       ;
    private String  busan       ;
    private String  daegu       ;
    private String  incheon     ;
    private String  gwangju     ;
    private String  daejeon     ;
    private String  ulsan       ;
    private String  gyeonggi     ;
    private String  gangwon     ;
    private String  chungbuk    ;
    private String  chungnam    ;
    private String  jeonbuk     ;
    private String  jeonnam     ;
    private String  gyeongbuk   ;
    private String  gyeongnam   ;
    private String  jeju         ;
    private String  sejong          ;



    public MainfragementDTO(){

    }


    public String getRegion(String region){


        switch(region){
            case "seoul":
                return  seoul;
            case   "busan":
                return  busan;
            case  "daegu":
                return  daegu;
            case   "incheon":
                return  incheon;
            case   "gwangju":
                return  gwangju;
            case  "daejeon":
                return  daejeon;
            case  "ulsan":
                return  ulsan;
            case  "gyeonggi":
                return  gyeonggi;
            case   "gangwon":
                return  gangwon;
            case   "chungbuk":
                return  chungbuk;
            case   "chungnam":
                return  chungnam;
            case   "jeonbuk":
                return  jeonbuk;
            case   "jeonnam":
                return  jeonnam;
            case  "gyeongbuk":
                return  gyeongbuk;
            case  "gyeongnam":
                return  gyeongnam;
            case   "jeju":
                return  jeju;
            case   "sejong":
                return  sejong;


        }



        return null;
    }


    public String getSeoul() {
        return seoul;
    }

    public void setSeoul(String seoul) {
        this.seoul = seoul;
    }

    public String getBusan() {
        return busan;
    }

    public void setBusan(String busan) {
        this.busan = busan;
    }

    public String getDaegu() {
        return daegu;
    }

    public void setDaegu(String daegu) {
        this.daegu = daegu;
    }

    public String getIncheon() {
        return incheon;
    }

    public void setIncheon(String incheon) {
        this.incheon = incheon;
    }

    public String getGwangju() {
        return gwangju;
    }

    public void setGwangju(String gwangju) {
        this.gwangju = gwangju;
    }

    public String getDaejeon() {
        return daejeon;
    }

    public void setDaejeon(String daejeon) {
        this.daejeon = daejeon;
    }

    public String getUlsan() {
        return ulsan;
    }

    public void setUlsan(String ulsan) {
        this.ulsan = ulsan;
    }

    public String getGyeonggi() {
        return gyeonggi;
    }

    public void setGyeonggi(String gyeonggi) {
        this.gyeonggi = gyeonggi;
    }

    public String getGangwon() {
        return gangwon;
    }

    public void setGangwon(String gangwon) {
        this.gangwon = gangwon;
    }

    public String getChungbuk() {
        return chungbuk;
    }

    public void setChungbuk(String chungbuk) {
        this.chungbuk = chungbuk;
    }

    public String getChungnam() {
        return chungnam;
    }

    public void setChungnam(String chungnam) {
        this.chungnam = chungnam;
    }

    public String getJeonbuk() {
        return jeonbuk;
    }

    public void setJeonbuk(String jeonbuk) {
        this.jeonbuk = jeonbuk;
    }

    public String getJeonnam() {
        return jeonnam;
    }

    public void setJeonnam(String jeonnam) {
        this.jeonnam = jeonnam;
    }

    public String getGyeongbuk() {
        return gyeongbuk;
    }

    public void setGyeongbuk(String gyeongbuk) {
        this.gyeongbuk = gyeongbuk;
    }

    public String getGyeongnam() {
        return gyeongnam;
    }

    public void setGyeongnam(String gyeongnam) {
        this.gyeongnam = gyeongnam;
    }

    public String getJeju() {
        return jeju;
    }

    public void setJeju(String jeju) {
        this.jeju = jeju;
    }

    public String getSejong() {
        return sejong;
    }

    public void setSejong(String sejong) {
        this.sejong = sejong;
    }

    @Override
    public String toString() {
        return "MainfragementDTO{" +
                "datatime='" + datatime + '\'' +
                ", seoul='" + seoul + '\'' +
                ", busan='" + busan + '\'' +
                ", daegu='" + daegu + '\'' +
                ", incheon='" + incheon + '\'' +
                ", gwangju='" + gwangju + '\'' +
                ", daejeon='" + daejeon + '\'' +
                ", ulsan='" + ulsan + '\'' +
                ", gyeonggi='" + gyeonggi + '\'' +
                ", gangwon='" + gangwon + '\'' +
                ", chungbuk='" + chungbuk + '\'' +
                ", chungnam='" + chungnam + '\'' +
                ", jeonbuk='" + jeonbuk + '\'' +
                ", jeonnam='" + jeonnam + '\'' +
                ", gyeongbuk='" + gyeongbuk + '\'' +
                ", gyeongnam='" + gyeongnam + '\'' +
                ", jeju='" + jeju + '\'' +
                ", sejong='" + sejong + '\'' +
                '}';
    }

    public MainfragementDTO(String datatime, String seoul, String busan, String daegu, String incheon, String gwangju, String daejeon, String ulsan, String gyeonggi, String gangwon, String chungbuk, String chungnam, String jeonbuk, String jeonnam, String gyeongbuk, String gyeongnam, String jeju, String sejong) {
        this.datatime=datatime;
        this.seoul = seoul;
        this.busan = busan;
        this.daegu = daegu;
        this.incheon = incheon;
        this.gwangju = gwangju;
        this.daejeon = daejeon;
        this.ulsan = ulsan;
        this.gyeonggi = gyeonggi;
        this.gangwon = gangwon;
        this.chungbuk = chungbuk;
        this.chungnam = chungnam;
        this.jeonbuk = jeonbuk;
        this.jeonnam = jeonnam;
        this.gyeongbuk = gyeongbuk;
        this.gyeongnam = gyeongnam;
        this.jeju = jeju;
        this.sejong = sejong;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }
}
