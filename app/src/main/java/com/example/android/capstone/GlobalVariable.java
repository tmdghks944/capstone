package com.example.android.capstone;

import android.app.Application;

import java.util.HashMap;

public class GlobalVariable extends Application {
    private String user_name;
    private int temp_value;    //설문조사때 값.
    private int od_value;      //지성 건성 수치
    private int pn_value;      //색소, 비색소 수치
    private int sr_value;      //민감, 저항 수치
    private int survey_sr_value;
    private int wt_value;      //주름 수치
    private int tone_value;
    private int type_value;
    private int user_age;
    private boolean surveydone;
    private boolean cameradone;
    private String detailid;
    private String detailname;
    private boolean highlight;

    private HashMap<String,Integer> warn = new HashMap<String,Integer>();

    public String getusername() { return user_name; }
    public void setusername(String globalString)
    {
        this.user_name = globalString;
    }

    public String getdetailid(){return detailid;}
    public void setdetailid(String detailid){this.detailid=detailid;}

    public String getdetailname(){return detailname;}
    public void setdetailname(String detailname){this.detailname=detailname;}

    public int gettemp_value(){ return temp_value; }
    public int getod_value(){ return od_value; }
    public int getpn_value(){ return pn_value; }
    public int getsr_value(){ return sr_value; }
    public int getsurvey_sr_value(){ return survey_sr_value; }
    public int getwt_value(){ return wt_value; }
    public int gettone_value(){ return tone_value; }
    public int gettype_value(){return type_value;}

    public boolean gethighlight(){return highlight;}
    public int getuser_age(){return user_age;}
    public boolean getsurveydone(){ return surveydone; }
    public boolean getcameradone(){ return cameradone; }

    public HashMap<String,Integer> getwarn(){
        return warn;
    }
    public void initializewarn(){
        warn.clear();
    }
    public void pushwarn(String str,int val){
        warn.put(str,new Integer(val));
    }
    public void setuser_age(int user_age){this.user_age=user_age;}

    public void settemp_value(int temp_value){ this.temp_value=temp_value; }
    public void setod_value(int od_value){ this.od_value=od_value;}
    public void setpn_value(int pn_value){ this.pn_value=pn_value;}
    public void setsr_value(int sr_value){ this.sr_value=sr_value;}
    public void setsurvey_sr_value(int survey_sr_value){this.survey_sr_value=survey_sr_value;}
    public void setwt_value(int wt_value){ this.wt_value=wt_value;}
    public void settone_value(int tone_value){this.tone_value=tone_value;}
    public void settype_value(int type_value){this.type_value=type_value;}

    public void setsurveydone(boolean surveydone){ this.surveydone = surveydone; }
    public void setcameradone(boolean cameradone){ this.cameradone = cameradone; }
    public void sethighright(boolean highlight){this.highlight=highlight;}
}
