package com.example.c1811070_imdb_watchlist.ADAPTER;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.c1811070_imdb_watchlist.ACTIVITY.ACTIVITIES.FilmDetailsActivity;
import com.example.c1811070_imdb_watchlist.ENTITY.Result;
import com.example.c1811070_imdb_watchlist.R;

import java.util.List;

public class FilmResultAdapter extends RecyclerView.Adapter<FilmResultAdapter.ViewHolder> {

    private List<Result> filmResultList;
    private Result result;

    public FilmResultAdapter(List<Result> results) {
        this.filmResultList=results;
    }


    @Override
    public FilmResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film_items,parent,false);
        ViewHolder holder;
        holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FilmResultAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        result=filmResultList.get(position);

        holder.filmName.setText("Film Name: "+result.getTitle());
        holder.filmYear.setText("Year: "+result.getYear());
        String imageURL=result.getPoster();
        Glide.with(holder.filmImage)
                .load(imageURL)
                .fitCenter()
                .override(5000,5000)
                .into(holder.filmImage);

        String filmID=result.getImdbID();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                navigateFilmDetails(v.getContext(),position,filmID);
            }
        });
    }



    @Override
    public int getItemCount() {
        return filmResultList.size();
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView filmImage;
        private TextView filmName;
        private TextView filmYear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            filmImage=itemView.findViewById(R.id.film_image);
            filmName=itemView.findViewById(R.id.film_name);
            filmYear=itemView.findViewById(R.id.film_year);
        }
    }
    private void navigateFilmDetails(Context context, int position, String filmID)
    {
        Intent intent = new Intent(context, FilmDetailsActivity.class);
        result=filmResultList.get(position);
        intent.putExtra("result",result);
        intent.putExtra("imdb_id",filmID);
        context.startActivity(intent);
    }
}
