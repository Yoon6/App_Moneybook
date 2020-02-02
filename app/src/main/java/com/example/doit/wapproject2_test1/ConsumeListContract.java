package com.example.doit.wapproject2_test1;


import android.provider.BaseColumns;

public class ConsumeListContract {

    public static final class ConsumeListEntry implements BaseColumns{
        // table name
        public static final String TABLE_NAME = "ComsumeList";
        // table components
        public static final String COLUMN_STATE = "state";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_PLACE = "place";
        public static final String COLUMN_COST = "cost";
        public static final String COLUMN_DATE = "date";
    }
}
