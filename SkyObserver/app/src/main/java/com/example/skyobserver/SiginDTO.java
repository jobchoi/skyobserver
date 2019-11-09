package com.example.skyobserver;

public class SiginDTO {

    private String id;
    private String pwd;
    private String name;
    private String email;


    public SiginDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail(){ return email; }

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public SiginDTO(String id, String pwd, String email, String name) {
        this.id = id;
        this.pwd = pwd;
        this.email = email;
        this.name = name;
    }


    @Override
    public String toString() {
        return "SiginDTO{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
