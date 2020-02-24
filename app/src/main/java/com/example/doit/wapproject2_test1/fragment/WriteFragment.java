package com.example.doit.wapproject2_test1.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

import com.example.doit.wapproject2_test1.R;
import com.example.doit.wapproject2_test1.ViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WriteFragment extends Fragment implements View.OnClickListener {
    Spinner writeCategoryList;

    private ViewModel viewModel;

    Button submitBtn;
    Button cancelBtn;

    RadioButton writeRadioButton1;
    RadioButton writeRadioButton2;
    EditText writePlace;
    TextView writeDate;
    EditText writeCost;
    CardView cardViewCategory;
    TextView textViewCategory;


    String radio_state;

    public static final String EXTRA_REPLY = "com.example.doit.wordlistsql.REPLY";

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.KOREAN);

    DatePickerDialog datePickerDialog;
    Calendar dateCalendar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_write, container, false);

        findViewsById(v);


        writeRadioButton1.setOnClickListener(this::onClick);
        writeRadioButton2.setOnClickListener(this::onClick);

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
        Calendar maxDate = Calendar.getInstance();

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


        long now = System.currentTimeMillis();
        Date mdate = new Date(now);
        datePickerDialog.getDatePicker().setMaxDate(mdate.getTime());

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

        cardViewCategory = v.findViewById(R.id.card_view_write3);
        textViewCategory = v.findViewById(R.id.textView2);
    }

    @Override
    public void onClick(View view) {
        if (view == writeDate) {
            datePickerDialog.show();
        } else if (view == submitBtn) {
            submitMsg();
        } else if (view == cancelBtn) {
            cancelMsg();
        } else if (view == writeRadioButton1){
            cardViewCategory.setVisibility(View.VISIBLE);
        } else if (view == writeRadioButton2){
            cardViewCategory.setVisibility(View.GONE);
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

                        if(!(TextUtils.isEmpty(writeCost.getText()) && TextUtils.isEmpty(writeDate.getText()) && TextUtils.isEmpty(writePlace.getText()))) {
                            if(writeRadioButton1.isChecked() == true){
                                radio_state = "-";
                            }else{
                                radio_state = "+";
                            }
                            String Cost = writeCost.getText().toString();
                            String Date = writeDate.getText().toString();
                            String Place = writePlace.getText().toString();
                            String Category = writeCategoryList.getSelectedItem().toString();

                            Bundle bundle = new Bundle();

                            bundle.putString("state", radio_state);
                            bundle.putString("cost", Cost);
                            bundle.putString("date", Date);
                            bundle.putString("place", Place);
                            bundle.putString("category", Category);

                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                            List_MainFragment list_mainFragment = new List_MainFragment();
                            list_mainFragment.setArguments(bundle);

                            fragmentTransaction.replace(R.id.nav_host_fragment, list_mainFragment);
                            fragmentTransaction.commit();

                        } else {

                        }
                        /*
                        if (TextUtils.isEmpty(writeCost.getText())) {
                            System.out.println("Write 값이 없음");
                            getActivity().setResult(RESULT_CANCELED, replyIntent);
                        } else {
                            System.out.println("Write 인텐트 시작");
                            String word = writeCost.getText().toString();
                            replyIntent.putExtra(EXTRA_REPLY, word);
                            getActivity().setResult(RESULT_OK, replyIntent);
                            System.out.println("Write 인텐트 끝");
                        }

                        getActivity().finish();
                         */

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