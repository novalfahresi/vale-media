package com.noval.valemedia.storage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavoriteMovie.class, FavoriteTv.class}, version = 1, exportSchema = false)
public abstract class Hidden extends RoomDatabase {
    private static Hidden instance;

    public abstract HiddenFavorite favoriteDao();

    public synchronized static Hidden getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            Hidden.class,
                            "favorite_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

}
