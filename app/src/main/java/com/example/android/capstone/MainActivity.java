package com.example.android.capstone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mainLinear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLinear = (LinearLayout) findViewById(R.id.mainLinear);
    }
    public void clickMain(View view) {
        switch (view.getId()) {
            case R.id.main1:
                Intent menu1Intent = new Intent(this,Menu1Activity.class);
                startActivity(menu1Intent);
                break;
            case R.id.main2:
                Intent menu2Intent = new Intent(this,Menu2Activity.class);
                startActivity(menu2Intent);
                break;
            case R.id.main3:
                Intent photoIntent = new Intent(this,CameraActivity.class);
                startActivity(photoIntent);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        if (mainLinear.getVisibility() == View.VISIBLE) {
            finish();
            return;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.skin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent startSettingsActivity = new Intent(this, SettingActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
