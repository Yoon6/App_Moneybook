package com.example.doit.wapproject2_test1.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.doit.wapproject2_test1.entity.Consume;

import java.util.List;

@Dao
public interface ConsumeDao {

    @Query("SELECT * FROM consume_table")
    LiveData<List<Consume>> getAllConsumes();

    @Insert
    void insert(Consume consumes);

    @Update
    void update(Consume consume);

    @Delete
    void delete(Consume consume);

    @Query("DELETE FROM consume_table")
    void deleteAllConsumes();


}
