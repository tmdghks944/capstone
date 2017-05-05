package com.example.android.capstone;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class SettingActivity extends AppCompatActivity {
    private String myType;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalVariable globalvariable = (GlobalVariable)getApplication();
        setContentView(R.layout.activity_setting);            //다시 조사하기 버튼도 만들자.
        readResultFile();
        if(globalvariable.getwt_value()==-1){
            ActionBar actionBar = this.getSupportActionBar();
            // Set the action bar back button to look like an up button
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            Toast.makeText(SettingActivity.this,"얼굴 인식에 실패하였습니다. 다시 촬영해주세요.",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SettingActivity.this,CameraActivity.class);
            startActivity(intent);
        }
        else{
            myType = getUserType();
            textView = (TextView)findViewById(R.id.myTypeView);
            textView.setText("당신의 피부 타입은 "+ myType + "입니다");
            ActionBar actionBar = this.getSupportActionBar();
            // Set the action bar back button to look like an up button
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

    }

    public void Initialize(View view) {
        switch (view.getId()) {
            case R.id.typeInitialize:
                GlobalVariable globalvariable = (GlobalVariable)getApplication();
                globalvariable.setsurveydone(false);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // When the home button is pressed, take the user back to the VisualizerActivity
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
    public void readResultFile(){
        GlobalVariable globalvariable = (GlobalVariable)getApplication();
        StringBuilder text = new StringBuilder();
        try {
            File sdcard = Environment.getExternalStorageDirectory();
            File file = new File(sdcard,"result.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            line = br.readLine();                       //wt에 대한 정보 0 or 1 or -1  -1이라면 다시 사진을 촬영해야함.
            text.append(line);
            globalvariable.setwt_value(Integer.parseInt(text.toString()));
            text.setLength(0);

            line = br.readLine();                       //sr에 대한 정보 0~5 정도
            text.append(line);
            globalvariable.setsr_value(Integer.parseInt(text.toString()));
            text.setLength(0);

            line = br.readLine();                       //피부 톤에 대한 정보
            text.append(line);
            globalvariable.settone_value(Integer.parseInt(text.toString()));
            text.setLength(0);

            br.close() ;
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getUserType(){

        String type="";
        GlobalVariable globalvariable = (GlobalVariable)getApplication();
        if(globalvariable.getod_value()<=16)
            type+='D';
        else
            type+='O';
        if(globalvariable.getsr_value()>=3)
            type+='S';
        else
            type+='R';
        if(globalvariable.getpn_value()<=15)
            type+='N';
        else
            type+='P';
        if(globalvariable.getwt_value()>=1)
            type+='W';
        else
            type+='T';

        return type;
    }
}
