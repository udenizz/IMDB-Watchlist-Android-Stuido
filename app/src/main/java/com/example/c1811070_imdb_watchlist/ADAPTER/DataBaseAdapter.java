package com.example.c1811070_imdb_watchlist.ADAPTER;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.c1811070_imdb_watchlist.DB.DataBase;
import com.example.c1811070_imdb_watchlist.ENTITY.MovieDetailResult;
import com.example.c1811070_imdb_watchlist.ENTITY.Result;
import com.example.c1811070_imdb_watchlist.R;

import java.util.ArrayList;
import java.util.List;

public class DataBaseAdapter extends RecyclerView.Adapter<DataBaseAdapter.ViewHolder>{
    private ArrayList<MovieDetailResult> watchList;
    private MovieDetailResult watchItem;
    private MovieDetailResult tempItem;

    public DataBaseAdapter(ArrayList<MovieDetailResult> movieDetails) {
        watchList=movieDetails;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.database_item,parent,false);
        ViewHolder holder;
        holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        watchItem=watchList.get(position);

        holder.dbFilmName.setText("Film Name: "+watchItem.getTitle());
        holder.dbFilmYear.setText("Film Year: "+ watchItem.getYear());
        holder.dbFilmAward.setText("Awards: "+watchItem.getAwards());
        holder.dbFilmWriter.setText("Writer: "+watchItem.getWriter());
        holder.dbFilmDirector.setText("Director: "+watchItem.getDirector());
        holder.dbFilmGenre.setText("Type: "+ watchItem.getGenre());
        holder.dbFilmSummary.setText("Plot: "+watchItem.getPlot());
        holder.dbWatchedFilm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                deleteFromDb(position,v);
            }
        });
        String imageUrl=watchItem.getPoster();
        Glide.with(holder.dbFilmYear)
                .load(imageUrl)
                .fitCenter()
                .override(5000,5000)
                .into(holder.dbImage);

    }
    @SuppressLint("NotifyDataSetChanged")
    private void deleteFromDb(int pos, View v) {
        DataBase db=DataBase.getInstance(v.getContext());
        tempItem=watchList.get(pos);
        db.deleteFilm(tempItem.getImdbID());
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return watchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView dbImage;
        private TextView dbFilmName;
        private TextView dbFilmYear;
        private TextView dbFilmAward;
        private TextView dbFilmWriter;
        private TextView dbFilmDirector;
        private TextView dbFilmGenre;
        private TextView dbFilmSummary;
        private Button dbWatchedFilm;

        public ViewHolder(@NonNull View v) {
            super(v);

            dbImage=v.findViewById(R.id.saved_film_image);
            dbFilmName=v.findViewById(R.id.saved_film_name);
            dbFilmYear=v.findViewById(R.id.saved_film_year);
            dbFilmAward=v.findViewById(R.id.saved_film_award);
            dbFilmWriter=v.findViewById(R.id.saved_film_writer);
            dbFilmDirector=v.findViewById(R.id.saved_film_director);
            dbFilmGenre=v.findViewById(R.id.saved_film_genre);
            dbFilmSummary=v.findViewById(R.id.saved_film_summary);
            dbWatchedFilm=v.findViewById(R.id.watchedButton);
        }
    }
}
