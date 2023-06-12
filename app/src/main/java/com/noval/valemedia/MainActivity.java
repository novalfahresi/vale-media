package com.noval.valemedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.noval.valemedia.databinding.ActivityMainBinding;
import com.noval.valemedia.fragment.FavoriteFragment;
import com.noval.valemedia.fragment.MovieFragment;
import com.noval.valemedia.fragment.TvFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        gantiFragment(new MovieFragment()); // Mulai dari movie

        binding.bottonnav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_movie) {
                gantiFragment(new MovieFragment());
            } else if (item.getItemId() == R.id.nav_tv) {
                gantiFragment(new TvFragment());
            } else if (item.getItemId() == R.id.nav_favorites) {
                gantiFragment(new FavoriteFragment());
            }
            return false;
        });
    }

    private void gantiFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, fragment)
                .commit();
    }
}