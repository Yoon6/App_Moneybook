package com.example.doit.wapproject2_test1;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.example.doit.wapproject2_test1.entity.Consume;
import com.example.doit.wapproject2_test1.fragment.MainFragment;
import com.example.doit.wapproject2_test1.fragment.SettingFragment;
import com.example.doit.wapproject2_test1.fragment.WriteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ViewModel mViewModel;
    public static final int NEW_CONSUME_FRAGMENT_REQUEST_CODE = 1;

    //bottomNavi
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MainFragment mainFragment = new MainFragment();
    private WriteFragment writeFragment = new WriteFragment();
    private SettingFragment settingFragment = new SettingFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bottomNavi
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, mainFragment).commitAllowingStateLoss();

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
