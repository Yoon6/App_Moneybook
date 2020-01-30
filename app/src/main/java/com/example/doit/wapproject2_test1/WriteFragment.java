package com.example.doit.wapproject2_test1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteFragment extends Fragment {
    Spinner writeCategoryList;

    Button submitBtn;
    Button cancelBtn;

    String paramPlace;
    String paramCost;
    String paramCategory;
    String paramDate;
    String paramState;
    String radio_state;

    RadioButton writeRadioButton1;
    RadioButton writeRadioButton2;
    EditText writePlace;
    EditText writeDate;
    EditText writeCost;
    private CustomOnClickListener customOnClickListener;
    private WriteValueSetListener writeValueSetListener;


    private void fg_refresh() {
        writeRadioButton1.setChecked(true);
        setDate();
        writePlace.setText(null);
        writeCost.setText(null);
        writeCategoryList.setSelection(0);
    }

    private void setDate(){
        // 날짜는 현재 날짜로 고정
        // 현재 시간 구하기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        // 출력될 포맷 설정
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        writeDate.setText(simpleDateFormat.format(date));
    }
    public interface CustomOnClickListener {
        void onSubmitClicked (View v);
    }

    public interface WriteValueSetListener {
        void writeValueSet(String category, String price, String cost,String date, String state);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_write, container, false);

        final String [] values =
                {"식비/생활", "패션/미용", "교통/통신", "문화/여가", "교육/학습", "의료/건강"};
        writeCategoryList = v.findViewById(R.id.writeCategory);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        writeCategoryList.setAdapter(adapter);

        writeDate=v.findViewById(R.id.writeDate);
        writePlace = v.findViewById(R.id.writePlace);
        writeCost = v.findViewById(R.id.writeCost);
        writeRadioButton1=v.findViewById(R.id.writeRadioButton1);
        writeRadioButton2=v.findViewById(R.id.writeRadioButton2);

        setDate();
        submitBtn = v.findViewById(R.id.submitBtn);
        cancelBtn = v.findViewById(R.id.cancelBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(writeRadioButton1.isChecked()==true){
                    radio_state="expense";
                }else{
                    radio_state="income";
                }
                paramState=radio_state;
                paramCategory = writeCategoryList.getSelectedItem().toString();
                paramCost = writeCost.getText().toString();
                paramPlace = writePlace.getText().toString();
                paramDate = writeDate.getText().toString();
                if(paramCost.isEmpty() || Integer.parseInt(paramCost)==0){  // 가격을 기입하지 않을 경우 리스트에 요소 추가하지 않음
                    Toast.makeText(getActivity().getApplicationContext(),"양식을 완성해주세요.",Toast.LENGTH_LONG).show();
                }
                else {
                    buttonClicked(v,paramCategory,paramCost,paramPlace,paramDate,paramState);
                    fg_refresh();
                    Toast.makeText(getActivity().getApplicationContext(),"입력되었습니다.",Toast.LENGTH_LONG).show();

                }

            }
        } );

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelMsg();
            }
        });

        return v;
    }

    public void buttonClicked(View v,String category, String cost, String place, String date,String state){
        System.out.println(cost+"여기는라이트프레그먼트");

//        Log.d("text","값은 : "+s);
        writeValueSetListener.writeValueSet(category,cost,place,date,state);
        customOnClickListener.onSubmitClicked(v);

    }

    @Override
    @Deprecated
    public void onAttach(Context context){
        System.out.println("어태치 되었음 라이트");

        super.onAttach(context);
        writeValueSetListener = (WriteValueSetListener)context;
        customOnClickListener = (CustomOnClickListener)context;
    }

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
}