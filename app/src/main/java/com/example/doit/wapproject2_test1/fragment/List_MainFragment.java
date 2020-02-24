package com.example.doit.wapproject2_test1.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doit.wapproject2_test1.PreferenceManager;
import com.example.doit.wapproject2_test1.R;
import com.example.doit.wapproject2_test1.ViewModel;
import com.example.doit.wapproject2_test1.entity.Consume;
import com.example.doit.wapproject2_test1.list_Adapter;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class List_MainFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private ViewModel mViewModel;
    public static final int NEW_CONSUME_FRAGMENT_REQUEST_CODE = 1;

    //recyclerview
    private RecyclerView list_recyclerView;
    private com.example.doit.wapproject2_test1.list_Adapter list_Adapter;
    private RecyclerView.LayoutManager list_layoutManager;

    private TextView text_income;
    private TextView text_spend;
    private int spendMoney = 0;
    private int incomeMoney = 0;
    private Context mContext;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_list__main,container,false);

        DecimalFormat format = new DecimalFormat("###,###,###,###");

        mContext = getActivity();
        //totalM=v.findViewById(R.id.totalMoney);

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(v, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(7)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                //do something
            }
        });


        //recyclerview
        list_recyclerView= v.findViewById(R.id.my_recycler_view);
        list_recyclerView.setHasFixedSize(true);
        list_layoutManager=new LinearLayoutManager(getActivity());
        list_recyclerView.setLayoutManager(list_layoutManager);
        list_recyclerView.scrollToPosition(0);
        list_Adapter = new list_Adapter(getActivity());
        list_recyclerView.setAdapter(list_Adapter);
        list_recyclerView.setItemAnimator(new DefaultItemAnimator());

        //totalMoney = PreferenceManager.getInt(mContext, "total");
        //totalM.setText(format.format(totalMoney));

        // Frag <-> ViewModel
        mViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            String state = bundle.getString("state");
            String cost = bundle.getString("cost"); // 돈
            String date = bundle.getString("date");
            String place = bundle.getString("place");
            String category = bundle.getString("category");

            //totalMoney = PreferenceManager.getInt(mContext, "total");
            if(state == "-"){
                //totalMoney = totalMoney - cost;
            }else{
                //totalMoney = totalMoney + cost;
                category = "수입";
            }
            //PreferenceManager.setInt(mContext, "total", totalMoney);
            //totalM.setText(format.format(totalMoney));


            Consume consume_cost = new Consume(state, place, cost, date, category);
            mViewModel.insert(consume_cost);
            Toast.makeText(getActivity(),"추가 완료",Toast.LENGTH_SHORT).show();
        }

        mViewModel.getAllConsumes().observe(this, new Observer<List<Consume>>() {
            @Override
            public void onChanged(@NonNull List<Consume> consumes) {
                list_Adapter.setConsumes(consumes);
            }
        });

        //


        return v;
    }

    @Override
    public void onResume() {
        //getActivity().setTitle(R.string.app_name);
        //getActivity().getActionBar().setTitle(R.string.app_name);
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void messageFromChildFragment(Uri uri);
    }

}
