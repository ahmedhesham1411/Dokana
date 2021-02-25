package com.example.aldokana.Adapter;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aldokana.Interfaces.HomeItemClick;
import com.example.aldokana.Model.HomeItemModel;
import com.example.aldokana.R;
import com.example.aldokana.databinding.HomeItemBinding;
import com.example.aldokana.databinding.SimilarItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarAdapter extends RecyclerView.Adapter<SimilarAdapter.ViewHolder> {

    Activity activity;
    List<HomeItemModel> homeItemModels;

    public SimilarAdapter(Activity activity, List<HomeItemModel> homeItemModels) {
        this.activity = activity;
        this.homeItemModels = homeItemModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SimilarItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.similar_item, parent, false);
        return new ViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.binding.title.setText(homeItemModels.get(position).getTitle());
        holder.binding.discr.setText(homeItemModels.get(position).getDisc());
        holder.binding.price.setText(homeItemModels.get(position).getPrice());

        holder.binding.itemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeItemModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SimilarItemBinding binding;

        private ViewHolder(SimilarItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
