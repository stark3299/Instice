package com.example.android.instice1;

public class List1 {
    private String name;
    private String email;
    private String imgView;
    private Boolean check;

    public List1(String name, String email, String imgView , Boolean check ) {
        this.name = name;
        this.email = email;
        this.imgView = imgView;
        this.check = check;
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
    public Boolean getCheck() {
        return check;
    }
    public void setCheck(Boolean check) {
        this.check = check;
    }
    public String getImgView() {
        return imgView;
    }
    public void setImgView(String imgView) {
        this.imgView = imgView;
    }
    public List1()
    {

    }
}
