package com.example.c1811070_imdb_watchlist.ACTIVITY.ACTIVITIES;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.c1811070_imdb_watchlist.DB.DataBase;
import com.example.c1811070_imdb_watchlist.ENTITY.MovieDetail;
import com.example.c1811070_imdb_watchlist.ENTITY.MovieDetailResult;
import com.example.c1811070_imdb_watchlist.R;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FilmDetailsActivity extends AppCompatActivity {
    private volatile MovieDetailResult movieDetailItem;
    String imdbID;
    private ImageView detailed_film_image;
    private TextView detailed_movie_film_name;
    private TextView detailed_movie_film_year;
    private TextView detailed_movie_time_to_watch;
    private TextView detailed_movie_type_of_film;
    private TextView detailed_movie_director;
    private TextView detailed_movie_writer;
    private TextView detailed_movie_actors;
    private TextView detailed_movie_awards;
    private TextView detailed_movie_imdbRatings;
    private TextView detailed_movie_Plot;
    private Button SaveWatchlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details);
        Bundle bundle=getIntent().getExtras();

        detailed_film_image=findViewById(R.id.detailed_film_image);
        detailed_movie_film_name=findViewById(R.id.detailed_movie_film_name);
        detailed_movie_film_year=findViewById(R.id.detailed_movie_film_year);
        detailed_movie_time_to_watch=findViewById(R.id.detailed_movie_time_to_watch);
        detailed_movie_type_of_film=findViewById(R.id.detailed_movie_type_of_film);
        detailed_movie_director=findViewById(R.id.detailed_movie_director);
        detailed_movie_writer=findViewById(R.id.detailed_movie_writer);
        detailed_movie_actors=findViewById(R.id.detailed_movie_actors);
        detailed_movie_awards=findViewById(R.id.detailed_movie_awards);
        detailed_movie_imdbRatings=findViewById(R.id.detailed_movie_imdbRatings);
        detailed_movie_Plot=findViewById(R.id.detailed_movie_Plot);
        SaveWatchlist=findViewById(R.id.SaveWatchlist);

        imdbID="";

        if(bundle!=null)
        {
            imdbID= bundle.getString("imdb_id");
        }

        getDetailDataFromNetwork(imdbID);
        while(movieDetailItem==null);

        SetText(movieDetailItem);
        MovieDetailResult temp=new MovieDetailResult(movieDetailItem.getImdbID(),movieDetailItem.getTitle(),movieDetailItem.getYear(),  movieDetailItem.getPoster(),  movieDetailItem.getAwards(),movieDetailItem.getWriter(),  movieDetailItem.getDirector(), movieDetailItem.getGenre(),movieDetailItem.getPlot());
        SaveWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToDatabase(temp);
            }
        });

        movieDetailItem=null;

    }

    private void SetText(MovieDetailResult movieDetailItem)
    {
        detailed_movie_film_name.setText("Film Name: "+movieDetailItem.getTitle());
        detailed_movie_film_year.setText("Year: "+movieDetailItem.getYear());
        detailed_movie_time_to_watch.setText("Film Runtime: "+movieDetailItem.getRuntime());
        detailed_movie_type_of_film.setText("Film Type: "+movieDetailItem.getGenre());
        detailed_movie_director.setText("Director: "+movieDetailItem.getDirector());
        detailed_movie_writer.setText("Writer of Film: "+movieDetailItem.getWriter());
        detailed_movie_actors.setText("Actors: "+movieDetailItem.getActors());
        detailed_movie_awards.setText("Awards:"+movieDetailItem.getAwards());
        detailed_movie_imdbRatings.setText("IMDB Rating Point: "+movieDetailItem.getImdbRating());
        detailed_movie_Plot.setText("Summary: "+movieDetailItem.getPlot());
        String imageURLL=movieDetailItem.getPoster();
        Glide.with(detailed_film_image)
                .load(imageURLL)
                .fitCenter()
                .override(5000,5000)
                .into(detailed_film_image);
        detailed_film_image.setAlpha(100);
    }

    private void saveDataToDatabase(MovieDetailResult temp) {
        DataBase db=DataBase.getInstance(this);
        db.addNewFilm(temp.getImdbID(),temp.getTitle(),temp.getYear(),  temp.getPoster(),  temp.getAwards(),temp.getWriter(), temp.getDirector(), temp.getGenre(),temp.getPlot());
        finish();
    }

    private void getDetailDataFromNetwork(String imdbID)
    {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.collectapi.com/imdb/imdbSearchById?movieId="+imdbID)
                .method("GET", null)
                .addHeader("authorization", "apikey 3kJbsvpmSBgy1btYqFZS6X:4MntNlBbpHRVoVLHFCrEHD")
                .addHeader("content-type", "application/json")
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
                    final String responseBody2 = response.body().string();
                    MovieDetail movieDetail = new Gson().fromJson(responseBody2,MovieDetail.class);
                    setConstructor(movieDetail.getMovieDetailResult());

                }

            }
        });
    }

    private void setConstructor(MovieDetailResult movieDetailResult) {
        this.movieDetailItem=movieDetailResult;
    }

}