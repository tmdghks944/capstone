package com.example.android.capstone;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SurveyActivity extends AppCompatActivity{
    TextView textview;
    Button gotoMainbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        GlobalVariable globalvariable = (GlobalVariable)getApplication();
        textview = (TextView)findViewById(R.id.hello_text);
        textview.setText("안녕하세요 " + globalvariable.getusername()+"님");
    }
    public void clickBtn(View view) {
        switch (view.getId()) {
            case R.id.gotoMain:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
