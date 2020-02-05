package com.example.doit.wapproject2_test1.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "consume")
public class ConsumeEntity {
    @PrimaryKey
    public int id;

    public String state;
    public String category;
    public String place;
    public String cost;
    public String date;
}
