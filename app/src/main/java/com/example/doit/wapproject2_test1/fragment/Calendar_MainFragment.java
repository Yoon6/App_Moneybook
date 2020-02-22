package com.example.doit.wapproject2_test1.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doit.wapproject2_test1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Calendar_MainFragment extends Fragment {


    public Calendar_MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list__main, container, false);

        return  v;
    }

}
