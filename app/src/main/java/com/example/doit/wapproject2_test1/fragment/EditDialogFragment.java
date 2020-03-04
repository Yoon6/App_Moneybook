package com.example.doit.wapproject2_test1.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.doit.wapproject2_test1.NumberTextWatcher;
import com.example.doit.wapproject2_test1.PreferenceManager;
import com.example.doit.wapproject2_test1.R;
import com.example.doit.wapproject2_test1.ViewModel;
import com.example.doit.wapproject2_test1.entity.Consume;
import com.example.doit.wapproject2_test1.list_Adapter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditDialogFragment extends DialogFragment implements View.OnClickListener {

    Spinner writeCategoryList;

    private ViewModel mViewModel;

    RadioButton writeRadioButton1;
    RadioButton writeRadioButton2;
    EditText writePlace;
    TextView writeDate;
    EditText writeCost;
    CardView cardViewCategory;
    TextView textViewCategory;

    TextView total_spend;
    TextView total_income;
    private com.example.doit.wapproject2_test1.list_Adapter list_Adapter;

    String radio_state;
    DecimalFormat format = new DecimalFormat("###,###,###,###");
    String edt_cost = "";
    int intCost;
    private int spendMoney = 0;
    private int incomeMoney = 0;
    private Context mContext;

    String state_b;
    String category;
    String place;
    String cost_b;
    String date;
    String state;
    int uid;

    private View header;

    public static final String EXTRA_REPLY = "com.example.doit.wordlistsql.REPLY";

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.KOREAN);
    public static final String TAG_EVENT_DIALOG = "dialog_event";
    DatePickerDialog datePickerDialog;
    Calendar dateCalendar;

    public EditDialogFragment(){}
    public static EditDialogFragment getInstance(){
        EditDialogFragment e = new EditDialogFragment();
        System.out.println("생성자");
        return e;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        System.out.println("온 크리에이트");
    }

    //@Nullable
    //@Override
    //public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
    //                         @Nullable Bundle savedInstanceState){
    //    View v = inflater.inflate(R.layout.fragment_edit, container);
    //    return v;
    //}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        int position = getArguments().getInt("position");

        uid = getArguments().getInt("id");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_edit, null);

        findViewsById(view);

        //header = getLayoutInflater().inflate(R.layout.fragment_list__main, null, false);
        total_income = view.findViewById(R.id.textView_income);
        total_spend = view.findViewById(R.id.textView_spend);


        String title = "수정";
        Toolbar toolbar = view.findViewById(R.id.my_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle the menu item
                dismiss();
                return true;
            }
        });
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setTitle(title);



        writeRadioButton1.setOnClickListener(this::onClick);
        writeRadioButton2.setOnClickListener(this::onClick);
        //long now = System.currentTimeMillis();
        //Date mDate = new Date(now);
        //String getCurrentTime = formatter.format(mDate);
        //writeDate.setText(getCurrentTime);

        writeCost.addTextChangedListener(new NumberTextWatcher(writeCost));


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


        builder.setView(view)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id)
                            {
                                mContext = getActivity();
                                mViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);


                                if(writeRadioButton1.isChecked() == true){
                                    state = "-";
                                }else{
                                    state = "+";
                                }
                                category = writeCategoryList.getSelectedItem().toString();
                                place = writePlace.getText().toString();
                                String cost = writeCost.getText().toString();
                                date = writeDate.getText().toString();

                                if(state_b.equals("-")){
                                    spendMoney = PreferenceManager.getInt(mContext, date+"spend");
                                    spendMoney -= Integer.parseInt(cost_b.replace(",",""));
                                    if(state.equals("-")){
                                        spendMoney += Integer.parseInt(cost.replace(",", ""));
                                        PreferenceManager.setInt(mContext, date+"spend", spendMoney);
                                    }else if (state.equals("+")){
                                        category = "수입";
                                        incomeMoney = PreferenceManager.getInt(mContext, date+"income");
                                        incomeMoney += Integer.parseInt(cost.replace(",", ""));
                                        PreferenceManager.setInt(mContext, date+"income", incomeMoney);
                                        PreferenceManager.setInt(mContext, date+"spend", spendMoney);
                                    }
                                }else if (state_b.equals("+")){
                                    incomeMoney = PreferenceManager.getInt(mContext, date+"income");
                                    incomeMoney -= Integer.parseInt(cost_b.replace(",",""));
                                    if(state.equals("-")){
                                        spendMoney = PreferenceManager.getInt(mContext, date+"spend");
                                        spendMoney += Integer.parseInt(cost.replace(",", ""));
                                        PreferenceManager.setInt(mContext, date+"income", incomeMoney);
                                        PreferenceManager.setInt(mContext, date+"spend", spendMoney);
                                    }else if (state.equals("+")){
                                        category = "수입";
                                        incomeMoney += Integer.parseInt(cost.replace(",", ""));
                                        PreferenceManager.setInt(mContext, date+"income", incomeMoney);
                                    }
                                }



                                System.out.println(uid);
                                Consume consume = new Consume(state, place, cost, date, category);
                                consume.setId(uid);
                                mViewModel.update(consume);
                                Toast.makeText(mContext, "수정완료", Toast.LENGTH_SHORT).show();

                            }
                        });

        Bundle bundle = getArguments();

        if(bundle != null){

            state_b = bundle.getString("state");
            category = bundle.getString("category");
            place = bundle.getString("place");
            cost_b = bundle.getString("cost");
            date = bundle.getString("date");

            if(state_b.equals("-")){
                writeRadioButton1.setChecked(true);
            }else if(state_b.equals("+")){
                writeRadioButton2.setChecked(true);
                cardViewCategory.setVisibility(View.GONE);

            }

            switch (category){
                case "식비/생활":
                    writeCategoryList.setSelection(0);
                    break;
                case "패션/미용":
                    writeCategoryList.setSelection(1);
                    break;
                case "교통/통신":
                    writeCategoryList.setSelection(2);
                    break;
                case "문화/여가":
                    writeCategoryList.setSelection(3);
                    break;
                case "교육/학습":
                    writeCategoryList.setSelection(4);
                    break;
                case "의료/건강":
                    writeCategoryList.setSelection(5);
                    break;
            }
            writePlace.setText(place);
            writeCost.setText(cost_b);
            writeDate.setText(date);
        }

        setCancelable(true);
        return builder.create();
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

    }

    // 프래그먼트 새로고침
    private void fg_refresh() {
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        writeDate.setText(formatter.format(mDate));
        writePlace.setText(null);
        writeCost.setText(null);
        writeRadioButton1.setChecked(true);
        cardViewCategory.setVisibility(View.VISIBLE);
        writeCategoryList.setSelection(0);
    }

    private void findViewsById(View v) {


        writeRadioButton1=v.findViewById(R.id.writeRadioButton1);
        writeRadioButton2=v.findViewById(R.id.writeRadioButton2);

        writeCategoryList = v.findViewById(R.id.writeCategory);
        writePlace = v.findViewById(R.id.writePlace);
        writeDate=v.findViewById(R.id.writeDate);
        writeDate.setInputType(InputType.TYPE_NULL);
        writeCost = v.findViewById(R.id.writeCost);


        cardViewCategory = v.findViewById(R.id.card_view_write3);
        textViewCategory = v.findViewById(R.id.textView2);


    }

    @Override
    public void onClick(View view) {
        if (view == writeDate) {
            datePickerDialog.show();
        } else if (view == writeRadioButton1){
            cardViewCategory.setVisibility(View.VISIBLE);
        } else if (view == writeRadioButton2){
            cardViewCategory.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        DisplayMetrics dm = getActivity().getApplicationContext().getResources().getDisplayMetrics();

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getDialog().getWindow().setLayout(width, (int)(height*0.75));
        super.onResume();
    }




}
