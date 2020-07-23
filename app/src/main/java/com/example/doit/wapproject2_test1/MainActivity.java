package com.example.doit.wapproject2_test1;

import android.os.Bundle;
import androidx.annotation.NonNull;

import com.example.doit.wapproject2_test1.fragment.Calendar_MainFragment;
import com.example.doit.wapproject2_test1.fragment.List_MainFragment;
import com.example.doit.wapproject2_test1.fragment.SettingFragment;
import com.example.doit.wapproject2_test1.fragment.WriteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ViewModel mViewModel;
    public static final int NEW_CONSUME_FRAGMENT_REQUEST_CODE = 1;
    private ViewPager mViewPager;

    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;

    //bottomNavi
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private WriteFragment writeFragment = new WriteFragment();
    private SettingFragment settingFragment = new SettingFragment();
    private List_MainFragment listMainFragment = new List_MainFragment();
    private Calendar_MainFragment calendarMainFragment = new Calendar_MainFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bottomNavi
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, listMainFragment).commitAllowingStateLoss();


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
                        case R.id.ic_calendar:
                            transaction.replace(R.id.nav_host_fragment, calendarMainFragment).commitAllowingStateLoss();
                            break;
                        case R.id.ic_list:
                            transaction.replace(R.id.nav_host_fragment, listMainFragment).commitAllowingStateLoss();
                            break;
                        case R.id.ic_write:
                            transaction.replace(R.id.nav_host_fragment, writeFragment).commitAllowingStateLoss();
                            break;
                        case R.id.ic_setting:
                            transaction.replace(R.id.nav_host_fragment, settingFragment).commitAllowingStateLoss();
                            break;
                    }
            return true;
        }

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
