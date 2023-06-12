package com.noval.valemedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.noval.valemedia.databinding.ActivityDetailBinding;
import com.noval.valemedia.network.Movie;
import com.noval.valemedia.network.TV;
import com.noval.valemedia.storage.FavoriteMovie;
import com.noval.valemedia.storage.FavoriteTv;
import com.noval.valemedia.storage.HiddenHelper;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private HiddenHelper hiddenHelper;
    private int itemID;
    private String posterPath, backdropPath;
    private boolean isMovie = false;
    private boolean isFavorite = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        hiddenHelper = new HiddenHelper(this);

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
        else isFavorite = checkFavorite(hiddenHelper.checkFavoriteTvShow(favoriteTv.getId()));
        this.itemID = favoriteTv.getId();
        this.isMovie = false;
        this.posterPath = favoriteTv.getPosterPath();
        this.backdropPath = favoriteTv.getBackdropPath();

        binding.tvTitle.setText(favoriteTv.getTitle());
        binding.tvOverview.setText(favoriteTv.getOverview());
        binding.tvDate.setText(favoriteTv.getReleaseDate());
        loadImage(favoriteTv.getPosterPath(), binding.ivPoster);
        loadImage(favoriteTv.getBackdropPath(), binding.ivBackdrop);

    }

    private void loadFavoriteMovieIfExist(FavoriteMovie favoriteMovie) {
        if (favoriteMovie == null) return;
        else isFavorite = checkFavorite(hiddenHelper.checkFavoriteMovie(favoriteMovie.getId()));
        this.itemID = favoriteMovie.getId();
        this.isMovie = true;
        this.posterPath = favoriteMovie.getPosterPath();
        this.backdropPath = favoriteMovie.getBackdropPath();

        binding.tvTitle.setText(favoriteMovie.getTitle());
        binding.tvOverview.setText(favoriteMovie.getOverview());
        binding.tvDate.setText(favoriteMovie.getReleaseDate());
        loadImage(favoriteMovie.getPosterPath(), binding.ivPoster);
        loadImage(favoriteMovie.getBackdropPath(), binding.ivBackdrop);
    }

    private void toggleFavorite() {
        if (isFavorite) {
            if (isMovie) {
                hiddenHelper.deleteFavoriteMovie(itemID);
            } else {
                hiddenHelper.deleteFavoriteTvShow(itemID);
            }
            binding.btnFavorite.setImageResource(R.drawable.round_favorite_border_24);
        } else {
            hiddenHelper.insertFavoriteMovie(
                    itemID,
                    binding.tvTitle.getText().toString(),
                    binding.tvDate.getText().toString(),
                    posterPath, backdropPath,
                    binding.tvOverview.getText().toString(),
                    binding.tvRating.getText().toString()
            );
            binding.btnFavorite.setImageResource(R.drawable.round_favorite_24);
        }
    }

    private void loadMovieIfExist(Movie movie) {
        if (movie == null) return;
        else isFavorite = checkFavorite(hiddenHelper.checkFavoriteMovie(movie.getId()));
        this.itemID = movie.getId();
        this.isMovie = true;
        this.posterPath = movie.getPosterPath();
        this.backdropPath = movie.getBackdropPath();

        binding.tvTitle.setText(movie.getTitle());
        binding.tvOverview.setText(movie.getOverview());
        binding.tvDate.setText(movie.getReleaseDate());
        binding.tvRating.setText(getString(R.string.star)  + movie.getVoteAverage());
        loadImage(movie.getPosterPath(), binding.ivPoster);
        loadImage(movie.getBackdropPath(), binding.ivBackdrop);
    }

    private void loadTVIfExist(TV tv) {
        if (tv == null) return;
        else isFavorite = checkFavorite(hiddenHelper.checkFavoriteTvShow(tv.getId()));
        this.itemID = tv.getId();
        this.isMovie = false;
        this.posterPath = tv.getPosterPath();
        this.backdropPath = tv.getBackdropPath();

        binding.tvTitle.setText(tv.getName());
        binding.tvOverview.setText(tv.getOverview());
        binding.tvDate.setText(tv.getFirstAirDate());
        binding.tvRating.setText(getString(R.string.star)  + tv.getVoteAverage());

        loadImage(tv.getPosterPath(), binding.ivPoster);
        loadImage(tv.getBackdropPath(), binding.ivBackdrop);
    }



    private void loadImage(String path, ImageView image) {
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + path)
                .into(image);
    }

    private boolean checkFavorite(boolean isFavorite) {
        if (isFavorite) {
            binding.btnFavorite.setImageResource(R.drawable.round_favorite_24);
            return true;
        } else {
            binding.btnFavorite.setImageResource(R.drawable.round_favorite_border_24);
            return false;
        }
    }



}