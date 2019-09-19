package com.example.doit.wapproject2_test1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //bottomNavi
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MainFragment mainFragment = new MainFragment();
    private WriteFragment writeFragment = new WriteFragment();
    private SettingFragment settingFragment = new SettingFragment();
    public TextView textView_money;
    private String allmoney="4000000";
    private int format_allmoney;
    private String allmoney_final;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //bottomNavi
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, mainFragment).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

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

}
