package com.example.c1811070_imdb_watchlist.ACTIVITY.ACTIVITIES;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.c1811070_imdb_watchlist.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    private EditText film_input;
    Button show_film_btn;

    private String filmName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);

        film_input=findViewById(R.id.film_input_text);
        show_film_btn=findViewById(R.id.show_data_button);


        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.search_bar);


        show_film_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getInput();
                navigateSearchDetail();
            }
        });

    }
    MainFragment mainFragment = new MainFragment();
    Top250MovieListFragment top250MovieList=new Top250MovieListFragment();
    WatchListFragment watchListFragment=new WatchListFragment();


    private void getInput()
    {
        filmName=film_input.getText().toString();
    }


    private void navigateSearchDetail()
    {
        Intent filmList=new Intent(MainActivity.this, FilmListActivity.class);
        filmList.putExtra("film_name",filmName);
        startActivity(filmList);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.search_bar:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,mainFragment).commit();
                return true;
            case R.id.top_250movies:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,top250MovieList).commit();
                Intent intent2=new Intent(MainActivity.this,Top250MoviesActivity.class);
                startActivity(intent2);
                return true;
            case R.id.watchlist:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,watchListFragment).commit();
                Intent intent3=new Intent(MainActivity.this,RecordedFilmsActivity.class);
                startActivity(intent3);
                return true;
        }
        return false;
    }
}