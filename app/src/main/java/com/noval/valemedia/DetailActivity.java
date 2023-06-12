package com.noval.valemedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.noval.valemedia.databinding.ActivityDetailBinding;
import com.noval.valemedia.network.Movie;
import com.noval.valemedia.network.TV;
import com.noval.valemedia.storage.FavoriteMovie;
import com.noval.valemedia.storage.FavoriteTv;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private boolean isFavorite = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Movie movie = getIntent().getParcelableExtra("MOVIE");
        TV tv = getIntent().getParcelableExtra("TV");

        FavoriteTv favoriteTv = getIntent().getParcelableExtra("FAV_TV");
        FavoriteMovie favoriteMovie = getIntent().getParcelableExtra("FAV_MOVIE");

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });

        binding.btnFavorite.setOnClickListener(v -> {
            toggleFavorite();
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        });

        loadFavoriteMovieIfExist(favoriteMovie);
        loadFavoriteTvIfExist(favoriteTv);
        loadMovieIfExist(movie);
        loadTVIfExist(tv);
    }

    private void loadFavoriteTvIfExist(FavoriteTv favoriteTv) {
        if (favoriteTv == null) return;

    }

    private void loadFavoriteMovieIfExist(FavoriteMovie favoriteMovie) {
        if (favoriteMovie == null) return;

    }

    private void toggleFavorite() {

    }

    private void loadMovieIfExist(Movie movie) {
        if (movie == null) return;
        binding.tvTitle.setText(movie.getTitle());
        binding.tvOverview.setText(movie.getOverview());
        binding.tvDate.setText(movie.getReleaseDate());
        binding.tvRating.setText(getString(R.string.star)  + movie.getVoteAverage());
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath())
                .into(binding.ivPoster);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + movie.getBackdropPath())
                .into(binding.ivBackdrop);
    }

    private void loadTVIfExist(TV tv) {
        if (tv == null) return;
        binding.tvTitle.setText(tv.getName());
        binding.tvOverview.setText(tv.getOverview());
        binding.tvDate.setText(tv.getFirstAirDate());
        binding.tvRating.setText(getString(R.string.star)  + tv.getVoteAverage());
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + tv.getPosterPath())
                .into(binding.ivPoster);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + tv.getBackdropPath())
                .into(binding.ivBackdrop);
    }
}