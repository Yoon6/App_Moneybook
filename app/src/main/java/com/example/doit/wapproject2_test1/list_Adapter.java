package com.example.doit.wapproject2_test1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class list_Adapter extends RecyclerView.Adapter<list_Adapter.MyViewHolder> {
    //private ArrayList<list_Data> list_Dataset;
    private Context mContext;
    private Cursor mCursor;

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
    public list_Adapter(Context context, Cursor cursor) {

        this.mContext = context;
        mCursor = cursor;
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

    // 포지션을 입력 받아서 해당 데이터를 UI에 출력
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // 해당 포지션으로 이동한다.
        // false 가 리턴되면 데이터가 없거나 혹은 범위를 초과했다는 뜻이다.
        if(!mCursor.moveToPosition(position))
            return;
        // 열의 이름으로 열의 번호를 넘겨줌
        String state = mCursor.getString(mCursor.getColumnIndex(ConsumeListContract.ConsumeListEntry.COLUMN_STATE));
        String category = mCursor.getString(mCursor.getColumnIndex(ConsumeListContract.ConsumeListEntry.COLUMN_CATEGORY));
        String place = mCursor.getString(mCursor.getColumnIndex(ConsumeListContract.ConsumeListEntry.COLUMN_PLACE));
        String cost = mCursor.getString(mCursor.getColumnIndex(ConsumeListContract.ConsumeListEntry.COLUMN_COST));
        String date = mCursor.getString(mCursor.getColumnIndex(ConsumeListContract.ConsumeListEntry.COLUMN_DATE));

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView_category.setText(category);
        holder.textView_place.setText(place);
        holder.textView_price.setText(cost);
        holder.recycler_list_Date.setText(date);
   }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return mCursor.getCount();
    }



}


