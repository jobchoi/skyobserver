package com.example.skyobserver.msmap;

public class RegiongpsDTO {
    private String code,sido,gugun,dong,ri,x,y,wx,wy;


    public RegiongpsDTO(String code, String sido, String gugun, String dong, String ri, String x, String y, String wx, String wy) {
        this.code = code;
        this.sido = sido;
        this.gugun = gugun;
        this.dong = dong;
        this.ri = ri;
        this.x = x;
        this.y = y;
        this.wx = wx;
        this.wy = wy;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getWy() {
        return wy;
    }

    public void setWy(String wy) {
        this.wy = wy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSido() {
        return sido;
    }

    public void setSido(String sido) {
        this.sido = sido;
    }

    public String getGugun() {
        return gugun;
    }

    public void setGugun(String gugun) {
        this.gugun = gugun;
    }

    public String getRi() {
        return ri;
    }

    public void setRi(String ri) {
        this.ri = ri;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getDong() {
        return dong;
    }

    public void setDong(String dong) {
        this.dong = dong;
    }
}
