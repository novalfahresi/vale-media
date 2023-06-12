package com.noval.valemedia.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDBConfig {
    @GET("movie/popular")
    Call<DataResponse<List<Movie>>> getMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("tv/popular")
    Call<DataResponse<List<TV>>> getTVShows(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
