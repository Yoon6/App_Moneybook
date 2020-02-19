package com.example.doit.wapproject2_test1.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.doit.wapproject2_test1.entity.Consume;

import java.util.List;

@Dao
public interface ConsumeDao {

    @Query("SELECT * FROM Consume")
    LiveData<List<Consume>> getAll();

    @Insert
    void insert(Consume consumes);

    @Delete
    void delete(Consume consume);

    @Query("DELETE FROM consume")
    void deleteAll();

}
