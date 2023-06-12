package com.noval.valemedia.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noval.valemedia.R;
import com.noval.valemedia.adapter.FavoriteAdapter;
import com.noval.valemedia.databinding.FragmentFavoriteBinding;
import com.noval.valemedia.databinding.FragmentMovieBinding;
import com.noval.valemedia.storage.FavoriteMovie;
import com.noval.valemedia.storage.FavoriteTv;
import com.noval.valemedia.storage.Hidden;
import com.noval.valemedia.storage.HiddenFavorite;
import com.noval.valemedia.storage.HiddenHelper;

import java.util.List;


public class FavoriteFragment extends Fragment {
    private FragmentFavoriteBinding binding;
    private FavoriteAdapter adapter;
    private HiddenHelper hiddenHelper;
    private Hidden hidden;

    public FavoriteFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hiddenHelper = new HiddenHelper(requireActivity().getApplicationContext());
        hidden = Hidden.getInstance(requireActivity().getApplicationContext());

        binding.rvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadData();


    }

    private void loadData() {
        hidden.favoriteDao().getAllMovie().observe(getViewLifecycleOwner(), favoriteMovies ->
                hidden.favoriteDao().getAllTvShows().observe(getViewLifecycleOwner(), favoriteTvShows -> {
                    adapter = new FavoriteAdapter(favoriteMovies, favoriteTvShows);
                    adapter.notifyDataSetChanged();
                    binding.rvContent.setAdapter(adapter);

                    if (favoriteMovies.size() == 0 && favoriteTvShows.size() == 0) {
                        binding.tvAlert.setVisibility(View.VISIBLE);
                    }
                }));
    }
}