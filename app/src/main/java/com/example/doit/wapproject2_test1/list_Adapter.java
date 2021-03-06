package com.example.doit.wapproject2_test1;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.doit.wapproject2_test1.entity.Consume;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class list_Adapter extends RecyclerView.Adapter<list_Adapter.MyViewHolder> {

    private final LayoutInflater mInflater;
    private List<Consume> consumes = new ArrayList<>(); // Cached copy of words

    public Consume getConsumeAtPosition(int position){
        return consumes.get(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView costview;
        private final TextView categoryview;
        private final TextView placeview;
        //private final TextView dateview;


        private MyViewHolder(View itemView) {
            super(itemView);

            costview = itemView.findViewById(R.id.textView_recycler_price);
            placeview = itemView.findViewById(R.id.textView_recycler_place);
            categoryview = itemView.findViewById(R.id.textView_recycler_category);
            //dateview = itemView.findViewById(R.id.recycler_list_Date);

        }
    }

    public list_Adapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.fragment_writelist_recyclerview, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (consumes != null) {
            Consume current = consumes.get(position);

            if(current.getState().equals("-")) {
                holder.costview.setTextColor(Color.parseColor("#e57f8f"));
            } else if(current.getState().equals("+")) {
                holder.costview.setTextColor(Color.parseColor("#000000"));
            }
            holder.costview.setText(current.getState() + " " + current.getCost() + " 원");
            holder.categoryview.setText(current.getCategory());
            holder.placeview.setText(current.getPlace());
            //holder.dateview.setText(current.getDate());
        } else {
            // Covers the case of data not being ready yet.
            holder.costview.setText("Default");
            holder.placeview.setText("Default");
            holder.categoryview.setText("Default");
            //holder.dateview.setText("Default");
        }
    }


    public void setConsumes(List<Consume> consumes){
        this.consumes = consumes;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (consumes != null)
            return consumes.size();
        else return 0;
    }


}

