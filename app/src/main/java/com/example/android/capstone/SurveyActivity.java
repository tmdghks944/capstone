package com.example.android.capstone;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SurveyActivity extends AppCompatActivity{
    TextView textview;
    LinearLayout question1;
    LinearLayout question2;
    LinearLayout question3;
    LinearLayout question4;
    LinearLayout question5;
    LinearLayout question6;
    LinearLayout question7;
    LinearLayout question8;
    LinearLayout question9;
    LinearLayout question10;
    LinearLayout question11;
    LinearLayout question12;
    LinearLayout question13;
    LinearLayout question14;
    LinearLayout question15;
    LinearLayout question16;
    LinearLayout question17;
    LinearLayout question18;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn10;
    Button btn11;
    Button btn12;
    Button btn13;
    Button btn14;
    Button btn15;
    Button btn16;
    Button btn17;
    Button gotomainbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalVariable globalvariable = (GlobalVariable)getApplication();
        if(globalvariable.getsurveydone()==true){
            Intent intent = new Intent(SurveyActivity.this,MainActivity.class);
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_survey);
            textview = (TextView) findViewById(R.id.hello_text);
            globalvariable.setod_value(0);
            globalvariable.setpn_value(0);
            textview.setText("안녕하세요 " + globalvariable.getusername() + "님");
            setUp();
        }
    }

    public void clickBtn(View view) {
        GlobalVariable globalvariable = (GlobalVariable)getApplication();
        EditText editText = (EditText)findViewById(R.id.userageinput);
        switch (view.getId()) {
            case R.id.gotoMain://이제 이게 나이측정하는걸로바뀐다.
                if(editText.getText().toString().getBytes().length<=0){
                    Toast.makeText(SurveyActivity.this,"나이를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
                else {
                    globalvariable.setuser_age(Integer.parseInt(editText.getText().toString()));
                    globalvariable.setsurveydone(true);
                    SurveyActivity.this.finish();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                break;
            //TODO : 선택으로 다음 누르면 못가게 해야함.
            case R.id.q1:
                globalvariable.setod_value(globalvariable.getod_value()+globalvariable.gettemp_value());
                question1.setVisibility(View.GONE);
                btn1.setVisibility(View.GONE);
                question2.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                break;
            case R.id.q2:
                globalvariable.setod_value(globalvariable.getod_value()+globalvariable.gettemp_value());
                question2.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                question3.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);
                break;
            case R.id.q3:
                globalvariable.setod_value(globalvariable.getod_value()+globalvariable.gettemp_value());
                question3.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
                question4.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.VISIBLE);
                break;
            case R.id.q4:
                globalvariable.setod_value(globalvariable.getod_value()+globalvariable.gettemp_value());
                question4.setVisibility(View.GONE);
                btn4.setVisibility(View.GONE);
                question5.setVisibility(View.VISIBLE);
                btn5.setVisibility(View.VISIBLE);
                break;
            case R.id.q5:
                globalvariable.setod_value(globalvariable.getod_value()+globalvariable.gettemp_value());
                question5.setVisibility(View.GONE);
                btn5.setVisibility(View.GONE);
                question6.setVisibility(View.VISIBLE);
                btn6.setVisibility(View.VISIBLE);
                break;
            case R.id.q6:
                globalvariable.setod_value(globalvariable.getod_value()+globalvariable.gettemp_value());
                question6.setVisibility(View.GONE);
                btn6.setVisibility(View.GONE);
                question7.setVisibility(View.VISIBLE);
                btn7.setVisibility(View.VISIBLE);
                break;
            case R.id.q7:
                globalvariable.setpn_value(globalvariable.getpn_value()+globalvariable.gettemp_value());
                question7.setVisibility(View.GONE);
                btn7.setVisibility(View.GONE);
                question8.setVisibility(View.VISIBLE);
                btn8.setVisibility(View.VISIBLE);
                break;
            case R.id.q8:
                globalvariable.setpn_value(globalvariable.getpn_value()+globalvariable.gettemp_value());
                question8.setVisibility(View.GONE);
                btn8.setVisibility(View.GONE);
                question9.setVisibility(View.VISIBLE);
                btn9.setVisibility(View.VISIBLE);
                break;
            case R.id.q9:
                globalvariable.setpn_value(globalvariable.getpn_value()+globalvariable.gettemp_value());
                question9.setVisibility(View.GONE);
                btn9.setVisibility(View.GONE);
                question10.setVisibility(View.VISIBLE);
                btn10.setVisibility(View.VISIBLE);
                break;
            case R.id.q10:
                globalvariable.setpn_value(globalvariable.getpn_value()+globalvariable.gettemp_value());
                question10.setVisibility(View.GONE);
                btn10.setVisibility(View.GONE);
                question11.setVisibility(View.VISIBLE);
                btn11.setVisibility(View.VISIBLE);
                break;
            case R.id.q11:
                globalvariable.setpn_value(globalvariable.getpn_value()+globalvariable.gettemp_value());
                question11.setVisibility(View.GONE);
                btn11.setVisibility(View.GONE);
                question12.setVisibility(View.VISIBLE);
                btn12.setVisibility(View.VISIBLE);
                break;
            case R.id.q12://원래 메인가는 버튼.
                globalvariable.setpn_value(globalvariable.getpn_value()+globalvariable.gettemp_value());
                question12.setVisibility(View.GONE);
                btn12.setVisibility(View.GONE);
                question13.setVisibility(View.VISIBLE);
                btn13.setVisibility(View.VISIBLE);
                break;
            case R.id.q13://
                globalvariable.setpn_value(globalvariable.getpn_value()+globalvariable.gettemp_value());
                question13.setVisibility(View.GONE);
                btn13.setVisibility(View.GONE);
                question14.setVisibility(View.VISIBLE);
                btn14.setVisibility(View.VISIBLE);
                break;
            case R.id.q14:
                globalvariable.setsurvey_sr_value(globalvariable.getsurvey_sr_value()+globalvariable.gettemp_value());
                question14.setVisibility(View.GONE);
                btn14.setVisibility(View.GONE);
                question15.setVisibility(View.VISIBLE);
                btn15.setVisibility(View.VISIBLE);
                break;
            case R.id.q15:
                globalvariable.setsurvey_sr_value(globalvariable.getsurvey_sr_value()+globalvariable.gettemp_value());
                question15.setVisibility(View.GONE);
                btn15.setVisibility(View.GONE);
                question16.setVisibility(View.VISIBLE);
                btn16.setVisibility(View.VISIBLE);
                break;
            case R.id.q16:
                globalvariable.setsurvey_sr_value(globalvariable.getsurvey_sr_value()+globalvariable.gettemp_value());
                question16.setVisibility(View.GONE);
                btn16.setVisibility(View.GONE);
                question17.setVisibility(View.VISIBLE);
                btn17.setVisibility(View.VISIBLE);
                break;
            case R.id.q17:
                globalvariable.setsurvey_sr_value(globalvariable.getsurvey_sr_value()+globalvariable.gettemp_value());
                question17.setVisibility(View.GONE);
                btn17.setVisibility(View.GONE);
                question18.setVisibility(View.VISIBLE);
                gotomainbtn.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setUp(){
        final GlobalVariable globalvariable = (GlobalVariable)getApplication();

        question1 = (LinearLayout)findViewById(R.id.survey1);
        question2 = (LinearLayout)findViewById(R.id.survey2);
        question3 = (LinearLayout)findViewById(R.id.survey3);
        question4 = (LinearLayout)findViewById(R.id.survey4);
        question5 = (LinearLayout)findViewById(R.id.survey5);
        question6 = (LinearLayout)findViewById(R.id.survey6);
        question7 = (LinearLayout)findViewById(R.id.survey7);
        question8 = (LinearLayout)findViewById(R.id.survey8);
        question9 = (LinearLayout)findViewById(R.id.survey9);
        question10 = (LinearLayout)findViewById(R.id.survey10);
        question11 = (LinearLayout)findViewById(R.id.survey11);
        question12 = (LinearLayout)findViewById(R.id.survey12);
        question13 = (LinearLayout)findViewById(R.id.survey13);
        question14 = (LinearLayout)findViewById(R.id.survey14);
        question15 = (LinearLayout)findViewById(R.id.survey15);
        question16 = (LinearLayout)findViewById(R.id.survey16);
        question17 = (LinearLayout)findViewById(R.id.survey17);
        question18 = (LinearLayout)findViewById(R.id.survey18);



        btn1 = (Button)findViewById(R.id.q1);
        btn2 = (Button)findViewById(R.id.q2);
        btn3 = (Button)findViewById(R.id.q3);
        btn4 = (Button)findViewById(R.id.q4);
        btn5 = (Button)findViewById(R.id.q5);
        btn6 = (Button)findViewById(R.id.q6);
        btn7 = (Button)findViewById(R.id.q7);
        btn8 = (Button)findViewById(R.id.q8);
        btn9 = (Button)findViewById(R.id.q9);
        btn10 = (Button)findViewById(R.id.q10);
        btn11 = (Button)findViewById(R.id.q11);
        btn12 = (Button)findViewById(R.id.q12);
        btn13 = (Button)findViewById(R.id.q13);
        btn14 = (Button)findViewById(R.id.q14);
        btn15 = (Button)findViewById(R.id.q15);
        btn16 = (Button)findViewById(R.id.q16);
        btn17 = (Button)findViewById(R.id.q17);
        gotomainbtn = (Button)findViewById(R.id.gotoMain);


        Spinner odq1Spinner = (Spinner)findViewById(R.id.odq1);
        final ArrayAdapter odq1Adapter = ArrayAdapter.createFromResource(this,
                R.array.odq1, android.R.layout.simple_spinner_item);
        odq1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        odq1Spinner.setAdapter(odq1Adapter);

        odq1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });


        Spinner odq2Spinner = (Spinner)findViewById(R.id.odq2);
        ArrayAdapter odq2Adapter = ArrayAdapter.createFromResource(this,
                R.array.odq2, android.R.layout.simple_spinner_item);
        odq2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        odq2Spinner.setAdapter(odq2Adapter);

        odq2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });

        Spinner odq3Spinner = (Spinner)findViewById(R.id.odq3);
        ArrayAdapter odq3Adapter = ArrayAdapter.createFromResource(this,
                R.array.odq3, android.R.layout.simple_spinner_item);
        odq3Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        odq3Spinner.setAdapter(odq3Adapter);

        odq3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });

        Spinner odq4Spinner = (Spinner)findViewById(R.id.odq4);
        ArrayAdapter odq4Adapter = ArrayAdapter.createFromResource(this,
                R.array.odq4, android.R.layout.simple_spinner_item);
        odq4Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        odq4Spinner.setAdapter(odq4Adapter);

        odq4Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });

        Spinner odq5Spinner = (Spinner)findViewById(R.id.odq5);
        ArrayAdapter odq5Adapter = ArrayAdapter.createFromResource(this,
                R.array.odq5, android.R.layout.simple_spinner_item);
        odq5Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        odq5Spinner.setAdapter(odq5Adapter);

        odq5Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });

        Spinner odq6Spinner = (Spinner)findViewById(R.id.odq6);
        ArrayAdapter odq6Adapter = ArrayAdapter.createFromResource(this,
                R.array.odq6, android.R.layout.simple_spinner_item);
        odq6Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        odq6Spinner.setAdapter(odq6Adapter);

        odq6Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });

        Spinner pnq1Spinner = (Spinner)findViewById(R.id.pnq1);
        ArrayAdapter pnq1Adapter = ArrayAdapter.createFromResource(this,
                R.array.pnq1, android.R.layout.simple_spinner_item);
        pnq1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pnq1Spinner.setAdapter(pnq1Adapter);

        pnq1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });

        Spinner pnq2Spinner = (Spinner)findViewById(R.id.pnq2);
        ArrayAdapter pnq2Adapter = ArrayAdapter.createFromResource(this,
                R.array.pnq2, android.R.layout.simple_spinner_item);
        pnq2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pnq2Spinner.setAdapter(pnq2Adapter);

        pnq2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });

        Spinner pnq3Spinner = (Spinner)findViewById(R.id.pnq3);
        ArrayAdapter pnq3Adapter = ArrayAdapter.createFromResource(this,
                R.array.pnq3, android.R.layout.simple_spinner_item);
        pnq3Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pnq3Spinner.setAdapter(pnq3Adapter);

        pnq3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
                if(position==5)
                    globalvariable.settemp_value(2);
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });

        Spinner pnq4Spinner = (Spinner)findViewById(R.id.pnq4);
        ArrayAdapter pnq4Adapter = ArrayAdapter.createFromResource(this,
                R.array.pnq4, android.R.layout.simple_spinner_item);
        pnq4Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pnq4Spinner.setAdapter(pnq4Adapter);

        pnq4Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });

        Spinner pnq5Spinner = (Spinner)findViewById(R.id.pnq5);
        ArrayAdapter pnq5Adapter = ArrayAdapter.createFromResource(this,
                R.array.pnq5, android.R.layout.simple_spinner_item);
        pnq5Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pnq5Spinner.setAdapter(pnq5Adapter);

        pnq5Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
                if(position==5){
                    globalvariable.settemp_value(2);
                }
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });

        Spinner pnq6Spinner = (Spinner)findViewById(R.id.pnq6);
        ArrayAdapter pnq6Adapter = ArrayAdapter.createFromResource(this,
                R.array.pnq6, android.R.layout.simple_spinner_item);
        pnq6Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pnq6Spinner.setAdapter(pnq6Adapter);

        pnq6Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });

        Spinner pnq7Spinner = (Spinner)findViewById(R.id.pnq7);
        ArrayAdapter pnq7Adapter = ArrayAdapter.createFromResource(this,
                R.array.pnq7, android.R.layout.simple_spinner_item);
        pnq7Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pnq7Spinner.setAdapter(pnq7Adapter);

        pnq7Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
                if(position==5){
                    globalvariable.settemp_value(2);
                }
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });

        Spinner srq1Spinner = (Spinner)findViewById(R.id.srq1);
        ArrayAdapter srq1Adapter = ArrayAdapter.createFromResource(this,
                R.array.srq1, android.R.layout.simple_spinner_item);
        srq1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        srq1Spinner.setAdapter(srq1Adapter);

        srq1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
                if(position==5){
                    globalvariable.settemp_value(2);
                }
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });

        Spinner srq2Spinner = (Spinner)findViewById(R.id.srq2);
        ArrayAdapter srq2Adapter = ArrayAdapter.createFromResource(this,
                R.array.srq2, android.R.layout.simple_spinner_item);
        srq2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        srq2Spinner.setAdapter(srq2Adapter);

        srq2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
                if(position==4){
                    globalvariable.settemp_value(2);
                }
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });

        Spinner srq3Spinner = (Spinner)findViewById(R.id.srq3);
        ArrayAdapter srq3Adapter = ArrayAdapter.createFromResource(this,
                R.array.srq3, android.R.layout.simple_spinner_item);
        srq3Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        srq3Spinner.setAdapter(srq3Adapter);

        srq3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
                if(position==4){
                    globalvariable.settemp_value(2);
                }
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });

        Spinner srq4Spinner = (Spinner)findViewById(R.id.srq4);
        ArrayAdapter srq4Adapter = ArrayAdapter.createFromResource(this,
                R.array.srq4, android.R.layout.simple_spinner_item);
        srq4Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        srq4Spinner.setAdapter(srq4Adapter);

        srq4Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                globalvariable.settemp_value(position);
            }
            public void onNothingSelected(AdapterView<?>  parent) {

            }
        });
    }
}