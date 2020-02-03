package com.example.doit.wapproject2_test1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.ParseException;

import com.example.doit.wapproject2_test1.model.Consume;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ConsumeDAO extends ConsumeDBDAO{

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.KOREAN);

    public ConsumeDAO(Context context) {
        super(context);
    }

    //...

    public long save(Consume consume) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.STATE_COLUMN, consume.getState());
        values.put(DataBaseHelper.CONSUME_CATEGORY, consume.getCategory());
        values.put(DataBaseHelper.CONSUME_PLACE, consume.getPlace());
        values.put(DataBaseHelper.CONSUME_COST, consume.getCost());
        values.put(DataBaseHelper.CONSUME_DATE, formatter.format(consume.getDate()));

        return database.insert(DataBaseHelper.CONSUME_TABLE, null, values);
    }

    //Get all records from the database
    public ArrayList<Consume> getConsmloyees() {
        ArrayList<Consume> consumes = new ArrayList<Consume>();

        Cursor cursor = database.query(DataBaseHelper.CONSUME_TABLE,
                new String[] { DataBaseHelper.ID_COLUMN,
                        DataBaseHelper.STATE_COLUMN,
                        DataBaseHelper.CONSUME_CATEGORY,
                        DataBaseHelper.CONSUME_PLACE,
                        DataBaseHelper.CONSUME_COST,
                        DataBaseHelper.CONSUME_DATE }, null, null, null,
                null, null);

        while (cursor.moveToNext()) {
            Consume consume = new Consume();
            consume.setId(cursor.getInt(0));
            consume.setState(cursor.getString(1));
            consume.setCategory(cursor.getString(2));
            consume.setPlace(cursor.getString(3));
            consume.setCost(cursor.getString(4));
            try {
                consume.setDate(formatter.parse(cursor.getString(5)));
            } catch (ParseException e) {
                consume.setDate(null);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

            consumes.add(consume);
        }
        return consumes;
    }
}
