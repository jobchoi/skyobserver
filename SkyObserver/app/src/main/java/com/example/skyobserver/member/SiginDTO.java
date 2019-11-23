package com.example.skyobserver.member;

public class SiginDTO {

    private String id;
    private String pwd;
    private String name;
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public SiginDTO() {

    }

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SiginDTO(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SiginDTO{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
