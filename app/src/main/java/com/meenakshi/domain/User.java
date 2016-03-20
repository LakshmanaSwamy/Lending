package com.meenakshi.domain;

/**
 * Created by lakshmanasattineedi on 05/03/16.
 */
public class User {
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String loginId;
    private String password;
    private String loginType;
    private String firstLoginTime;
    private String lastLogoutTime;
    private int isActive;
    private String email;

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
    
    private String address;
    private String gender;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getFirstLoginTime() {
        return firstLoginTime;
    }

    public void setFirstLoginTime(String firstLoginTime) {
        this.firstLoginTime = firstLoginTime;
    }

    public String getLastLogoutTime() {
        return lastLogoutTime;
    }

    public void setLastLogoutTime(String lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
