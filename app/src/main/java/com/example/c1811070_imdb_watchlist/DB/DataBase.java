package com.example.c1811070_imdb_watchlist.DB;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.c1811070_imdb_watchlist.ADAPTER.DataBaseAdapter;
import com.example.c1811070_imdb_watchlist.ENTITY.MovieDetailResult;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    private final static String dataBaseName="FilmList1";
    private final static int databaseVersion=1;
    private String Film_Table1 = "Saved_Film1";
    private static DataBase dbInstance = null;

    public DataBase(@Nullable Context context) {
        super(context, dataBaseName, null, databaseVersion);
    }

    public synchronized static DataBase getInstance(Context context){
        if(dbInstance==null){
            dbInstance=new DataBase(context.getApplicationContext());
        }
        return dbInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + Film_Table1 + " ("
                + " imdbID STRING PRIMARY KEY ,"
                + " filmName TEXT,"
                + " filmYear TEXT,"
                + " image TEXT,"
                + " awards TEXT,"
                + " writers TEXT,"
                + " director TEXT,"
                + " plot TEXT,"
                + " genre TEXT"
                + " )";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @SuppressLint("Range")
    public MovieDetailResult getMovie(String n_imdbID){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(Film_Table1,null,"imdbID=?", new String[]{n_imdbID},null,null,null);


        if(cursor.moveToFirst()){
            new MovieDetailResult(
                    cursor.getString(cursor.getColumnIndex("imdbID")),
                    cursor.getString(cursor.getColumnIndex("filmName")),
                    cursor.getString(cursor.getColumnIndex("filmYear")),
                    cursor.getString(cursor.getColumnIndex("image")),
                    cursor.getString(cursor.getColumnIndex("awards")),
                    cursor.getString(cursor.getColumnIndex("writers")),
                    cursor.getString(cursor.getColumnIndex("director")),
                    cursor.getString(cursor.getColumnIndex("genre")),
                    cursor.getString(cursor.getColumnIndex("plot"))
            );
        }
        cursor.close();
        return null;
    }

    @SuppressLint("Range")
    public  ArrayList<MovieDetailResult> getMovieList(){
        ArrayList<MovieDetailResult> movieDetailResultsArrayList=new ArrayList<MovieDetailResult>();
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(Film_Table1,null,null,null,null,null,null);


        if (cursor.moveToFirst()){
            do {
                movieDetailResultsArrayList.add(
                        new MovieDetailResult(
                            cursor.getString(cursor.getColumnIndex("imdbID")),
                            cursor.getString(cursor.getColumnIndex("filmName")),
                            cursor.getString(cursor.getColumnIndex("filmYear")),
                            cursor.getString(cursor.getColumnIndex("image")),
                            cursor.getString(cursor.getColumnIndex("awards")),
                            cursor.getString(cursor.getColumnIndex("writers")),
                            cursor.getString(cursor.getColumnIndex("director")),
                            cursor.getString(cursor.getColumnIndex("genre")),
                            cursor.getString(cursor.getColumnIndex("plot"))
                )
                );
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movieDetailResultsArrayList;
    }

    public void deleteFilm(String imdbID2){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Film_Table1,"imdbID=?",new String[]{imdbID2});
        db.close();
    }

    public void addNewFilm(String imdbID, String filmName, String filmYear, String image, String awards, String writers, String director, String genre,String summary)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();

        cv.put("imdbID",imdbID);
        cv.put("filmName",filmName);
        cv.put("filmYear",filmYear);
        cv.put("image",image);
        cv.put("awards",awards);
        cv.put("writers",writers);
        cv.put("director",director);
        cv.put("genre",genre);
        cv.put("plot",summary);

        db.insert(Film_Table1,null,cv);
        db.close();
    }

}
