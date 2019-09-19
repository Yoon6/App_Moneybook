package com.example.doit.wapproject2_test1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class list_Adapter extends RecyclerView.Adapter<list_Adapter.MyViewHolder> {
    private ArrayList<list_Data> list_Dataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView textView_category;
        public TextView textView_place;
        public TextView textView_price;

        public MyViewHolder(View view) {
            super(view);

            textView_category = view.findViewById(R.id.textView_recycler_category);
            textView_place = view.findViewById(R.id.textView_recycler_place);
            textView_price = view.findViewById(R.id.textView_recycler_price);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public list_Adapter(ArrayList<list_Data> listDataset) {

        list_Dataset = listDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public list_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_writelist_recyclerview, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView_category.setText(list_Dataset.get(position).category);
        holder.textView_place.setText(list_Dataset.get(position).place);
        holder.textView_price.setText(list_Dataset.get(position).price);

   }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return list_Dataset.size();
    }



}


