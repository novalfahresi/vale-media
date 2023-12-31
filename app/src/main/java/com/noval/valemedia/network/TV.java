package com.noval.valemedia.network;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TV implements Parcelable {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("first_air_date")
    String firstAirDate;
    @SerializedName("poster_path")
    String posterPath;
    @SerializedName("backdrop_path")
    String backdropPath;
    @SerializedName("overview")
    String overview;
    @SerializedName("vote_average")
    String voteAverage;
    @SerializedName("genre_ids")
    int[] genreIDs;
    @SerializedName("vote_count")
    int voteCount;

    public TV(int id, String name, String firstAirDate, String posterPath, String backdropPath, String overview, String voteAverage, int[] genreIDs, int voteCount) {
        this.id = id;
        this.name = name;
        this.firstAirDate = firstAirDate;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.genreIDs = genreIDs;
        this.voteCount = voteCount;
    }

    protected TV(Parcel in) {
        id = in.readInt();
        name = in.readString();
        firstAirDate = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        overview = in.readString();
        voteAverage = in.readString();
        genreIDs = in.createIntArray();
        voteCount = in.readInt();
    }

    public static final Creator<TV> CREATOR = new Creator<TV>() {
        @Override
        public TV createFromParcel(Parcel in) {
            return new TV(in);
        }

        @Override
        public TV[] newArray(int size) {
            return new TV[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public int[] getGenreIDs() {
        return genreIDs;
    }

    public int getVoteCount() {
        return voteCount;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(firstAirDate);
        parcel.writeString(posterPath);
        parcel.writeString(backdropPath);
        parcel.writeString(overview);
        parcel.writeString(voteAverage);
        parcel.writeIntArray(genreIDs);
        parcel.writeInt(voteCount);
    }
}
