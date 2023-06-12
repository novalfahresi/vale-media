package com.noval.valemedia.storage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface HiddenFavorite {
    @Query("SELECT * FROM favorite_movie_table")
    LiveData<List<FavoriteMovie>> getAllMovie();

    @Query("SELECT * FROM favorite_movie_table WHERE id=:id LIMIT 1")
    FavoriteMovie findMovieById(int id);

    @Query("SELECT EXISTS (SELECT * FROM favorite_movie_table WHERE id=:id)")
    boolean isMovieExist(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable addFavoriteMovie(FavoriteMovie favoriteMovie);

    @Delete
    Completable deleteFavoriteMovie(FavoriteMovie favoriteMovie);



    @Query("SELECT * FROM favorite_tv_show_table")
    LiveData<List<FavoriteTv>> getAllTvShows();

    @Query("SELECT * FROM favorite_tv_show_table WHERE id=:id LIMIT 1")
    FavoriteTv findByTvShowId(int id);

    @Query("SELECT EXISTS (SELECT * FROM favorite_tv_show_table WHERE id=:id)")
    boolean isTvShowExist(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable addFavoriteTvShow(FavoriteTv favoriteTvShow);

    @Delete()
    Completable deleteFavoriteTvShow(FavoriteTv favoriteTvShow);

}

