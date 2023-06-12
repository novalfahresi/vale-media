package com.noval.valemedia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.noval.valemedia.DetailActivity;
import com.noval.valemedia.databinding.ItemContentBinding;
import com.noval.valemedia.network.TV;

import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {

    private List<TV> tv;

    private Context context;

    public TvAdapter(List<TV> tv) {
        this.tv = tv;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContentBinding binding = ItemContentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TvAdapter.ViewHolder holder, int position) {
        holder.onBind(tv.get(position));
    }

    @Override
    public int getItemCount() {
        return tv.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemContentBinding binding;
        public ViewHolder(@NonNull ItemContentBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;


        }
        public void onBind(TV tv){
            binding.tvTitle.setText(tv.getName());
            binding.tvDate.setText(tv.getFirstAirDate());
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500" + tv.getPosterPath())
                    .into(binding.ivPoster);

            binding.getRoot().setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("TV", tv);
                context.startActivity(intent);
            });
        }
    }
}
