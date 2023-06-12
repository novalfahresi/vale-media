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
import com.noval.valemedia.storage.FavoriteMovie;
import com.noval.valemedia.storage.FavoriteTv;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<FavoriteMovie> movies;
    private List<FavoriteTv> tvs;
    private Context context;

    public FavoriteAdapter(List<FavoriteMovie> movies, List<FavoriteTv> tvs) {
        this.movies = movies;
        this.tvs = tvs;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContentBinding binding = ItemContentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        if (position < movies.size())
            holder.onBindMovie(movies.get(position));
        else {
            int tvPosition = position - movies.size();
            holder.onBindTV(tvs.get(tvPosition));
        }
    }

    @Override
    public int getItemCount() {
        return movies.size() + tvs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemContentBinding binding;
        public ViewHolder(@NonNull ItemContentBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void onBindTV(FavoriteTv tv) {
            binding.tvTitle.setText(tv.getTitle());
            binding.tvDate.setText(tv.getReleaseDate());
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500" + tv.getPosterPath())
                    .into(binding.ivPoster);

            binding.getRoot().setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("FAV_TV", tv);
                context.startActivity(intent);
            });
        }

        public void onBindMovie(FavoriteMovie movie) {
            binding.tvTitle.setText(movie.getTitle());
            binding.tvDate.setText(movie.getReleaseDate());
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath())
                    .into(binding.ivPoster);

            binding.getRoot().setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("FAV_MOVIE", movie);
                context.startActivity(intent);
            });
        }

    }
}
