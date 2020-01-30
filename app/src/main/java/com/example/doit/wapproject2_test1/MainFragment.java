package com.example.doit.wapproject2_test1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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



import java.util.ArrayList;
import java.text.DecimalFormat;

public class MainFragment extends Fragment {


    private static final String SETTINGS_CATEGORY="settings_category";
    private static final String SETTINGS_PLACE="settings_place";
    private static final String SETTINGS_PRICE="settings_price";
    private static final String SETTINGS_DATE="settings_date";
    //recyclerview
    private RecyclerView list_recyclerView;
    private RecyclerView.Adapter list_Adapter;
    private RecyclerView.LayoutManager list_layoutManager;
    private ArrayList<list_Data> listDataset;

    private ArrayList<String> list_category= new ArrayList<>();
    private ArrayList<String> list_place = new ArrayList<>();
    private ArrayList<String> list_price= new ArrayList<>();
    private ArrayList<String> list_date= new ArrayList<>();
    private ArrayList<String> list_state= new ArrayList<>();


    private ArrayList<String> storedCategoryData= new ArrayList<>();
    private ArrayList<String> storedCostData= new ArrayList<>();
    private ArrayList<String> storedPlaceData= new ArrayList<>();
    private ArrayList<String> storedDateData= new ArrayList<>();
    private ArrayList<String> storedStateData= new ArrayList<>();


    public TextView totalMoney;
    public int int_totalMoney=0;
    private String totalMoney_final="0";


    public void AppendItem(String category, String cost, String place, String date, String state){
        System.out.println("AppendItem 수행 ");

        list_category.add(0,category);
        list_price.add(0,cost);
        list_place.add(0,place);
        list_date.add(0,date);
        list_state.add(0,state);



    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_main,container,false);



        //컴마찍는 함수
        DecimalFormat formatter=new DecimalFormat("###,###,###");
        totalMoney = v.findViewById(R.id.totalMoney);

        //다시 불러와도 총합으로 계산되게 해야됨
        int_totalMoney=0;
        for(int i=0;i<list_price.size();i++){
            int list_price_int=Integer.parseInt(list_price.get(i));
            if(list_state.get(i).equals("expense")) {
                int_totalMoney -= list_price_int;
            }
            else if(list_state.get(i).equals("income")){
                int_totalMoney += list_price_int;
            }
        }
        totalMoney_final=formatter.format(int_totalMoney);
        totalMoney.setText(totalMoney_final);

        System.out.println("메인 fragment oncreateview 수행 ");

        //recyclerview
        list_recyclerView= v.findViewById(R.id.my_recycler_view); //activity_list_recycler 의 리스트 뷰
        list_recyclerView.setHasFixedSize(true);
        list_layoutManager=new LinearLayoutManager(getActivity());
        list_recyclerView.setLayoutManager(list_layoutManager);
        list_recyclerView.scrollToPosition(0);
        list_Adapter = new list_Adapter(listDataset);
        list_recyclerView.setAdapter(list_Adapter);
        list_recyclerView.setItemAnimator(new DefaultItemAnimator());

        System.out.println("메인 fragment oncreateview 수행 끝끝끝");

        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        System.out.println("메인프레그 생성");
        prepareData();


    }

    public void reFreshArr(ArrayList<String> categorydata,ArrayList<String> costdata,ArrayList<String> placedata,ArrayList<String> datedata, ArrayList<String> statedata){
        storedCategoryData = categorydata;
        storedCostData = costdata;
        storedPlaceData = placedata;
        storedDateData = datedata;
        storedStateData = statedata;
        for(int n = 0; n < storedCostData.size();n ++){
//            System.out.println(storedCostData.size());
//            System.out.println("오픈됨 "+storedCategoryData.get(n)+storedCostData.get(n)+storedPlaceData.get(n)+storedDateData.get(n)+storedStateData.get(n));
            AppendItem(storedCategoryData.get(n),storedCostData.get(n),storedPlaceData.get(n),storedDateData.get(n),storedStateData.get(n));
        }
    }

    public void prepareData(){
        System.out.println("prepareData 수행 ");

        listDataset = new ArrayList<>();

        //컴마찍는 함수
        DecimalFormat formatter=new DecimalFormat("###,###,###");
        String[] list_price_final=new String[list_category.size()];

        //데이터 보내기
        for(int i=0;i<list_price.size();i++) {

            //컴마
            int list_price_int=Integer.parseInt(list_price.get(i));
            list_price_final[i]=formatter.format(list_price_int);
            //recyclerview
            if(list_state.get(i).equals("expense")) {
                listDataset.add(new list_Data(list_category.get(i), list_place.get(i), "-" + list_price_final[i], list_date.get(i)));
            }else if(list_state.get(i).equals("income")){
                listDataset.add(new list_Data(list_category.get(i), list_place.get(i), "+" + list_price_final[i],list_date.get(i)));

            }

        }
    }




}
