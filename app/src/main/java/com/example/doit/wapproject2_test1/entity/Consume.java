package com.example.doit.wapproject2_test1.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "consume_table")
public class Consume {

    /*
    @PrimaryKey(autoGenerate = true)
    public int uid;

     */

    //@ColumnInfo(name = "state")
    @PrimaryKey(autoGenerate = true)
    private int id;
    //@ColumnInfo(name = "price")

    public String State;
    public int Cost;
    public String Date;
    public String Place;
    public String Category;

    public Consume(String State, String Place, int Cost, String Date, String Category){
        this.State = State;
        this.Place = Place;
        this.Cost = Cost;
        this.Date = Date;
        this.Category = Category;
    }

    public void setId(int id){
        this.id =id;
    }

    public int getId() {
        return id;
    }

    public int getCost(){
        return this.Cost;
    }

    public String getState() { return this.State;}

    public String getPlace() { return this.Place;}

    public String getDate() {return  this.Date; }

    public String getCategory() {return  this.Category; }


}
