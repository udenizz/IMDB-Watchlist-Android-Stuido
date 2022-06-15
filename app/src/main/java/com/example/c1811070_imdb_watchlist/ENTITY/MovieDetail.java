

package com.example.c1811070_imdb_watchlist.ENTITY;


import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MovieDetail implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("result")
    @Expose
    private MovieDetailResult result1;
    public final static Creator<MovieDetail> CREATOR = new Creator<MovieDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieDetail createFromParcel(android.os.Parcel in) {
            return new MovieDetail(in);
        }

        public MovieDetail[] newArray(int size) {
            return (new MovieDetail[size]);
        }

    }
            ;

    protected MovieDetail(android.os.Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.result1 = ((MovieDetailResult) in.readValue((MovieDetailResult.class.getClassLoader())));
    }

    public MovieDetail() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public MovieDetailResult getMovieDetailResult() {
        return result1;
    }

    public void setResult(MovieDetailResult result) {
        this.result1 = result;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(result1);
    }

    public int describeContents() {
        return 0;
    }

}