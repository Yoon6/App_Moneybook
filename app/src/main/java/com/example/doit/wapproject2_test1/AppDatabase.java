package com.example.doit.wapproject2_test1;

import android.content.Context;
import android.os.AsyncTask;

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

    //싱글톤 패턴으로 재구성
    private static AppDatabase instance;

    public abstract ConsumeDao consumeDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "consume_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    public static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ConsumeDao consumeDao;

        private PopulateDbAsyncTask(AppDatabase db) {
            consumeDao = db.consumeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //consumeDao.insert(new Consume("-","메우",20000,"2017-01-12","Hou!!!"));
            return null;
        }
    }






    /*private static volatile AppDatabase INSTANCE;
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
                /*dao.insert(consume_cost);
                dao.insert(consume_cost2);
            });
        }
    }; */
}
