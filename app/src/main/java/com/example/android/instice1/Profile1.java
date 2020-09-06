package com.example.android.instice1;

public class Profile1 {
    private String name;
    private String imgView;

    public Profile1(String name, String imgView ) {
        this.name = name;
        this.imgView = imgView;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImgView() {
        return imgView;
    }
    public void setImgView(String imgView) {
        this.imgView = imgView;
    }
    public Profile1()
    {

    }
}
