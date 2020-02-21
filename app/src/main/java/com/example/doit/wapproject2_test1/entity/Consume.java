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
    public String mDate;
    public String mPlace;
    public String mCategory;

    public Consume(String place, String price, String date, String category){
        this.mPlace = place;
        this.mPrice = price;
        this.mDate = date;
        this.mCategory = category;
    }

    public String getPrice(){
        return this.mPrice;
    }

    public String getPlace() { return this.mPlace;}

    public String getDate() {return  this.mDate; }

    public String getCategory() {return  this.mCategory; }

    /*
    public String state;
    public String category;
    public String cost;
    public String date;
    */
}
