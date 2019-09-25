package com.example.doit.wapproject2_test1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class WriteFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*
        View v=inflater.inflate(R.layout.fragment_write,container,false);
        return v;
         */
        View v = inflater.inflate(R.layout.fragment_write, container, false);

        String [] values =
                {"식비", "생활", "패션", "쇼핑", "교통", "문화여가", "교육학습", "여행", "선물", "유흥", "의료건강", "카페", "미용", "통신"};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner_year);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        return v;
    }

}