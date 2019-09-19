package com.example.doit.wapproject2_test1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainFragment extends Fragment {

    //recyclerview
    private RecyclerView list_recyclerView;
    private RecyclerView.Adapter list_Adapter;
    private RecyclerView.LayoutManager list_layoutManager;
    private ArrayList<list_Data> listDataset;
    private String allmoney="4000000";
    private int format_allmoney;
    private String allmoney_final;
    private String[] list_category={"식비","생활","통신","주거","식비","카페"};
    private String[] list_place={"맥도날드","다이소","휴대폰요금","월세","학식","스타벅스"};
    private String[] list_price={"7000","3000","30000","400000","4000","4900"};
    private int[] format_list_price={1,2,3,4,5,6};
    private String[] list_price_final={"1","2","3","4","5","6"};
    public TextView textView_money;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_main,container,false);

        textView_money = v.findViewById(R.id.textView_money1);

        //recyclerview
        list_recyclerView= (RecyclerView) v.findViewById(R.id.my_recycler_view); //activity_list_recycler 의 리스트 뷰
        list_recyclerView.setHasFixedSize(true);
        list_layoutManager=new LinearLayoutManager(getActivity());
        list_recyclerView.setLayoutManager(list_layoutManager);
        list_recyclerView.scrollToPosition(0);
        list_Adapter = new list_Adapter(listDataset);
        list_recyclerView.setAdapter(list_Adapter);
        list_recyclerView.setItemAnimator(new DefaultItemAnimator());
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        prepareData();
        /*
        //총자산표시
        DecimalFormat formatter2=new DecimalFormat("###,###,###");
        textView_money = findViewById(R.id.textView_money1);
        format_allmoney=Integer.parseInt(allmoney);
        allmoney_final=formatter2.format(format_allmoney);
        textView_money.setText(allmoney_final);
        */

    }

    private void prepareData(){

        listDataset = new ArrayList<>();

        //컴마찍는 함수
        DecimalFormat formatter=new DecimalFormat("###,###,###");

        //총자산 설정
        //format_allmoney=Integer.parseInt(allmoney);
        //allmoney_final=formatter.format(format_allmoney);
        //textView_money.setText(allmoney_final);

        // price 컴마 찍기
        for(int i=0;i<list_price.length;i++){
            format_list_price[i]=Integer.parseInt(list_price[i]);
            //format_allmoney -= format_list_price[i];
            list_price_final[i]=formatter.format(format_list_price[i]);
        }
        //데이터 보내기
        for(int i=0;i<list_category.length;i++) {
            listDataset.add(new list_Data(list_category[i], list_place[i], list_price_final[i]));
        }
    }
}
