package com.example.c1811070_imdb_watchlist.ENTITY;

import java.util.List;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Top250Film implements Parcelable
{

    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;
    public final static Creator<Top250Film> CREATOR = new Creator<Top250Film>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Top250Film createFromParcel(android.os.Parcel in) {
            return new Top250Film(in);
        }

        public Top250Film[] newArray(int size) {
            return (new Top250Film[size]);
        }

    }
            ;

    protected Top250Film(android.os.Parcel in) {
        in.readList(this.items, (com.example.c1811070_imdb_watchlist.ENTITY.Item.class.getClassLoader()));
        this.errorMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Top250Film() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeList(items);
        dest.writeValue(errorMessage);
    }

    public int describeContents() {
        return 0;
    }

}
