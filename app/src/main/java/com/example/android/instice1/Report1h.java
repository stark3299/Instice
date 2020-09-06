package com.example.android.instice1;

public class Report1h {

    private String userName;
    private String userId;
    private String userEmail;
    private String userImageUrl;
    public Report1h(){

    }

    public Report1h(String userName, String userId, String userEmail,String userImageUrl ) {
        this.userName = userName;
        this.userId = userId;
        this.userEmail = userEmail;
        this.userImageUrl = userImageUrl;

    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public  String getUserImageUrl(){
        return userImageUrl;
    }

}
