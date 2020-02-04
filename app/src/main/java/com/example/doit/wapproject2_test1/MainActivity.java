package com.example.doit.wapproject2_test1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    //bottomNavi
    private FragmentManager fragmentManager = getSupportFragmentManager();
    //private MainFragment mainFragment = new MainFragment();
    private WriteFragment writeFragment = new WriteFragment();
    private SettingFragment settingFragment = new SettingFragment();
    private FragmentPage2 fragmentPage2 = new FragmentPage2();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bottomNavi
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragmentPage2).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

    }

    // 바텀 내비게이션바 클릭 트랜잭션
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();

                    switch(menuItem.getItemId())
                    {
                        case R.id.ic_main:
                            transaction.replace(R.id.nav_host_fragment, fragmentPage2).commitAllowingStateLoss();
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
