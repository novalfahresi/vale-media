package com.noval.valemedia.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.noval.valemedia.R;
import com.noval.valemedia.adapter.MovieAdapter;
import com.noval.valemedia.databinding.FragmentMovieBinding;
import com.noval.valemedia.network.DataResponse;
import com.noval.valemedia.network.InstanceAPI;
import com.noval.valemedia.network.Movie;

import java.util.List;

import retrofit2.Call;

public class MovieFragment extends Fragment {
    private FragmentMovieBinding binding;

    public MovieFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMovieBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Call<DataResponse<List<Movie>>> client = InstanceAPI.service().getMovies("70320fbb66bd272fc5e9ef04e0c09b2b", "en-US");
        client.enqueue(new retrofit2.Callback<DataResponse<List<Movie>>>() {
            @Override
            public void onResponse(Call<DataResponse<List<Movie>>> call, retrofit2.Response<DataResponse<List<Movie>>> response) {
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body().getData();
                    MovieAdapter adapter = new MovieAdapter(movies);
                    adapter.setContext(getContext());
                    binding.rvContent.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<DataResponse<List<Movie>>> call, Throwable t) {
                Toast.makeText(getContext(), "Please Check Your Internet Connection!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}