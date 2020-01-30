package com.example.doit.wapproject2_test1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
                {"식비", "생활", "패션", "쇼핑", "교통", "문화여가", "교육학습", "여행", "선물", "유흥", "의료건강", "카페", "미용", "통신"};
        final Spinner writeCategoryList = v.findViewById(R.id.writeCategory);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        writeCategoryList.setAdapter(adapter);

        writeDate=v.findViewById(R.id.writeDate);
        writePlace = v.findViewById(R.id.writePlace);
        writeCost = v.findViewById(R.id.writeCost);
        writeRadioButton1=v.findViewById(R.id.writeRadioButton1);
        writeRadioButton2=v.findViewById(R.id.writeRadioButton2);

        // 날짜는 현재 날짜로 고정
        // 현재 시간 구하기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        // 출력될 포맷 설정
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년MM월dd일");
        writeDate.setText(simpleDateFormat.format(date));

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
                if(paramCost.isEmpty()){  // 가격을 기입하지 않을 경우 리스트에 요소 추가하지 않음
                    Toast.makeText(getActivity().getApplicationContext(),"양식을 완성해주세요.",Toast.LENGTH_LONG).show();
                }
                else {
                    buttonClicked(v,paramCategory,paramCost,paramPlace,paramDate,paramState);
                    writeCost.setText(null);
                    writePlace.setText(null);
                    Toast.makeText(getActivity().getApplicationContext(),"입력되었습니다.",Toast.LENGTH_LONG).show();

                }

            }
        } );

/*
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //submit 버튼 입력
                Intent intent = new Intent(WriteFragment.this.getActivity() , MainFragment.class);
                intent.putExtra("place",writePlace.getText().toString());
                startActivity(intent);
            }
        });
*/

        final String [] texts = {"Place", "Cost"};
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*cancel 버튼 입력*/
                for (int i = 0; i < 2; i++) {
                    int resID = getResources().getIdentifier("write"+texts[i],
                            "id", "com.example.doit.wapproject2_test1");
                    ((EditText) v.findViewById(resID)).setText("");
                }
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
}