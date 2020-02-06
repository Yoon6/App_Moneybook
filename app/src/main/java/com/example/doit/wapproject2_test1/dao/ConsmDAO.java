package com.example.doit.wapproject2_test1.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.doit.wapproject2_test1.entity.ConsumeEntity;

import java.util.List;


@Dao
public interface ConsmDAO {
    @Query("SELECT * FROM consume")
    LiveData<List<ConsumeEntity>> getAll();


    @Query("SELECT * FROM consume WHERE id IN (:userIds)")
    LiveData<List<ConsumeEntity>> loadAllByIds(int[] userIds);

    // first_name의 목록중에 파라미터 first와 같거나 last_name의 목록중에 파라미터2 last와 같은거 출력
    //@Query("SELECT * FROM consume WHERE first_name LIKE :first AND " +
    //        "last_name LIKE :last LIMIT 1")
    //ConsumeEntity findByName(String first, String last);

    @Insert
    void insert(ConsumeEntity consumes);

    @Update
    void update(ConsumeEntity consumes);

    @Delete
    void delete(ConsumeEntity consumes);

    @Query("DELETE FROM consume")
    void deleteAll();
}
