package com.example.doit.wapproject2_test1;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.doit.wapproject2_test1.dao.ConsumeDao;
import com.example.doit.wapproject2_test1.entity.Consume;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Consume.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ConsumeDao consumeDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS =4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database").addCallback(sAppDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sAppDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);

            databaseWriteExecutor.execute(() -> {
                ConsumeDao dao = INSTANCE.consumeDao();

                Consume consume_cost = new Consume("hou","1000","1000-10-10","hou");
                Consume consume_cost2 = new Consume("hou2","1","1000-10-10","hou");
                dao.insert(consume_cost);
                dao.insert(consume_cost2);
            });
        }
    };
}
