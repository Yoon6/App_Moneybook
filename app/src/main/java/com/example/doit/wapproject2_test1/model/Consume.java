package com.example.doit.wapproject2_test1.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Consume implements Parcelable {

    private int id;
    private String state;
    private String category;
    private String place;
    private String cost;
    private Date date;

    public Consume() {
        super();
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Consume(Parcel in) {
        super();
        this.id = in.readInt();
        this.state = in.readString();
        this.category = in.readString();
        this.place = in.readString();
        this.cost = in.readString();
        this.date = new Date(in.readLong());
    }


    @Override
    public String toString() {
        return "Employee [id=" + id + ", state=" + state + ", category=" + category
                + ", place=" + place + ", cost=" + cost + ", date=" + date + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Consume other = (Consume) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeString(getState());
        parcel.writeString(getCategory());
        parcel.writeString(getPlace());
        parcel.writeString(getCost());
        parcel.writeLong(getDate().getTime());

    }

    public static final Creator<Consume> CREATOR = new Creator<Consume>() {
        public Consume createFromParcel(Parcel in) {
            return new Consume(in);
        }

        public Consume[] newArray(int size) {
            return new Consume[size];
        }
    };

}
