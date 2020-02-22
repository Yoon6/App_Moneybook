package com.example.doit.wapproject2_test1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.doit.wapproject2_test1.entity.Consume;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository Repository;

    private LiveData<List<Consume>> mAllConsumes;

    public ViewModel (@NonNull Application application) {
        super(application);
        Repository = new Repository(application);
        mAllConsumes = Repository.getAllConsumes();
    }

    public LiveData<List<Consume>> getAllConsumes() { return mAllConsumes; }

    public void insert(Consume consume) { Repository.insert(consume); }

    public void update(Consume consume) { Repository.update(consume);}

    public void delete(Consume consume) { Repository.delete(consume);}

    public void deleteAllConsumes() { Repository.deleteAllconsume();}
}
