package com.noval.valemedia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.noval.valemedia.DetailActivity;
import com.noval.valemedia.databinding.ItemContentBinding;
import com.noval.valemedia.network.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movie> movies;
    private Context context;
    
    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContentBinding binding = ItemContentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemContentBinding binding;
        public ViewHolder(@NonNull ItemContentBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;


        }

        public void onBind(Movie movie) {
            binding.tvTitle.setText(movie.getTitle());
            binding.tvDate.setText(movie.getReleaseDate());
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath())
                    .into(binding.ivPoster);

            binding.getRoot().setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("MOVIE", movie);
                context.startActivity(intent);
            });
        }
    }
}
