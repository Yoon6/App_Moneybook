package com.example.doit.wapproject2_test1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.doit.wapproject2_test1.fragment.MainFragment;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        Fragment fragment = new MainFragment();
        fragment.setArguments(bundle);

        Intent change = new Intent(SubActivity.this, MainActivity.class);
        startActivity(change);
    }


}
