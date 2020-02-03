package com.example.doit.wapproject2_test1;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.doit.wapproject2_test1.db.ConsumeDAO;
import com.example.doit.wapproject2_test1.model.Consume;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class MainFragment extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    Activity activity;
    ConsumeDAO consumeDAO;
    private GetConsmTask task;

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

    public TextView totalMoney;
    public int totalMoney_int=0;
    private String totalMoney_final="0";


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        activity = getActivity();
        consumeDAO = new ConsumeDAO(activity);


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_main,container,false);

        findViewsById(v);

        //컴마찍는 함수
        DecimalFormat formatter=new DecimalFormat("###,###,###,###");


        //다시 불러와도 총합으로 계산되게 해야됨
        totalMoney_int=0;
        for(int i=0;i<list_price.size();i++){
            int list_price_int=Integer.parseInt(list_price.get(i));
            if(list_state.get(i).equals("expense")) {
                totalMoney_int -= list_price_int;
            }
            else if(list_state.get(i).equals("income")){
                totalMoney_int += list_price_int;
            }
        }
        totalMoney_final=formatter.format(totalMoney_int);
        totalMoney.setText(totalMoney_final);

        //recyclerview

        list_recyclerView.setHasFixedSize(true);
        list_layoutManager=new LinearLayoutManager(getActivity());
        list_recyclerView.setLayoutManager(list_layoutManager);
        list_recyclerView.scrollToPosition(0);
        list_Adapter = new list_Adapter(listDataset);
        list_recyclerView.setAdapter(list_Adapter);
        list_recyclerView.setItemAnimator(new DefaultItemAnimator());


        return v;
    }

    public void prepareData(){

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
    public class GetConsmTask extends AsyncTask<Void, Void, ArrayList<Consume>> {

        private final WeakReference<Activity> activityWeakRef;

        public GetConsmTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected ArrayList<Consume> doInBackground(Void... arg0) {
            ArrayList<Consume> employeeList = consumeDAO.getConsumes();
            return employeeList;
        }

        @Override
        protected void onPostExecute(ArrayList<Consume> consmList) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
                Log.d("employees", consmList.toString());
                employees = consmList;
                if (consmList != null) {
                    if (consmList.size() != 0) {
                        list_Adapter = new list_Adapter(activity,
                                consmList);
                        list_recyclerView.setAdapter(list_Adapter);
                    } else {
                        Toast.makeText(activity, "No Employee Records",
                                Toast.LENGTH_LONG).show();
                    }
                }

            }
        }
    }

    private void findViewsById(View v) {
        totalMoney = v.findViewById(R.id.totalMoney);
        list_recyclerView= v.findViewById(R.id.my_recycler_view); //activity_list_recycler 의 리스트 뷰
    }

    @Override
    public void onResume() {
        //getActivity().setTitle(R.string.app_name);
        //getActivity().getActionBar().setTitle(R.string.app_name);
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> list, View arg1, int position,
                            long arg3) {
        Consume consume = (Consume) list.getItemAtPosition(position);

        if (consume != null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable("selectedConsumeList", consume);
            //**CustomEmpDialogFragment customEmpDialogFragment = new CustomEmpDialogFragment();
            //**customEmpDialogFragment.setArguments(arguments);
            //**customEmpDialogFragment.show(getFragmentManager(),
            //**        CustomEmpDialogFragment.ARG_ITEM_ID);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                   int position, long arg3) {
        Consume consume = (Consume) parent.getItemAtPosition(position);

        // Use AsyncTask to delete from database
        //**employeeDAO.delete(employee);
        //**employeeListAdapter.remove(employee);
        return true;
    }
}
