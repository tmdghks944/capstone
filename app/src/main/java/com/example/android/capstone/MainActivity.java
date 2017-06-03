package com.example.android.capstone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.SearchView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mainLinear;
    LinearLayout question1;
    LinearLayout question17;
    Button btn1;
    Button gotomainbtn;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GlobalVariable globalvariable = (GlobalVariable)getApplication();
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
            case R.id.main4:
                Intent intent = new Intent(this,FirebaseActivity.class);
                startActivity(intent);
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
        final GlobalVariable globalvariable = (GlobalVariable)getApplication();
        final Intent intent = new Intent(this, ResultActivity.class);

        MenuItem myActionMenuItem = menu.findItem( R.id.search_cosmetic);
        searchView = (SearchView) myActionMenuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast like print
                globalvariable.setdetailname(query);
                startActivity(intent);

                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                //myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });
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
        else if(id==R.id.search_cosmetic){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
