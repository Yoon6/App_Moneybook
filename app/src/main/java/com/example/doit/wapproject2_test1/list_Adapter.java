package com.example.doit.wapproject2_test1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doit.wapproject2_test1.model.Consume;

import java.util.ArrayList;
/*

public class list_Adapter extends RecyclerView.Adapter<list_Adapter.MyViewHolder> {
    private ArrayList<Consume> consumes;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView textView_category;
        public TextView textView_place;
        public TextView textView_price;
        public TextView recycler_list_Date;

        public MyViewHolder(View view) {
            super(view);

            textView_category = view.findViewById(R.id.textView_recycler_category);
            textView_place = view.findViewById(R.id.textView_recycler_place);
            textView_price = view.findViewById(R.id.textView_recycler_price);
            recycler_list_Date=view.findViewById(R.id.recycler_list_Date);


        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public list_Adapter(Context context, ArrayList<Consume> consumes) {

        this.context=context;
        this.consumes = consumes;
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
        // holder.textView_category.setText(list_Dataset.get(position).category);
        // holder.textView_place.setText(list_Dataset.get(position).place);
        // holder.textView_price.setText(list_Dataset.get(position).price);
        // holder.recycler_list_Date.setText(list_Dataset.get(position).date);
   }

     // Return the size of your dataset (invoked by the layout manager)
     @Override
     public int getItemCount() {
         return consumes.size();
     }



}


 */

