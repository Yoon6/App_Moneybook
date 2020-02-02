package com.example.doit.wapproject2_test1;

import android.content.Context;
import android.database.sqlite.*;

import com.example.doit.wapproject2_test1.ConsumeListContract.*;


/*
 * 실제 DB 생성을 위해선 DB Helper 라는 클래스가 필요하다.
 * DB Helper 는 DB의 레퍼런스를 통해 생성되며 쿼리를 통해 DB를 접근하게 해준다.
 * DB Helper 를 생성하기 위해서 SQLiterOpenHelper 를 상속 받는다.
 * SQLiterOpenHelper 는 DB를 처음 생성할 때와 DB의 스키마를 변경할 때 도움을 준다.
 * SQLiterOpenHelper 는 onCreate 와 onUpgrade 메서드를 제공한다.
 * */
public class ConsumeListDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "consumelist.db";
    private static final int DATABASE_VERSION = 1;

    public ConsumeListDbHelper(Context context) {
        super(context,  DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 실제 DB가 생성된다.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE " + ConsumeListEntry.TABLE_NAME + " (" +
                ConsumeListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ConsumeListEntry.COLUMN_STATE + " TEXT NOT NULL, " +
                ConsumeListEntry.COLUMN_CATEGORY + " TEXT NOT NULL, " +
                ConsumeListEntry.COLUMN_PLACE + " TEXT NOT NULL, " +
                ConsumeListEntry.COLUMN_COST + " INTEGER NOT NULL, " +
                ConsumeListEntry.COLUMN_DATE + " TEXT NOT NULL " +
                "); ";

        // 쿼리 실행
        sqLiteDatabase.execSQL(SQL_CREATE_WAITLIST_TABLE);
    }

    // DB 스키마가 최근 것을 반영하게 해준다.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // 버전이 바뀌면 예전 버전의 테이블을 삭제 (나중에 ALTER 문으로 대체)
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConsumeListEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
