package com.meenakshi.lending;

import android.app.Application;

import com.meenakshi.domain.User;

/**
 * Created by lakshmanasattineedi on 15/03/16.
 */
public class Lending extends Application {
    private User user;

    public User getUser(){
        return user;
    }
    public  void setUser(User user){
       this.user = user;
    }
}
