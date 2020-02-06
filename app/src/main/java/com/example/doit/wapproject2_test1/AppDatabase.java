package com.example.doit.wapproject2_test1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.doit.wapproject2_test1.dao.ConsmDAO;
import com.example.doit.wapproject2_test1.entity.ConsumeEntity;

@Database(version = 1, entities = {ConsumeEntity.class})
abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ConsmDAO consmDAO();

    // DB객체 생성 가져오기
    public static AppDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "consume").build();
        }
        return INSTANCE;
    }

    // DB객체 삭제
    public static void destroyInstance() {

    }

}
