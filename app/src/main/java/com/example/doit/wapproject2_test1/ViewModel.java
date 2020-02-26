package com.example.doit.wapproject2_test1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.doit.wapproject2_test1.entity.Consume;

import java.util.List;
import java.util.function.Consumer;

public class ViewModel extends AndroidViewModel {

    private Repository Repository;

    private LiveData<List<Consume>> mAllConsumes;
    private LiveData<List<Consume>> mOrderedConsumes;
    private final MutableLiveData<String> filterLiveData = new MutableLiveData<String>();

    public ViewModel (@NonNull Application application) {
        super(application);
        Repository = new Repository(application);
        mAllConsumes = Repository.getAllConsumes();
        mOrderedConsumes = Transformations.switchMap(filterLiveData, v -> Repository.getDataOrderByDate(v));
    }

    public LiveData<List<Consume>> getAllConsumes() { return mAllConsumes; }
    public LiveData<List<Consume>> getOrderByDate() { return mOrderedConsumes; }

    public void setFilter(String filter) { filterLiveData.postValue(filter);}

    public void insert(Consume consume) { Repository.insert(consume); }

    public void update(Consume consume) { Repository.update(consume);}

    public void delete(Consume consume) { Repository.delete(consume);}

    public void deleteAllConsumes() { Repository.deleteAllconsume();}
}
