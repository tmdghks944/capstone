package com.example.android.capstone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }
    public void showResult(View view){
        switch (view.getId()) {
            case R.id.showResultbtn:
                Intent intent = new Intent(this,SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
