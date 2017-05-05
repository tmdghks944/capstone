package com.example.android.capstone;

import android.app.Application;

public class GlobalVariable extends Application {
    private String user_name;
    private int temp_value;    //설문조사때 값.
    private int od_value;      //지성 건성 수치
    private int pn_value;      //색소, 비색소 수치
    private int sr_value;      //민감, 저항 수치
    private int wt_value;      //주름 수치
    private int tone_value;
    private boolean surveydone;
    private boolean cameradone;

    public String getusername()
    {
        return user_name;
    }
    public void setusername(String globalString)
    {
        this.user_name = globalString;
    }

    public int gettemp_value(){ return temp_value; }
    public int getod_value(){ return od_value; }
    public int getpn_value(){ return pn_value; }
    public int getsr_value(){ return sr_value; }
    public int getwt_value(){ return wt_value; }
    public int gettone_value(){ return tone_value; }
    public boolean getsurveydone(){ return surveydone; }
    public boolean getcameradone(){ return cameradone; }
    public void settemp_value(int temp_value){ this.temp_value=temp_value; }
    public void setod_value(int od_value){ this.od_value=od_value;}
    public void setpn_value(int pn_value){ this.pn_value=pn_value;}
    public void setsr_value(int sr_value){ this.sr_value=sr_value;}
    public void setwt_value(int wt_value){ this.wt_value=wt_value;}
    public void settone_value(int tone_value){this.tone_value=tone_value;}
    public void setsurveydone(boolean surveydone){ this.surveydone = surveydone; }
    public void setcameradone(boolean cameradone){ this.cameradone = cameradone; }
}
