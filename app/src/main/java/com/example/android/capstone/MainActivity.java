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

import org.w3c.dom.Text;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mainLinear;
    LinearLayout question1;
    LinearLayout question17;
    Button btn1;
    Button gotomainbtn;
    SearchView searchView;
    TextView textView;
    private HashMap<String,Integer> warningmap;
    private String myType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.myTypeView);
        GlobalVariable globalvariable = (GlobalVariable)getApplication();

        if(globalvariable.getcameradone()==false){
            textView.setText("피부분석이 아직 이루어지지 않았습니다. 사진촬영을 진행해 주세요.");
        }
        else{
            myType = getUserType();
            settypenum(myType);
            globalvariable.settype_name(myType);
            warningmap = globalvariable.getwarn();
            String tempstring = getWarningIngredient(globalvariable.gettype_value());
            if(globalvariable.getwt_value()==-1){
                textView.setText("얼굴 인식에 실패하였습니다. 사진촬영을 다시 진행해주세요.");
            }
            else {
                textView.setText(globalvariable.getusername() + "님의 피부 타입은 " + myType + "입니다");
            }
        }
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
                GlobalVariable.menu1info=false;
                Intent intent = new Intent(this,Menu1Activity.class);
                startActivity(intent);
                break;
            case R.id.main5:
                Intent intent2 = new Intent(this,FirebaseActivity.class);
                startActivity(intent2);
                break;
        }
    }

    public void clickMainBtn1(View view) {
        switch (view.getId()) {
            case R.id.MainBtn1:
                GlobalVariable globalvariable = (GlobalVariable)getApplication();
                GlobalVariable.menu1info=true;
                Intent Intent = new Intent(this,Menu1Activity.class);
                startActivity(Intent);
                break;
        }
    }
    public void clickMainBtn2(View view) {
        switch (view.getId()) {
            case R.id.MainBtn2:
                Intent Intent = new Intent(this,SettingActivity.class);
                startActivity(Intent);
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
    public void onResume(){
        GlobalVariable globalvariable = (GlobalVariable)getApplication();
        if(globalvariable.getcameradone()==false){
            textView.setText("피부분석이 아직 이루어지지 않았습니다. 사진촬영을 진행해 주세요.");
        }
        else{
            myType = getUserType();
            settypenum(myType);
            globalvariable.settype_name(myType);
            warningmap = globalvariable.getwarn();
            String tempstring = getWarningIngredient(globalvariable.gettype_value());
            if(globalvariable.getwt_value()==-1){
                textView.setText("얼굴 인식에 실패하였습니다. 사진촬영을 다시 진행해주세요.");
            }
            else {
                textView.setText(globalvariable.getusername() + "님의 피부 타입은 " + myType + "입니다");
            }
        }
        super.onResume();
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

    public String getUserType(){
        String type="";
        GlobalVariable globalvariable = (GlobalVariable)getApplication();
        if(globalvariable.getod_value()<=16)
            type+='D';
        else
            type+='O';

        if(globalvariable.getsr_value()==0 && globalvariable.getsurvey_sr_value()<=8 )//sr이 0이더라도 알레르기가 있으면 s로 판단.
            type+='R';
        else
            type+='S';

        if(globalvariable.getpn_value()<=15)
            type+='N';
        else
            type+='P';

        if(globalvariable.getuser_age()<=29){
            if(globalvariable.getwt_value()>=24){
                type+='W';
            }
            else{
                type+='T';
            }
        }
        else if(globalvariable.getuser_age()<=39){
            if(globalvariable.getwt_value()>=38){
                type+='W';
            }
            else{
                type+='T';
            }
        }
        else if(globalvariable.getuser_age()<=49){
            if(globalvariable.getwt_value()>=51){
                type+='W';
            }
            else{
                type+='T';
            }
        }
        else if(globalvariable.getuser_age()<=59){
            if(globalvariable.getwt_value()>=62){
                type+='W';
            }
            else{
                type+='T';
            }
        }
        else if(globalvariable.getuser_age()<=69){
            if(globalvariable.getwt_value()>=68){
                type+='W';
            }
            else{
                type+='T';
            }
        }
        else{
            if(globalvariable.getwt_value()>=74){
                type+='W';
            }
            else{
                type+='T';
            }
        }

//        if(globalvariable.getwt_value()>=1)//나이따라.
//            type+='W';
//        else
//            type+='T';

        return type;
    }
    public String getWarningIngredient(int num){
        String str="";
        GlobalVariable globalvariable = (GlobalVariable)getApplication();
        if(num==1){
            str += "시나몬 오일, 이소프로필 미리스테이트, 코코아 버터, 페퍼민트 오일, 코코넛 오일";
            globalvariable.pushwarn("시나몬오일",1);
            globalvariable.pushwarn("이소프로필미리스테이트",1);
            globalvariable.pushwarn("코코아버터",1);
            globalvariable.pushwarn("페퍼민트오일",1);
            globalvariable.pushwarn("코코넛오일",1);
            globalvariable.pushwarn("정제수",1);
        }
        else if(num==2){
            str += "부틸 스테아레이트, 미리스틸 프로페오네이트, 이소 스테아릴, 이소 스테아레이트, 시나몬 오일";
            globalvariable.pushwarn("부틸스테아레이트",1);
            globalvariable.pushwarn("이소스테아릴",1);
            globalvariable.pushwarn("이소스테아레이트",1);
            globalvariable.pushwarn("시나몬오일",1);
            globalvariable.pushwarn("정제수",1);
        }
        else if(num==3){
            str += "아세틱 애씨드, 발삼, 벤조익 애씨드, 시나믹애씨드, 파라벤, 멘톨, 락틱애씨드";
            globalvariable.pushwarn("아세틱애씨드",1);
            globalvariable.pushwarn("발삼",2);
            globalvariable.pushwarn("이소스테아릴",1);
            globalvariable.pushwarn("이소스테아레이트",1);
            globalvariable.pushwarn("시나몬오일",1);
            globalvariable.pushwarn("파라벤",7);
            globalvariable.pushwarn("멘톨",1);
            globalvariable.pushwarn("락틱애씨드",4);
            globalvariable.pushwarn("정제수",1);
        }
        else if(num==4){
            str += "아세틱애씨드, 코코넛 오일, 알란토인, 알파-리포익애씨드, 발삼, 캠퍼, 페닐벤지미다졸";
            globalvariable.pushwarn("아세틱애씨드",1);
            globalvariable.pushwarn("코코넛오일",1);
            globalvariable.pushwarn("알란토인",1);
            globalvariable.pushwarn("알파-리포익애씨드",1);
            globalvariable.pushwarn("발삼",2);
            globalvariable.pushwarn("캠퍼",2);
            globalvariable.pushwarn("페닐벤지미다졸",3);
            globalvariable.pushwarn("정제수",1);
        }
        else if(num==5){
            str += "콩추출물, 야생암추출물, 레드클로버추출물, 체이스트리베리추출물, 바세린";
            globalvariable.pushwarn("콩추출물",1);
            globalvariable.pushwarn("야생암추출물",1);
            globalvariable.pushwarn("레드클로버추출물",1);
            globalvariable.pushwarn("체이스트리베리추출물",1);
            globalvariable.pushwarn("바세린",4);
            globalvariable.pushwarn("정제수",1);
        }
        else if(num==6){
            str += "샐러지추출물, 라임추출물, 당근추출물, AHA, 마그네슘아스코빌포스페이트";
            globalvariable.pushwarn("샐러지추출물",1);
            globalvariable.pushwarn("라임추출물",2);
            globalvariable.pushwarn("당근추출물",1);
            globalvariable.pushwarn("AHA",1);
            globalvariable.pushwarn("마그네슘아스코빌포스페이트",1);
            globalvariable.pushwarn("정제수",1);
        }
        else if(num==7){
            str += "미네랄오일, 해바라기씨오일, 바세린, 코코넛오일, 미리스틸프로페오네이트";
            globalvariable.pushwarn("미네랄오일",1);
            globalvariable.pushwarn("해바라기씨오일",2);
            globalvariable.pushwarn("바세린",4);
            globalvariable.pushwarn("코코넛오일",1);
            globalvariable.pushwarn("정제수",1);
        }
        else if(num==8){
            str += "바세린, 미네랄오일, 달맞이꽃오일";
            globalvariable.pushwarn("바세린",4);
            globalvariable.pushwarn("미네랄오일",1);
            globalvariable.pushwarn("달맞이꽃오일",1);
            globalvariable.pushwarn("정제수",1);
        }
        else if(num==9){
            str += "필라그리놀, 알로에베라, 캐모마일추출물, 고운오트밀가루, 녹차추출물, 판테놀";
            globalvariable.pushwarn("필라그리놀",1);
            globalvariable.pushwarn("알로에베라",3);
            globalvariable.pushwarn("캐모마일추출물",4);
            globalvariable.pushwarn("고운오트밀가루",1);
            globalvariable.pushwarn("녹차추출물",1);
            globalvariable.pushwarn("판테놀",1);
            globalvariable.pushwarn("정제수",1);
        }
        else if(num==10){
            str += "페퍼민트오일, 로즈오일, 디클로로펜, 이미다졸리디닐우레아, 트리클로산";
            globalvariable.pushwarn("페퍼민트오일",1);
            globalvariable.pushwarn("로즈오일",1);
            globalvariable.pushwarn("디클로로펜",1);
            globalvariable.pushwarn("이미다졸리디닐우레아",6);
            globalvariable.pushwarn("트리클로산",7);
            globalvariable.pushwarn("정제수",1);
        }
        else if(num==11){
            str += "마그네슘아스코빌포스페이트, 글루코노락톤, 프로필갈레이트, 피마자오일, 코발트, 알코올";
            globalvariable.pushwarn("마그네슘아스코빌포스페이트",1);
            globalvariable.pushwarn("글루코노락톤",1);
            globalvariable.pushwarn("프로필갈레이트",3);
            globalvariable.pushwarn("피마자오일",2);
            globalvariable.pushwarn("알코올",2);
            globalvariable.pushwarn("코발트",1);
            globalvariable.pushwarn("정제수",1);
        }
        else if(num==12){
            str += "이소프로필미리스테이트, 디클로로펜, 이미다졸리디닐우레아, 카톤CG, 트리클로산";
            globalvariable.pushwarn("이소프로필미리스테이트",1);
            globalvariable.pushwarn("디클로로펜",1);
            globalvariable.pushwarn("이미다졸리디닐우레아",6);
            globalvariable.pushwarn("카톤CG",1);
            globalvariable.pushwarn("트리클로산",7);
            globalvariable.pushwarn("정제수",1);
        }
        else if(num==13){
            str += "알코올, 소디움라우릴설페이트, 소디움라우레스설페이트파라벤";
            globalvariable.pushwarn("알코올",2);
            globalvariable.pushwarn("소디움라우릴설페이트",2);
            globalvariable.pushwarn("소디움라우레스설페이트파라벤",2);
            globalvariable.pushwarn("정제수",1);
        }
        else if(num==14){
            str += "에스트라디올, 콩추출물, 프로필갈레이트, 피마자오일, 코발트";
            globalvariable.pushwarn("에스트라디올",1);
            globalvariable.pushwarn("콩추출물",1);
            globalvariable.pushwarn("프로필갈레이트",3);
            globalvariable.pushwarn("피마자오일",2);
            globalvariable.pushwarn("코발트",1);
            globalvariable.pushwarn("정제수",1);
        }
        else if(num==15){
            str += "소디움라우릴설페이트, 소디움라우레스설페이트, 알코올, 글루코노락톤";
            globalvariable.pushwarn("소디움라우릴설페이트",2);
            globalvariable.pushwarn("소디움라우레스설페이트",2);
            globalvariable.pushwarn("알코올",2);
            globalvariable.pushwarn("글루코노락톤",1);
            globalvariable.pushwarn("정제수",1);
        }
        else if(num==16){
            str += "아세톤, 이소프로필알코올, 변성알코올, 메탄올, 벤질알코올, 에탄올";
            globalvariable.pushwarn("아세톤",3);
            globalvariable.pushwarn("이소프로필알코올",2);
            globalvariable.pushwarn("변성알코올",5);
            globalvariable.pushwarn("메탄올",1);
            globalvariable.pushwarn("벤질알코올",5);
            globalvariable.pushwarn("에탄올",2);
            globalvariable.pushwarn("정제수",1);
        }
        return str;
    }
    public void settypenum(String type){
        GlobalVariable globalvariable = (GlobalVariable)getApplication();
        if(type.equals("OSPW")){
            globalvariable.settype_value(1);
        }
        else if(type.equals("OSPT")){
            globalvariable.settype_value(2);
        }
        else if(type.equals("OSNW")){
            globalvariable.settype_value(3);
        }
        else if(type.equals("OSNT")){
            globalvariable.settype_value(4);
        }
        else if(type.equals("ORPW")){
            globalvariable.settype_value(5);
        }
        else if(type.equals("ORPT")){
            globalvariable.settype_value(6);
        }
        else if(type.equals("ORNW")){
            globalvariable.settype_value(7);
        }
        else if(type.equals("ORNT")){
            globalvariable.settype_value(8);
        }
        else if(type.equals("DSPW")){
            globalvariable.settype_value(9);
        }
        else if(type.equals("DSPT")){
            globalvariable.settype_value(10);
        }
        else if(type.equals("DSNW")){
            globalvariable.settype_value(11);
        }
        else if(type.equals("DSNT")){
            globalvariable.settype_value(12);
        }
        else if(type.equals("DRPW")){
            globalvariable.settype_value(13);
        }
        else if(type.equals("DRPT")){
            globalvariable.settype_value(14);
        }
        else if(type.equals("DRNW")){
            globalvariable.settype_value(15);
        }
        else if(type.equals("DRNT")){
            globalvariable.settype_value(16);
        }
    }
}
