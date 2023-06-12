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

    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "vote_average")
    private String voteAverage;

    public FavoriteTv() {
    }

    public FavoriteTv(int id, String title, String releaseDate, String posterPath, String backdropPath, String overview, String voteAverage) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.voteAverage = voteAverage;
    }

    protected FavoriteTv(Parcel in) {
        id = in.readInt();
        title = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        releaseDate = in.readString();
        overview = in.readString();
        voteAverage = in.readString();
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

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }


    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(releaseDate);
        parcel.writeString(posterPath);
        parcel.writeString(backdropPath);
        parcel.writeString(overview);
        parcel.writeString(voteAverage);
    }
}


