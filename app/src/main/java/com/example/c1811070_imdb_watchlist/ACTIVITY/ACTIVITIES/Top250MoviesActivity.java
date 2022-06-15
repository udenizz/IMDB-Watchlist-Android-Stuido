package com.example.c1811070_imdb_watchlist.ACTIVITY.ACTIVITIES;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.c1811070_imdb_watchlist.ADAPTER.Top250FilmAdapter;
import com.example.c1811070_imdb_watchlist.ENTITY.Item;
import com.example.c1811070_imdb_watchlist.ENTITY.Top250Film;
import com.example.c1811070_imdb_watchlist.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Top250MoviesActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    
    private RecyclerView top250_list_recyclerview;
    BottomNavigationView bottomNavigationView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top250_movies);
        Bundle bundle=getIntent().getExtras();
        bottomNavigationView1=findViewById(R.id.bottomNavigationView1);
        bottomNavigationView1.setOnNavigationItemSelectedListener(this);
        bottomNavigationView1.setSelectedItemId(R.id.top_250movies);


        
        top250_list_recyclerview=findViewById(R.id.top250_filmList_recyclerview);
        
        getTop250FilmFromNetwork();
        
    }

    private void getTop250FilmFromNetwork()
    {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://imdb-api.com/API/Top250Movies/k_ydd4eogv")
                .method("GET", null)
                .addHeader("accept", "text/plain")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e)
            {
                Log.d(TAG,"on Failure");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException
            {
                if(response.isSuccessful())
                {
                    final String responseBody1=response.body().string();
                    Top250Film top250Film=new Gson().fromJson(responseBody1,Top250Film.class);
                    Top250MoviesActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run()
                        {
                            setAdapterToRecyclerViewer250Film(top250Film.getItems());
                        }
                    });


                    Log.d(TAG,"onResponse");

                }

            }
        });
    }
    MainFragment mainFragment11 = new MainFragment();
    Top250MovieListFragment top250MovieList22=new Top250MovieListFragment();
    WatchListFragment watchListFragment33=new WatchListFragment();

    private void setAdapterToRecyclerViewer250Film(List<Item> items)
    {
        Top250FilmAdapter adapter = new Top250FilmAdapter(items);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        top250_list_recyclerview.setLayoutManager(mLayoutManager);
        top250_list_recyclerview.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.search_bar:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment1,mainFragment11).commit();
                Intent intent11 = new Intent (Top250MoviesActivity.this,MainActivity.class);
                startActivity(intent11);
                return true;
            case R.id.top_250movies:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment1,top250MovieList22).commit();
                return true;
            case R.id.watchlist:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment1,watchListFragment33).commit();
                Intent intent3=new Intent(Top250MoviesActivity.this,RecordedFilmsActivity.class);
                startActivity(intent3);
                return true;
        }
        return false;
    }

}