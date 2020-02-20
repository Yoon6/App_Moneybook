package com.example.doit.wapproject2_test1.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doit.wapproject2_test1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class WriteFragment extends Fragment implements View.OnClickListener {
    Spinner writeCategoryList;

    Button submitBtn;
    Button cancelBtn;

    RadioButton writeRadioButton1;
    RadioButton writeRadioButton2;
    EditText writePlace;
    TextView writeDate;
    EditText writeCost;

    String radio_state;

    public static final String EXTRA_REPLY = "com.example.doit.wordlistsql.REPLY";

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.KOREAN);

    DatePickerDialog datePickerDialog;
    Calendar dateCalendar;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_write, container, false);

        findViewsById(v);


        // 스피너
        final String [] values =
                {"식비/생활", "패션/미용", "교통/통신", "문화/여가", "교육/학습", "의료/건강"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        writeCategoryList.setAdapter(adapter);

        setListeners();

        //For orientation change.
        if (savedInstanceState != null) {
            dateCalendar = Calendar.getInstance();
            if (savedInstanceState.getLong("dateCalendar") != 0)
                dateCalendar.setTime(new Date(savedInstanceState.getLong("dateCalendar")));
        }

        return v;
    }

    private void setListeners() {
        writeDate.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        dateCalendar = Calendar.getInstance();
                        dateCalendar.set(year, monthOfYear, dayOfMonth);
                        writeDate.setText(formatter.format(dateCalendar
                                .getTime()));
                    }

                }, newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));

        submitBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    // 프래그먼트 새로고침
    private void fg_refresh() {
        writeRadioButton1.setChecked(true);
        writePlace.setText(null);
        writeCost.setText(null);
        writeDate.setText(null);
        writeCategoryList.setSelection(0);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (dateCalendar != null)
            outState.putLong("dateCalendar", dateCalendar.getTime().getTime());
    }

    private void findViewsById(View v) {

        writeRadioButton1=v.findViewById(R.id.writeRadioButton1);
        writeRadioButton2=v.findViewById(R.id.writeRadioButton2);

        writeCategoryList = v.findViewById(R.id.writeCategory);
        writePlace = v.findViewById(R.id.writePlace);
        writeDate=v.findViewById(R.id.writeDate);
        writeDate.setInputType(InputType.TYPE_NULL);
        writeCost = v.findViewById(R.id.writeCost);

        submitBtn = v.findViewById(R.id.submitBtn);
        cancelBtn = v.findViewById(R.id.cancelBtn);
    }

    @Override
    public void onClick(View view) {
        if (view == writeDate) {
            datePickerDialog.show();
        } else if (view == submitBtn) {
            submitMsg();
        } else if (view == cancelBtn) {
            cancelMsg();
        }
    }

    // 취소 경고창
    public void cancelMsg() {
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getActivity());
        alert_confirm.setMessage("작성내용이 삭제됩니다.").setCancelable(false).setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 'YES'
                        fg_refresh();
                    }
                }).setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 'No'
                        return;
                    }
                });
        AlertDialog alert = alert_confirm.create();
        alert.show();

    }

    // 입력 확인창
    public void submitMsg() {
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getActivity());
        alert_confirm.setMessage("작성내용을 입력합니다.").setCancelable(false).setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 'YES'

                        Intent replyIntent = new Intent();
                        if (TextUtils.isEmpty(writeCost.getText())) {
                            getActivity().setResult(RESULT_CANCELED, replyIntent);
                        } else {
                            String word = writeCost.getText().toString();
                            replyIntent.putExtra(EXTRA_REPLY, word);
                            getActivity().setResult(RESULT_OK, replyIntent);
                        }

                        fg_refresh();
                    }
                }).setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 'No'
                        return;
                    }
                });
        AlertDialog alert = alert_confirm.create();
        alert.show();

    }

}