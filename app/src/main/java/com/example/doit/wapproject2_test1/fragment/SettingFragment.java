package com.example.doit.wapproject2_test1.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doit.wapproject2_test1.R;

public class SettingFragment extends Fragment {


    private EditText et_set_total;
    private EditText et_set_income;
    private EditText et_set_expense;
    private Button bt_set_submit;
    private Button bt_set_cancel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_setting,container,false);
        et_set_total=v.findViewById(R.id.et_set_total);
        et_set_income=v.findViewById(R.id.et_set_income);
        et_set_expense=v.findViewById(R.id.et_set_expense);
        bt_set_submit=v.findViewById(R.id.bt_set_submit);
        bt_set_cancel=v.findViewById(R.id.bt_set_cancel);
        bt_set_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_set_total.setText(null);
                et_set_expense.setText(null);
                et_set_income.setText(null);
                Toast.makeText(getActivity().getApplicationContext(), "입력되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        bt_set_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_set_total.setText(null);
                et_set_expense.setText(null);
                et_set_income.setText(null);
                Toast.makeText(getActivity().getApplicationContext(),"취소했습니다.",Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }
}