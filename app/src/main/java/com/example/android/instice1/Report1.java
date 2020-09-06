package com.example.android.instice1;

public class Report1 {

    private String userName;
    private String userMobile_no;
    private String userCountry;
    private String userId;
    private String userState;
    private String userEmail;
    private String userDescription;
    private String userAddress;
    private String userTime;
    private String userDate;
    private String userImageUrl;
    private String userCheck;
    private String userCurrent_date;
    public Report1(){

    }

    public Report1(String userName, String userMobile_no, String userCountry, String userId,
                   String userState, String userEmail, String userDescription, String userCheck,
                   String userAddress, String userTime, String userDate, String userImageUrl , String userCurrent_date) {
        this.userName = userName;
        this.userMobile_no = userMobile_no;
        this.userCountry = userCountry;
        this.userId = userId;
        this.userState = userState;
        this.userEmail = userEmail;
        this.userCheck = userCheck;
        this.userDescription = userDescription;
        this.userAddress = userAddress;
        this.userTime = userTime;
        this.userDate = userDate;
        this.userImageUrl = userImageUrl;
        this.userCurrent_date = userCurrent_date;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserCheck() {
        return userCheck;
    }

    public String getUserMobile_no() {
        return userMobile_no;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserCurrent_date() { return userCurrent_date; }

    public String getUserState() {
        return userState;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserTime() {
        return userTime;
    }

    public String getUserDate() {
        return userDate;
    }

    public  String getUserImageUrl(){
        return userImageUrl;
    }

}
