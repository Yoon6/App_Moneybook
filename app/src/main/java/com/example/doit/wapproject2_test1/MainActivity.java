package com.example.doit.wapproject2_test1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.example.doit.wapproject2_test1.entity.Consume;
import com.example.doit.wapproject2_test1.fragment.List_MainFragment;
import com.example.doit.wapproject2_test1.fragment.MainFragment;
import com.example.doit.wapproject2_test1.fragment.SettingFragment;
import com.example.doit.wapproject2_test1.fragment.WriteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements MainFragment.OnFragmentInteractionListener, List_MainFragment.OnFragmentInteractionListener {

    private ViewModel mViewModel;
    public static final int NEW_CONSUME_FRAGMENT_REQUEST_CODE = 1;
    private ViewPager mViewPager;

    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;

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
        transaction.replace(R.id.nav_host_fragment, new MainFragment());
        transaction.commit();

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

    @Override
    public void messageFromParentFragment(Uri uri) {
        Log.i("TAG", "received communication from parent fragment");
    }

    @Override
    public void messageFromChildFragment(Uri uri) {
        Log.i("TAG", "received communication from child fragment");
    }

    @Override
    public void onBackPressed() {
        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        // super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }

}
