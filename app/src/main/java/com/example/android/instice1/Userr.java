package com.example.android.instice1;

public class Userr {
    private String name;
    private String email;
    private String imgView;
    private Boolean check;
    private String id;
    private String current_date;

    public Userr() {
    }

    public Userr(String name, String email, String imgView , Boolean check , String id , String current_date) {
        this.name = name;
        this.email = email;
        this.imgView = imgView;
        this.check = check;
        this.id = id;
        this.current_date = current_date;
    }

    public String getName() {
        return name;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
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

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgView() {
        return imgView;
    }

    public void setImgView(String imgView) {
        this.imgView = imgView;
    }
}
