package com.example.c1811070_imdb_watchlist.ACTIVITY.ACTIVITIES;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c1811070_imdb_watchlist.ADAPTER.FilmResultAdapter;
import com.example.c1811070_imdb_watchlist.ENTITY.Result;
import com.example.c1811070_imdb_watchlist.ENTITY.SearchResult;
import com.example.c1811070_imdb_watchlist.R;
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

public class FilmListActivity extends AppCompatActivity {

    private RecyclerView filmList_recyclerview;
    String filmName;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_list);

        filmList_recyclerview=findViewById(R.id.film_list_recyclerview);

        Bundle bundle=getIntent().getExtras();
        filmName="";
        if(bundle!=null)
        {
            filmName = bundle.getString("film_name");
        }
        getFilmListFromNetwork(filmName);
    }

    private void getFilmListFromNetwork(String filmName)
    {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.collectapi.com/imdb/imdbSearchByName?query="+filmName)
                .method("GET", null)
                .addHeader("authorization", "apikey 3kJbsvpmSBgy1btYqFZS6X:4MntNlBbpHRVoVLHFCrEHD")
                .addHeader("content-type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG,"on Failure");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful())
                {
                    final String responseBody=response.body().string();
                    SearchResult searchResult= new Gson().fromJson(responseBody,SearchResult.class);
                    FilmListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run()
                        {
                            setAdapterToRecyclerView(searchResult.getResult());
                        }
                    });


                    Log.d(TAG,"onResponse");
                }
            }
        });
    }

    private void setAdapterToRecyclerView(List<Result> results)
    {
        FilmResultAdapter adapter= new FilmResultAdapter(results);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        filmList_recyclerview.setLayoutManager(mLayoutManager);
        filmList_recyclerview.setAdapter(adapter);
    }

}
