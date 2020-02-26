package com.example.doit.wapproject2_test1;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Database;

import com.example.doit.wapproject2_test1.dao.ConsumeDao;
import com.example.doit.wapproject2_test1.entity.Consume;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class Repository  {

    private ConsumeDao consumeDao;
    private LiveData<List<Consume>> allConsumes;
    private LiveData<List<Consume>> consumesOrderByDate;

    private final Executor ioExecutor = Executors.newSingleThreadExecutor();


    Repository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        consumeDao = database.consumeDao();
        allConsumes = consumeDao.getAllConsumes();
    }


    public void insert(Consume consume){
        new InsertConsumeAsyncTask(consumeDao).execute(consume);
    }

    public void update(Consume consume) {
        new UpdateConsumeAsyncTask(consumeDao).execute(consume);
    }

    public void delete(Consume consume) {
        new DeleteConsumeAsyncTask(consumeDao).execute(consume);
    }

    public  void deleteAllconsume() {
        new DeleteAllConsumeAsyncTask(consumeDao).execute();

    }

    public LiveData<List<Consume>> getAllConsumes() {
        return allConsumes;
    }

    public LiveData<List<Consume>> getDataOrderByDate(String date) {
        consumesOrderByDate  = consumeDao.findDataOrderByDate(date);
        return consumesOrderByDate;
    }

    private static class InsertConsumeAsyncTask extends AsyncTask<Consume, Void, Void> {

        private ConsumeDao consumeDao;

        private InsertConsumeAsyncTask(ConsumeDao consumeDao) {
            this.consumeDao = consumeDao;
        }

        @Override
        protected Void doInBackground(Consume... consumes) {
            consumeDao.insert(consumes[0]);
            return null;
        }
    }

    private static class UpdateConsumeAsyncTask extends AsyncTask<Consume, Void, Void> {

        private ConsumeDao consumeDao;

        private UpdateConsumeAsyncTask(ConsumeDao consumeDao) {
            this.consumeDao = consumeDao;
        }

        @Override
        protected Void doInBackground(Consume... consumes) {
            consumeDao.update(consumes[0]);
            return null;
        }
    }

    private static class DeleteConsumeAsyncTask extends AsyncTask<Consume, Void, Void> {

        private ConsumeDao consumeDao;

        private DeleteConsumeAsyncTask(ConsumeDao consumeDao) {
            this.consumeDao = consumeDao;
        }

        @Override
        protected Void doInBackground(Consume... consumes) {
            consumeDao.delete(consumes[0]);
            return null;
        }
    }

    private static class DeleteAllConsumeAsyncTask extends AsyncTask<Void, Void, Void> {

        private ConsumeDao consumeDao;

        private DeleteAllConsumeAsyncTask(ConsumeDao consumeDao) {
            this.consumeDao = consumeDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            consumeDao.deleteAllConsumes();
            return null;
        }
    }




    /*
    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    Repository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mConsumeDao = db.consumeDao();
        mAllConsumes = mConsumeDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Consume>> getAllConsumes() {
        return mAllConsumes;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Consume consume) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mConsumeDao.insert(consume);
        });
    }

     */
}
