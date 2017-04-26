package com.example.android.capstone;

import android.app.Application;

public class GlobalVariable extends Application {
    private String user_name;

    public String getusername()
    {
        return user_name;
    }
    public void setusername(String globalString)
    {
        this.user_name = globalString;
    }
}
