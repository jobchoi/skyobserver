package com.example.skyobserver.board;

import java.sql.Timestamp;

public class BoardDTO {


    private String ARTICLENO;
    private String SUBJECT;
    private String CONTENT;
    private String PASSWD;
    private String REG_DATE;
    private String READCOUNT;
    private String REF;
    private String RE_STEP;
    private String RE_LEVEL;
    private String FILENAME;
    private String ID;
    private String name;
    private String email;


    public BoardDTO(String ARTICLENO, String SUBJECT, String CONTENT, String PASSWD, String REG_DATE, String READCOUNT, String REF, String RE_STEP, String RE_LEVEL, String FILENAME, String ID, String name, String email) {
        this.ARTICLENO = ARTICLENO;
        this.SUBJECT = SUBJECT;
        this.CONTENT = CONTENT;
        this.PASSWD = PASSWD;
        this.REG_DATE = REG_DATE;
        this.READCOUNT = READCOUNT;
        this.REF = REF;
        this.RE_STEP = RE_STEP;
        this.RE_LEVEL = RE_LEVEL;
        this.FILENAME = FILENAME;
        this.ID = ID;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getARTICLENO() {
        return ARTICLENO;
    }

    public void setARTICLENO(String ARTICLENO) {
        this.ARTICLENO = ARTICLENO;
    }

    public String getSUBJECT() {
        return SUBJECT;
    }

    public void setSUBJECT(String SUBJECT) {
        this.SUBJECT = SUBJECT;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getPASSWD() {
        return PASSWD;
    }

    public void setPASSWD(String PASSWD) {
        this.PASSWD = PASSWD;
    }

    public String getREG_DATE() {
        return REG_DATE;
    }

    public void setREG_DATE(String REG_DATE) {
        this.REG_DATE = REG_DATE;
    }

    public String getREADCOUNT() {
        return READCOUNT;
    }

    public void setREADCOUNT(String READCOUNT) {
        this.READCOUNT = READCOUNT;
    }

    public String getREF() {
        return REF;
    }

    public void setREF(String REF) {
        this.REF = REF;
    }

    public String getRE_STEP() {
        return RE_STEP;
    }

    public void setRE_STEP(String RE_STEP) {
        this.RE_STEP = RE_STEP;
    }

    public String getRE_LEVEL() {
        return RE_LEVEL;
    }

    public void setRE_LEVEL(String RE_LEVEL) {
        this.RE_LEVEL = RE_LEVEL;
    }

    public String getFILENAME() {
        return FILENAME;
    }

    public void setFILENAME(String FILENAME) {
        this.FILENAME = FILENAME;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "ARTICLENO='" + ARTICLENO + '\'' +
                ", SUBJECT='" + SUBJECT + '\'' +
                ", CONTENT='" + CONTENT + '\'' +
                ", PASSWD='" + PASSWD + '\'' +
                ", REG_DATE='" + REG_DATE + '\'' +
                ", READCOUNT='" + READCOUNT + '\'' +
                ", REF='" + REF + '\'' +
                ", RE_STEP='" + RE_STEP + '\'' +
                ", RE_LEVEL='" + RE_LEVEL + '\'' +
                ", FILENAME='" + FILENAME + '\'' +
                ", ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /*
    private int ARTICLENO;
    private String SUBJECT;
    private String CONTENT;
    private String PASSWD;
    private Timestamp REG_DATE;
    private int READCOUNT;
    private int REF;
    private int RE_STEP;
    private int RE_LEVEL;
    private String FILENAME;
    private String ID;*/
/*
public BoardDTO(){}

    public BoardDTO(int ARTICLENO, String SUBJECT, String CONTENT, String PASSWD, Timestamp REG_DATE, int READCOUNT, int REF, int RE_STEP, int RE_LEVEL, String FILENAME, String ID) {
        this.ARTICLENO = ARTICLENO;
        this.SUBJECT = SUBJECT;
        this.CONTENT = CONTENT;
        this.PASSWD = PASSWD;
        this.REG_DATE = REG_DATE;
        this.READCOUNT = READCOUNT;
        this.REF = REF;
        this.RE_STEP = RE_STEP;
        this.RE_LEVEL = RE_LEVEL;
        this.FILENAME = FILENAME;
        this.ID = ID;
    }

    public int getARTICLENO() {
        return ARTICLENO;
    }

    public void setARTICLENO(int ARTICLENO) {
        this.ARTICLENO = ARTICLENO;
    }

    public String getSUBJECT() {
        return SUBJECT;
    }

    public void setSUBJECT(String SUBJECT) {
        this.SUBJECT = SUBJECT;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getPASSWD() {
        return PASSWD;
    }

    public void setPASSWD(String PASSWD) {
        this.PASSWD = PASSWD;
    }

    public Timestamp getREG_DATE() {
        return REG_DATE;
    }

    public void setREG_DATE(Timestamp REG_DATE) {
        this.REG_DATE = REG_DATE;
    }

    public int getREADCOUNT() {
        return READCOUNT;
    }

    public void setREADCOUNT(int READCOUNT) {
        this.READCOUNT = READCOUNT;
    }

    public int getREF() {
        return REF;
    }

    public void setREF(int REF) {
        this.REF = REF;
    }

    public int getRE_STEP() {
        return RE_STEP;
    }

    public void setRE_STEP(int RE_STEP) {
        this.RE_STEP = RE_STEP;
    }

    public int getRE_LEVEL() {
        return RE_LEVEL;
    }

    public void setRE_LEVEL(int RE_LEVEL) {
        this.RE_LEVEL = RE_LEVEL;
    }

    public String getFILENAME() {
        return FILENAME;
    }

    public void setFILENAME(String FILENAME) {
        this.FILENAME = FILENAME;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "BoardnDTO{" +
                "ARTICLENO=" + ARTICLENO +
                ", SUBJECT='" + SUBJECT + '\'' +
                ", CONTENT='" + CONTENT + '\'' +
                ", PASSWD='" + PASSWD + '\'' +
                ", REG_DATE=" + REG_DATE +
                ", READCOUNT=" + READCOUNT +
                ", REF=" + REF +
                ", RE_STEP=" + RE_STEP +
                ", RE_LEVEL=" + RE_LEVEL +
                ", FILENAME='" + FILENAME + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }*/
}
