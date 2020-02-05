package com.example.doit.wapproject2_test1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.doit.wapproject2_test1.dao.ConsmDAO;
import com.example.doit.wapproject2_test1.entity.ConsumeEntity;

@Database(version = 1, entities = {ConsumeEntity.class})
abstract class AppDatabase extends RoomDatabase {

    public abstract ConsmDAO consmDAO();
}
