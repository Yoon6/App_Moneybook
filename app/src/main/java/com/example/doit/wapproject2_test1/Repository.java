package com.example.doit.wapproject2_test1;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Database;

import com.example.doit.wapproject2_test1.dao.ConsumeDao;
import com.example.doit.wapproject2_test1.entity.Consume;

import java.util.List;

public class Repository  {

    private ConsumeDao mConsumeDao;
    private LiveData<List<Consume>> mAllConsumes;

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
}
