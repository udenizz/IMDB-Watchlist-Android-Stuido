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
import com.example.c1811070_imdb_watchlist.ENTITY.Item;
import com.example.c1811070_imdb_watchlist.R;

import java.util.List;

public class Top250FilmAdapter  extends RecyclerView.Adapter<Top250FilmAdapter.ViewHolder>{

    private List<Item> top250FilmList;
    private Item item;

    public Top250FilmAdapter(List<Item> items) {
        this.top250FilmList=items;
    }


    @Override
    public Top250FilmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top250_filmitems,parent,false);
        ViewHolder holder1;
        holder1=new ViewHolder(view);
        return holder1;
    }

    @Override
    public void onBindViewHolder(@NonNull Top250FilmAdapter.ViewHolder holder1, @SuppressLint("RecyclerView") int position) {
            item=top250FilmList.get(position);
            holder1.top250filmName.setText("Film Name: "+item.getTitle());
            holder1.top250filmYear.setText("Year: "+item.getYear());
            holder1.top250Crew.setText("Director and Casts: "+item.getCrew());
            holder1.top250ImdbPoint.setText("Imdb Rating: "+item.getImDbRating());
            String imageURL=item.getImage();
            Glide.with(holder1.top250filmImage)
                .load(imageURL)
                .override(1200,1000)
                .into(holder1.top250filmImage);

            String filmImdbId=item.getId();
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navigateFilmDetailAct(v.getContext(),position,filmImdbId);
                }
            });
    }



    @Override
    public int getItemCount() {
        return top250FilmList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView top250filmImage;
        private TextView top250filmName;
        private TextView top250filmYear;
        private TextView top250Crew;
        private TextView top250ImdbPoint;

        public ViewHolder(@NonNull View v) {
            super(v);

            top250filmImage=v.findViewById(R.id.top250_film_image);
            top250filmName=v.findViewById(R.id.top250_film_name);
            top250filmYear =v.findViewById(R.id.top250_film_year);
            top250Crew=v.findViewById(R.id.top250_film_crews);
            top250ImdbPoint=v.findViewById(R.id.top250_imdbPoint);
        }
    }

    private void navigateFilmDetailAct(Context context, int position, String filmImdbId) {
        Intent intent = new Intent(context, FilmDetailsActivity.class);
        item=top250FilmList.get(position);
        intent.putExtra("imdb_id",filmImdbId);
        context.startActivity(intent);
    }
}
