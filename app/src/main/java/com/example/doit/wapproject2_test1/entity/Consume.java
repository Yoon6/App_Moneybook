package com.example.doit.wapproject2_test1.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "consume")
public class Consume {

    /*
    @PrimaryKey(autoGenerate = true)
    public int uid;

     */

    //@ColumnInfo(name = "state")
    @PrimaryKey(autoGenerate = true)
    private int id;
    //@ColumnInfo(name = "price")

    public String mPrice;
    public String mDate;
    public String mPlace;
    public String mCategory;

    public Consume(String place, String price, String date, String category){
        this.mPlace = place;
        this.mPrice = price;
        this.mDate = date;
        this.mCategory = category;
    }

    public void setId(int id){
        this.id =id;
    }

    public int getId() {
        return id;
    }

    public String getPrice(){
        return this.mPrice;
    }

    public String getPlace() { return this.mPlace;}

    public String getDate() {return  this.mDate; }

    public String getCategory() {return  this.mCategory; }
}
