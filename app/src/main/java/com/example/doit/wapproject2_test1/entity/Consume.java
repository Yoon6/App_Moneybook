package com.example.doit.wapproject2_test1.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//@Entity(tableName = "consume")
@Entity
public class Consume {

    /*
    @PrimaryKey(autoGenerate = true)
    public int uid;

     */

    //@ColumnInfo(name = "state")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "price")
    public String mPrice;

    public Consume(String price){
        this.mPrice = price;
    }

    public String getPrice(){
        return this.mPrice;
    }

    /*
    public String state;
    public String category;
    public String cost;
    public String date;
    */
}
