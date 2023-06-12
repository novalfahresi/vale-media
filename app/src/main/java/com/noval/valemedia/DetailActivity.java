package com.noval.valemedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.noval.valemedia.databinding.ActivityDetailBinding;
import com.noval.valemedia.network.Movie;
import com.noval.valemedia.network.TV;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Movie movie = getIntent().getParcelableExtra("MOVIE");
        TV tv = getIntent().getParcelableExtra("TV");

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });

        binding.btnFavorite.setOnClickListener(v -> {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        });

        loadMovieIfExist(movie);
        loadTVIfExist(tv);
    }

    private void loadMovieIfExist(Movie movie) {
        if (movie == null) return;
        binding.tvTitle.setText(movie.getTitle());
        binding.tvOverview.setText(movie.getOverview());
        binding.tvDate.setText(movie.getReleaseDate());
        binding.tvRating.setText("★"  + movie.getVoteAverage());
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
        binding.tvRating.setText("★"  + tv.getVoteAverage());
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + tv.getPosterPath())
                .into(binding.ivPoster);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + tv.getBackdropPath())
                .into(binding.ivBackdrop);
    }
}