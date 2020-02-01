package com.example.doit.wapproject2_test1;

/**
 * Created by DowonYoon on 2017-06-21.
 */

import android.provider.BaseColumns;

public final class DataBases {

    public static final class CreateDB implements BaseColumns {
        public static final String STATE = "state";
        public static final String CATEGORY = "category";
        public static final String PLACE = "place";
        public static final String COST = "cost";
        public static final String DATE = "date";
        public static final String _TABLENAME0 = "usertable";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +STATE+" text not null , "
                +CATEGORY+" text not null , "
                +PLACE+" text not null , "
                +COST+" integer not null , "
                +DATE+" text not null );";
    }
}