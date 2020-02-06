package com.example.doit.wapproject2_test1.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "consume")
public class ConsumeEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String state;
    public String category;
    public String place;
    public String cost;
    public String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
