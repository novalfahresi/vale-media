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
import com.noval.valemedia.adapter.TvAdapter;
import com.noval.valemedia.databinding.FragmentMovieBinding;
import com.noval.valemedia.databinding.FragmentTvBinding;
import com.noval.valemedia.network.DataResponse;
import com.noval.valemedia.network.InstanceAPI;
import com.noval.valemedia.network.Movie;
import com.noval.valemedia.network.TV;

import java.util.List;

import retrofit2.Call;


public class TvFragment extends Fragment {

    private FragmentTvBinding binding;
    public TvFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTvBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Call<DataResponse<List<TV>>> client = InstanceAPI.service().getTVShows("70320fbb66bd272fc5e9ef04e0c09b2b", "en-US");
        client.enqueue(new retrofit2.Callback<DataResponse<List<TV>>>() {
            @Override
            public void onResponse(Call<DataResponse<List<TV>>> call, retrofit2.Response<DataResponse<List<TV>>> response) {
                if (response.isSuccessful()) {
                    List<TV> tv = response.body().getData();
                    TvAdapter adapter = new TvAdapter(tv);
                    adapter.setContext(getContext());
                    binding.rvContent.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<DataResponse<List<TV>>> call, Throwable t) {
                Toast.makeText(getContext(), "Please Check Your Internet Connection!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}