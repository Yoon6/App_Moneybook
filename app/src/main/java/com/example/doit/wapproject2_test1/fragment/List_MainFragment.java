package com.example.doit.wapproject2_test1.fragment;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doit.wapproject2_test1.PreferenceManager;
import com.example.doit.wapproject2_test1.R;
import com.example.doit.wapproject2_test1.SwipeController;
import com.example.doit.wapproject2_test1.SwipeControllerActions;
import com.example.doit.wapproject2_test1.ViewModel;
import com.example.doit.wapproject2_test1.entity.Consume;
import com.example.doit.wapproject2_test1.list_Adapter;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class List_MainFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    ViewModel mViewModel;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


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

    private List<Consume> consumes = new ArrayList<>(); // Cached copy of words

    String strDate;

    SwipeController swipeController = null;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        System.out.println("온액티비티크리에이티드");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        System.out.println("온크리이에이트");


    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_list__main,container,false);

        System.out.println("온크리에이트뷰 리스트프래그먼트");
        DecimalFormat format = new DecimalFormat("###,###,###,###");

        mContext = getActivity();
        text_income=v.findViewById(R.id.textView_income);
        text_spend=v.findViewById(R.id.textView_spend);

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
                Calendar cal = horizontalCalendar.getDateAt(position);

                strDate = dateFormat.format(cal.getTime());
                incomeMoney = PreferenceManager.getInt(mContext, strDate+"income");
                spendMoney = PreferenceManager.getInt(mContext, strDate+"spend");
                text_income.setText("수입 : + "+format.format(incomeMoney)+" 원");
                text_spend.setText("지출 : - "+format.format(spendMoney)+" 원");

                //해당 날짜 값을 setFilter에 넣으면 그 날짜에 관한 정보를 db에서 가져옴
                mViewModel.setFilter(strDate);
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



        mViewModel = ViewModelProviders.of(getActivity()).get(ViewModel.class);

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        String stringNow = dateFormat.format(mDate);
        mViewModel.setFilter(stringNow);

        mViewModel.getOrderByDate().observe(this, new Observer<List<Consume>>() {
            @Override
            public void onChanged(@NonNull List<Consume> consumes) {
                list_Adapter.setConsumes(consumes);
            }
        });

        incomeMoney = PreferenceManager.getInt(mContext, stringNow+"income");
        spendMoney = PreferenceManager.getInt(mContext, stringNow+"spend");
        text_income.setText("수입 : + "+format.format(incomeMoney)+" 원");
        text_spend.setText("지출 : - "+format.format(spendMoney)+" 원");

        SwipeController swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                Consume consume = list_Adapter.getConsumeAtPosition(position);



                if(consume.getState().equals("-")){
                    spendMoney = PreferenceManager.getInt(mContext, consume.getDate()+"spend");
                    spendMoney -= Integer.parseInt(consume.getCost().replace(",",""));
                    PreferenceManager.setInt(mContext, consume.getDate()+"spend", spendMoney);
                }else{
                    incomeMoney = PreferenceManager.getInt(mContext, consume.getDate()+"income");
                    incomeMoney -= Integer.parseInt(consume.getCost().replace(",",""));
                    PreferenceManager.setInt(mContext, consume.getDate()+"income", incomeMoney);
                }

                text_income.setText("수입 : + "+format.format(incomeMoney)+" 원");
                text_spend.setText("지출 : - "+format.format(spendMoney)+" 원");

                mViewModel.delete(consume);
            }

            @Override
            public void onLeftClicked(int position) {
                super.onLeftClicked(position);

                Consume consume = list_Adapter.getConsumeAtPosition(position);

                Bundle bundle = new Bundle();

                bundle.putInt("position", position);
                bundle.putInt("id", consume.getId());
                bundle.putString("state", consume.getState());
                bundle.putString("category", consume.getCategory());
                bundle.putString("place", consume.getPlace());
                bundle.putString("cost", consume.getCost());
                bundle.putString("date", consume.getDate());

                EditDialogFragment e = EditDialogFragment.getInstance();
                e.setArguments(bundle);
                e.show(getActivity().getSupportFragmentManager(), EditDialogFragment.TAG_EVENT_DIALOG);

            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(list_recyclerView);

        list_recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });



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

    public String getSelectedDate() {
        return strDate;
    }


}
