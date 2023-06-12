package com.noval.valemedia.storage;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_tv_show_table")
public class FavoriteTv implements Parcelable {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

    public FavoriteTv() {
    }

    public FavoriteTv(int id, String title, String posterPath, String releaseDate) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
    }

    protected FavoriteTv(Parcel in) {
        id = in.readInt();
        title = in.readString();
        posterPath = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<FavoriteTv> CREATOR = new Creator<FavoriteTv>() {
        @Override
        public FavoriteTv createFromParcel(Parcel in) {
            return new FavoriteTv(in);
        }

        @Override
        public FavoriteTv[] newArray(int size) {
            return new FavoriteTv[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(posterPath);
        parcel.writeString(releaseDate);
    }
}


