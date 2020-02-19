package com.example.doit.wapproject2_test1;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.doit.wapproject2_test1.entity.Consume;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository mRepository;

    private LiveData<List<Consume>> mAllConsumes;

    public ViewModel (Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllConsumes = mRepository.getAllConsumes();
    }

    public LiveData<List<Consume>> getAllWords() { return mAllConsumes; }

    public void insert(Consume consume) { mRepository.insert(consume); }
}
