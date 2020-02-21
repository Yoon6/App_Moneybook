package com.example.doit.wapproject2_test1;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.doit.wapproject2_test1.entity.Consume;
import java.util.List;

public class list_Adapter extends RecyclerView.Adapter<list_Adapter.MyViewHolder> {

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView costview;
        private final TextView categoryvew;
        private final TextView placeview;

        private MyViewHolder(View itemView) {
            super(itemView);
            costview = itemView.findViewById(R.id.textView_recycler_price);
            placeview = itemView.findViewById(R.id.textView_recycler_place);
            categoryvew = itemView.findViewById(R.id.textView_recycler_category);
        }
    }

    private final LayoutInflater mInflater;
    private List<Consume> mConsumes; // Cached copy of words

    public list_Adapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.fragment_writelist_recyclerview, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (mConsumes != null) {
            Consume current = mConsumes.get(position);
            holder.costview.setText(current.getPrice());
            holder.categoryvew.setText(current.getCategory());
            holder.placeview.setText(current.getPlace());
        } else {
            // Covers the case of data not being ready yet.
            holder.costview.setText("Default");
            holder.placeview.setText("Default");
            holder.categoryvew.setText("Default");
        }
    }


    public void setConsumes(List<Consume> consumes){
        mConsumes = consumes;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mConsumes != null)
            return mConsumes.size();
        else return 0;
    }

}

