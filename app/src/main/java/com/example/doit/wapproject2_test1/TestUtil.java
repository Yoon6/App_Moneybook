package com.example.doit.wapproject2_test1;

/*
 * Created by YUNHEE on 2018-01-06.
 */

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static void insertFakeData(SQLiteDatabase db){
        if(db == null){
            return;
        }
        //create a list of fake guests
        List<ContentValues> list = new ArrayList<>();

        ContentValues cv = new ContentValues();
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_STATE, "expense");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_CATEGORY, "식비/생활");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_PLACE, "칫솔");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_COST, "10000");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_DATE, "2020년 1월 22일");
        list.add(cv);

        cv = new ContentValues();
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_STATE, "expense");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_CATEGORY, "패션/미용");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_PLACE, "머리");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_COST, "15000");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_DATE, "2020년 1월 25일");
        list.add(cv);

        cv = new ContentValues();
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_STATE, "income");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_CATEGORY, "식비/생활");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_PLACE, "월급");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_COST, "10000");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_DATE, "2020년 1월 22일");
        list.add(cv);

        cv = new ContentValues();
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_STATE, "income");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_CATEGORY, "식비/생활");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_PLACE, "칫솔");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_COST, "10000");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_DATE, "2020년 1월 22일");
        list.add(cv);

        cv = new ContentValues();
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_STATE, "income");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_CATEGORY, "식비/생활");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_PLACE, "칫솔");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_COST, "10000");
        cv.put(ConsumeListContract.ConsumeListEntry.COLUMN_DATE, "2020년 1월 22일");
        list.add(cv);

        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
            db.delete (ConsumeListContract.ConsumeListEntry.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(ConsumeListContract.ConsumeListEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

    }
}
