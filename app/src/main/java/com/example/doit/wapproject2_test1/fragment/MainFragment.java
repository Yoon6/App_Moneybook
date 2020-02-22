package com.example.doit.wapproject2_test1.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.doit.wapproject2_test1.R;
import com.example.doit.wapproject2_test1.ViewModel;
import com.example.doit.wapproject2_test1.entity.Consume;
import com.example.doit.wapproject2_test1.list_Adapter;

import java.util.List;

import static android.app.Activity.RESULT_OK;


public class MainFragment extends Fragment {

    private ViewModel mViewModel;
    public static final int NEW_CONSUME_FRAGMENT_REQUEST_CODE = 1;

    //recyclerview
    private RecyclerView list_recyclerView;
    private list_Adapter list_Adapter;
    private RecyclerView.LayoutManager list_layoutManager;

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
        View v=inflater.inflate(R.layout.fragment_main,container,false);

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

        Bundle bundle = this.getArguments();
        if(bundle != null){
            int cost = Integer.parseInt(bundle.getString("cost"));
            String date = bundle.getString("date");
            String place = bundle.getString("place");
            String category = bundle.getString("category");

            Consume consume_cost = new Consume(place, cost, date, category);
            mViewModel.insert(consume_cost);
            Toast.makeText(getActivity(),"추가 완료",Toast.LENGTH_SHORT).show();
        }



        mViewModel.getAllConsumes().observe(this, new Observer<List<Consume>>() {
            @Override
            public void onChanged(@NonNull List<Consume> consumes) {
                list_Adapter.setConsumes(consumes);
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



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("메인프래그먼트-OnActivityResult");
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_CONSUME_FRAGMENT_REQUEST_CODE && resultCode == RESULT_OK) {
            System.out.println("뷰모델에 인서트");
            //Consume consume = new Consume(data.getStringExtra(WriteFragment.EXTRA_REPLY));
            //mViewModel.insert(consume);
        } else {

        }
    }

}

