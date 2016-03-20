package com.meenakshi.domain;

/**
 * Created by lakshmanasattineedi on 18/03/16.
 */

public class Loan {
    public String loginId;
    public String loanType;
    public String loanDetails;
    public String updated_date;
    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public String getLoanType() {
        return loanType;
    }
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }
    public String getLoanDetails() {
        return loanDetails;
    }
    public void setLoanDetails(String loanDetails) {
        this.loanDetails = loanDetails;
    }
    public String getUpdated_date() {
        return updated_date;
    }
    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }



}
