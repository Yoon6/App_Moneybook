package com.example.doit.wapproject2_test1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "consume.db";
    private static final int DATABASE_VERSION = 2;

    public static final String CONSUME_TABLE = "consume";

    public static final String ID_COLUMN = "id";
    public static final String STATE_COLUMN = "state";
    public static final String CONSUME_CATEGORY = "category";
    public static final String CONSUME_PLACE = "place";
    public static final String CONSUME_COST = "cost";
    public static final String CONSUME_DATE = "date";

    public static final String CREATE_CONSUME_TABLE = "CREATE TABLE "
            + CONSUME_TABLE + "(" + ID_COLUMN + " INTEGER PRIMARY KEY, "
            + STATE_COLUMN + " TEXT, " + CONSUME_CATEGORY + " TEXT, "
            + CONSUME_PLACE + " TEXT, " + CONSUME_COST + " TEXT, "
            + CONSUME_DATE + " DATE " + ")";

    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context){
        if (instance == null)
            instance = new DataBaseHelper(context);
        return instance;
    }

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONSUME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
