package com.example.doit.wapproject2_test1.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.doit.wapproject2_test1.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ConsmListAdapter extends ArrayAdapter<Consume> {

    private Context context;
    List<Consume> consumses;

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);

    public ConsmListAdapter(Context context, List<Consume> consumses) {
        super(context, R.layout.fragment_writelist_recyclerview, consumses);
        this.context = context;
        this.consumses = consumses;
    }

    private class ViewHolder {
        TextView textView_category;
        TextView textView_place;
        TextView textView_price;
        TextView recycler_list_Date;
    }

    @Override
    public int getCount() {
        return consumses.size();
    }

    @Override
    public Consume getItem(int position) {
        return consumses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_writelist_recyclerview, null);
            holder = new ViewHolder();

            holder.textView_category = (TextView) convertView.findViewById(R.id.textView_recycler_category);
            holder.textView_place = (TextView) convertView.findViewById(R.id.textView_recycler_place);
            holder.textView_price = (TextView) convertView.findViewById(R.id.textView_recycler_price);
            holder.recycler_list_Date = (TextView) convertView.findViewById(R.id.recycler_list_Date);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Consume consume = (Consume) getItem(position);
        holder.textView_category.setText(consume.getCategory());
        holder.textView_place.setText(consume.getPlace());
        holder.textView_price.setText(consume.getCost());

        holder.recycler_list_Date.setText(formatter.format(consume.getDate()));

        return convertView;
    }

    @Override
    public void add(Consume consume) {
        consumses.add(consume);
        notifyDataSetChanged();
        super.add(consume);
    }

    @Override
    public void remove(Consume consume) {
        consumses.remove(consume);
        notifyDataSetChanged();
        super.remove(consume);
    }
}