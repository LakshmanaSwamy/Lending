package com.meenakshi.domain;

/**
 * Created by lakshmanasattineedi on 17/03/16.
 */
public class Contact {
    private String loginId;
    private String customerName;
    private String contactNumber;
    private String message;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    private String contactSubject;
    private String updatedTime;

    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customer_name) {
        this.customerName = customer_name;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public String getContactSubject() {
        return contactSubject;
    }
    public void setContactSubject(String contact_subject) {
        this.contactSubject = contactSubject;
    }
    public String getUpdatedTime() {
        return updatedTime;
    }
    public void setUpdatedTime(String updated_time) {
        this.updatedTime = updated_time;
    }

}

