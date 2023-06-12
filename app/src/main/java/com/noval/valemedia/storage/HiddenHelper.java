package com.noval.valemedia.storage;

import android.annotation.SuppressLint;
import android.content.Context;

public class HiddenHelper {

    private final Hidden roomDatabase;
    private boolean status;

    public HiddenHelper(Context context) {
        roomDatabase = Hidden.getInstance(context);
        status = false;
    }

    public boolean checkFavoriteMovie(int id) {
        return roomDatabase.favoriteDao().isMovieExist(id);
    }
    public boolean checkFavoriteTvShow(int id) {
        return roomDatabase.favoriteDao().isTvShowExist(id);
    }

    @SuppressLint("CheckResult")
    public boolean insertFavoriteMovie(int id, String title, String posterPath, String backdropPath, String releaseDate, String overview, String voteAverage) {
        FavoriteMovie favoriteMovie = new FavoriteMovie(id, title, posterPath, backdropPath, releaseDate, overview, voteAverage);

        roomDatabase.favoriteDao().addFavoriteMovie(favoriteMovie).subscribe(() -> {
            status = true;
        }, throwable -> {
            status = false;
        });

        return status;
    }

    @SuppressLint("CheckResult")
    public boolean deleteFavoriteMovie(int id) {
        FavoriteMovie favoriteMovie = roomDatabase.favoriteDao().findMovieById(id);

        roomDatabase.favoriteDao().deleteFavoriteMovie(favoriteMovie).subscribe(() -> {
            status = true;
        }, throwable -> {
            status = false;
        });

        return status;
    }

    @SuppressLint("CheckResult")
    public boolean insertFavoriteTvShow(int id, String title, String posterPath, String backdropPath, String releaseDate, String overview, String voteAverage) {
        FavoriteTv favoriteTvShow = new FavoriteTv(id, title, posterPath, backdropPath, releaseDate, overview, voteAverage);

        roomDatabase.favoriteDao().addFavoriteTvShow(favoriteTvShow).subscribe(() -> {
            status = true;
        }, throwable -> {
            status = false;
        });

        return status;
    }

    @SuppressLint("CheckResult")
    public boolean deleteFavorite(int id) {
        FavoriteMovie favoriteMovie = roomDatabase.favoriteDao().findMovieById(id);

        roomDatabase.favoriteDao().deleteFavoriteMovie(favoriteMovie).subscribe(() -> {
            status = true;
        }, throwable -> {
            status = false;
        });

        return status;
    }

    @SuppressLint("CheckResult")
    public boolean deleteFavoriteTvShow(int id) {
        FavoriteTv favoriteTvShow = roomDatabase.favoriteDao().findByTvShowId(id);

        roomDatabase.favoriteDao().deleteFavoriteTvShow(favoriteTvShow).subscribe(() -> {
            status = true;
        }, throwable -> {
            status = false;
        });

        return status;
    }
}
