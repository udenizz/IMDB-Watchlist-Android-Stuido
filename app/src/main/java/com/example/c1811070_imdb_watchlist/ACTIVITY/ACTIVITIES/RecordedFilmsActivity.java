package com.example.c1811070_imdb_watchlist.ACTIVITY.ACTIVITIES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.c1811070_imdb_watchlist.ADAPTER.DataBaseAdapter;
import com.example.c1811070_imdb_watchlist.DB.DataBase;
import com.example.c1811070_imdb_watchlist.ENTITY.MovieDetailResult;
import com.example.c1811070_imdb_watchlist.R;

import java.util.ArrayList;

public class RecordedFilmsActivity extends AppCompatActivity {
    private RecyclerView savedFilmList;
    public ArrayList<MovieDetailResult> movieDetailResultList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded_films);
        savedFilmList=findViewById(R.id.saved_film_list_recyclerview);
        movieDetailResultList=DataBase.getInstance(this).getMovieList();

        RecordedFilmsActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setAdapterToRecyclerView(movieDetailResultList);
            }
        });

    }

    private void setAdapterToRecyclerView(ArrayList<MovieDetailResult> movieDetails) {
        DataBaseAdapter adapter=new DataBaseAdapter(movieDetails);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        savedFilmList.setLayoutManager(mLayoutManager);
        savedFilmList.setAdapter(adapter);

    }
}