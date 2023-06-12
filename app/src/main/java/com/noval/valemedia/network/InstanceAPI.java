package com.noval.valemedia.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstanceAPI {
    public static TMDBConfig service() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(TMDBConfig.class);
    }
}
