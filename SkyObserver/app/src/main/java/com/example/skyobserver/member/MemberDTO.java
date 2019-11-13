package com.example.skyobserver.member;

public class MemberDTO {
    private String email;
    private String name;
    private String pwd;
    private String filename;

    @Override
    public String toString() {
        return "MemberDTO{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
