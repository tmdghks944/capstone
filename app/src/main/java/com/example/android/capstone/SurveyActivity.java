package com.example.android.capstone;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    Button gotomainbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        GlobalVariable globalvariable = (GlobalVariable)getApplication();
        textview = (TextView)findViewById(R.id.hello_text);
        textview.setText("안녕하세요 " + globalvariable.getusername()+"님");
        setUp();
    }

    public void clickBtn(View view) {
        switch (view.getId()) {
            case R.id.gotoMain:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.q1:
                question1.setVisibility(View.GONE);
                btn1.setVisibility(View.GONE);
                question2.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                break;
            case R.id.q2:
                question2.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                question3.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);
                break;
            case R.id.q3:
                question3.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
                question4.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.VISIBLE);
                break;
            case R.id.q4:
                question4.setVisibility(View.GONE);
                btn4.setVisibility(View.GONE);
                question5.setVisibility(View.VISIBLE);
                btn5.setVisibility(View.VISIBLE);
                break;
            case R.id.q5:
                question5.setVisibility(View.GONE);
                btn5.setVisibility(View.GONE);
                question6.setVisibility(View.VISIBLE);
                btn6.setVisibility(View.VISIBLE);
                break;
            case R.id.q6:
                question6.setVisibility(View.GONE);
                btn6.setVisibility(View.GONE);
                question7.setVisibility(View.VISIBLE);
                btn7.setVisibility(View.VISIBLE);
                break;
            case R.id.q7:
                question7.setVisibility(View.GONE);
                btn7.setVisibility(View.GONE);
                question8.setVisibility(View.VISIBLE);
                btn8.setVisibility(View.VISIBLE);
                break;
            case R.id.q8:
                question8.setVisibility(View.GONE);
                btn8.setVisibility(View.GONE);
                question9.setVisibility(View.VISIBLE);
                btn9.setVisibility(View.VISIBLE);
                break;
            case R.id.q9:
                question9.setVisibility(View.GONE);
                btn9.setVisibility(View.GONE);
                question10.setVisibility(View.VISIBLE);
                btn10.setVisibility(View.VISIBLE);
                break;
            case R.id.q10:
                question10.setVisibility(View.GONE);
                btn10.setVisibility(View.GONE);
                question11.setVisibility(View.VISIBLE);
                btn11.setVisibility(View.VISIBLE);
                break;
            case R.id.q11:
                question11.setVisibility(View.GONE);
                btn11.setVisibility(View.GONE);
                question12.setVisibility(View.VISIBLE);
                btn12.setVisibility(View.VISIBLE);
                break;
            case R.id.q12:
                question12.setVisibility(View.GONE);
                btn12.setVisibility(View.GONE);
                question13.setVisibility(View.VISIBLE);
                gotomainbtn.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setUp(){

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
        gotomainbtn = (Button)findViewById(R.id.gotoMain);
        Spinner odq1Spinner = (Spinner)findViewById(R.id.odq1);
        ArrayAdapter odq1Adapter = ArrayAdapter.createFromResource(this,
                R.array.odq1, android.R.layout.simple_spinner_item);
        odq1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        odq1Spinner.setAdapter(odq1Adapter);

        Spinner odq2Spinner = (Spinner)findViewById(R.id.odq2);
        ArrayAdapter odq2Adapter = ArrayAdapter.createFromResource(this,
                R.array.odq2, android.R.layout.simple_spinner_item);
        odq2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        odq2Spinner.setAdapter(odq2Adapter);

        Spinner odq3Spinner = (Spinner)findViewById(R.id.odq3);
        ArrayAdapter odq3Adapter = ArrayAdapter.createFromResource(this,
                R.array.odq3, android.R.layout.simple_spinner_item);
        odq3Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        odq3Spinner.setAdapter(odq3Adapter);

        Spinner odq4Spinner = (Spinner)findViewById(R.id.odq4);
        ArrayAdapter odq4Adapter = ArrayAdapter.createFromResource(this,
                R.array.odq4, android.R.layout.simple_spinner_item);
        odq4Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        odq4Spinner.setAdapter(odq4Adapter);

        Spinner odq5Spinner = (Spinner)findViewById(R.id.odq5);
        ArrayAdapter odq5Adapter = ArrayAdapter.createFromResource(this,
                R.array.odq5, android.R.layout.simple_spinner_item);
        odq5Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        odq5Spinner.setAdapter(odq5Adapter);

        Spinner odq6Spinner = (Spinner)findViewById(R.id.odq6);
        ArrayAdapter odq6Adapter = ArrayAdapter.createFromResource(this,
                R.array.odq6, android.R.layout.simple_spinner_item);
        odq6Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        odq6Spinner.setAdapter(odq6Adapter);

        Spinner pnq1Spinner = (Spinner)findViewById(R.id.pnq1);
        ArrayAdapter pnq1Adapter = ArrayAdapter.createFromResource(this,
                R.array.pnq1, android.R.layout.simple_spinner_item);
        pnq1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pnq1Spinner.setAdapter(pnq1Adapter);

        Spinner pnq2Spinner = (Spinner)findViewById(R.id.pnq2);
        ArrayAdapter pnq2Adapter = ArrayAdapter.createFromResource(this,
                R.array.pnq2, android.R.layout.simple_spinner_item);
        pnq2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pnq2Spinner.setAdapter(pnq2Adapter);

        Spinner pnq3Spinner = (Spinner)findViewById(R.id.pnq3);
        ArrayAdapter pnq3Adapter = ArrayAdapter.createFromResource(this,
                R.array.pnq3, android.R.layout.simple_spinner_item);
        pnq3Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pnq3Spinner.setAdapter(pnq3Adapter);

        Spinner pnq4Spinner = (Spinner)findViewById(R.id.pnq4);
        ArrayAdapter pnq4Adapter = ArrayAdapter.createFromResource(this,
                R.array.pnq4, android.R.layout.simple_spinner_item);
        pnq4Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pnq4Spinner.setAdapter(pnq4Adapter);

        Spinner pnq5Spinner = (Spinner)findViewById(R.id.pnq5);
        ArrayAdapter pnq5Adapter = ArrayAdapter.createFromResource(this,
                R.array.pnq5, android.R.layout.simple_spinner_item);
        pnq5Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pnq5Spinner.setAdapter(pnq5Adapter);

        Spinner pnq6Spinner = (Spinner)findViewById(R.id.pnq6);
        ArrayAdapter pnq6Adapter = ArrayAdapter.createFromResource(this,
                R.array.pnq6, android.R.layout.simple_spinner_item);
        pnq6Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pnq6Spinner.setAdapter(pnq6Adapter);

        Spinner pnq7Spinner = (Spinner)findViewById(R.id.pnq7);
        ArrayAdapter pnq7Adapter = ArrayAdapter.createFromResource(this,
                R.array.pnq7, android.R.layout.simple_spinner_item);
        pnq7Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pnq7Spinner.setAdapter(pnq7Adapter);
    }
}
