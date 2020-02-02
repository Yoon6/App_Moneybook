package com.example.doit.wapproject2_test1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;


import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import org.json.JSONArray;
import android.content.SharedPreferences;
import android.util.Log;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements WriteFragment.CustomOnClickListener, WriteFragment.WriteValueSetListener {

    //bottomNavi
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MainFragment mainFragment = new MainFragment();
    private WriteFragment writeFragment = new WriteFragment();
    private SettingFragment settingFragment = new SettingFragment();

    private String newPlace;
    private String newCategory;
    private String newCost;
    private String newDate;
    private String newState;

    private ArrayList<String> categoryData= new ArrayList<>();
    private ArrayList<String> placeData= new ArrayList<>();
    private ArrayList<String> costData= new ArrayList<>();
    private ArrayList<String> dateData= new ArrayList<>();
    private ArrayList<String> stateData= new ArrayList<>();

    public void appendData(String categorydata,String costdata,String placedata,String datedata,String statedata){
        categoryData.add(categorydata);
        costData.add(costdata);
        placeData.add(placedata);
        dateData.add(datedata);
        stateData.add(statedata);
        System.out.println("Appendtest호출");
    }


    private static final String SETTINGS_PLAYER = "settings_player";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("오픈");
        categoryData = getStringArrayPref("category");
        costData = getStringArrayPref("cost");
        placeData = getStringArrayPref("place");
        dateData = getStringArrayPref("date");
        stateData = getStringArrayPref("state");

        mainFragment.reFreshArr(categoryData,costData,placeData,dateData,stateData);

        //bottomNavi
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, mainFragment).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());



//        for(int n = 0; n < testArr.size();n ++){
//            System.out.println("오픈됨 "+testArr.get(n));
//            mainFragment.AppendItem(newCategory,testArr.get(n),newPlace,newDate,newState);
//        }
//        mainFragment.prepareData();

/*
        //총자산표시
        DecimalFormat formatter2=new DecimalFormat("###,###,###");
        textView_money = findViewById(R.id.textView_money1);
        format_allmoney=Integer.parseInt(allmoney);
        allmoney_final=formatter2.format(format_allmoney);
        textView_money.setText(allmoney_final);*/
    }




    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.ic_main:
                    transaction.replace(R.id.nav_host_fragment, mainFragment).commitAllowingStateLoss();
                    break;
                case R.id.ic_write:
                    transaction.replace(R.id.nav_host_fragment, writeFragment).commitAllowingStateLoss();
                    break;
                case R.id.ic_settings_applications:
                    transaction.replace(R.id.nav_host_fragment, settingFragment).commitAllowingStateLoss();
                    break;
            }
            return true;
        }



    }

    @Override
    public void onSubmitClicked(View v) {
        System.out.println("여기는 액티비티");
        appendData(newCategory,newCost,newPlace,newDate,newState);
        for(int n = 0; n < costData.size();n ++){
            System.out.println(costData.get(n));
        }
        mainFragment.AppendItem(newCategory,newCost,newPlace,newDate,newState);

    }

    @Override
    public void writeValueSet(String category, String cost, String place,String date, String state)
    {
        newPlace= place;
        newCategory = category;
        newCost = cost;
        newDate=date;
        newState=state;
//        System.out.println(cost + "여기는 메인 액티비티");
    }

    public void setStringArrayPref(String key, ArrayList<String> values) {
        System.out.println("여기는 setStringArrayPref");

        SharedPreferences prefs = getSharedPreferences(SETTINGS_PLAYER, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }
        editor.apply();
    }

    public  ArrayList<String> getStringArrayPref(String key) {
        System.out.println("여기는 getStringArrayPref");

        SharedPreferences prefs = getSharedPreferences(SETTINGS_PLAYER, MODE_PRIVATE);
        String json = prefs.getString(key, null);
        ArrayList<String> urls = new ArrayList<String>();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;

    }

    @Override
    protected void onStop() {
        super.onStop();

        // Activity가 종료되기 전에 저장한다.
        //SharedPreferences를 sFile이름, 기본모드로 설정
        for(int n = 0; n < costData.size();n ++){
            System.out.println("종료직전 "+costData.get(n));
        }
        setStringArrayPref("category", categoryData);
        setStringArrayPref("cost", costData);
        setStringArrayPref("place", placeData);
        setStringArrayPref("date", dateData);
        setStringArrayPref("state", stateData);
        System.out.println("저장");

    }


}
