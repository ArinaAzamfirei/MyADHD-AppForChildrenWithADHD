package com.myadhd;

import android.app.Application;


public class MyApplication extends Application {

    private static String userToken;

    private static Integer userId;
    private static Integer currentChildId;
    private static String currentChildCode;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer id){userId = id;}

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String token) {
        userToken = token;
    }

    public Integer getCurrentChildId(){return currentChildId;}

    public void setCurrentChildId(Integer id){currentChildId = id;}

    public String getCurrentChildCode() {
        return currentChildCode;
    }

    public void setCurrentChildCode(String currentChildCode) {
        MyApplication.currentChildCode = currentChildCode;
    }
}