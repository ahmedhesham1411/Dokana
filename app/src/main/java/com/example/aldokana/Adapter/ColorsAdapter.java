package com.example.aldokana.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aldokana.Interfaces.ColorClick;
import com.example.aldokana.Model.AddressesModel;
import com.example.aldokana.Model.ColorsModel;
import com.example.aldokana.R;
import com.example.aldokana.databinding.AddressesItemBinding;
import com.example.aldokana.databinding.ColorsItemBinding;

import java.util.List;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ViewHolder> {

    Activity activity;
    List<ColorsModel> colorsModels;
    ColorClick colorClick;
    List<ColorsModel> available_colors;
    ;
    public ColorsAdapter(Activity activity, List<ColorsModel> colorsModels,ColorClick colorClick,List<ColorsModel> available_colors) {
        this.activity = activity;
        this.colorsModels = colorsModels;
        this.colorClick = colorClick;
        this.available_colors=available_colors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ColorsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.colors_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (available_colors.get(position).getName().equals("احمر"))
            holder.binding.circleColored.setBackgroundDrawable(activity.getDrawable(R.drawable.shape_circle_red));
        else  if (available_colors.get(position).getName().equals("ازؤق"))
            holder.binding.circleColored.setBackgroundDrawable(activity.getDrawable(R.drawable.shape_circle_blue));
        else  if (available_colors.get(position).getName().equals("اسود"))
            holder.binding.circleColored.setBackgroundDrawable(activity.getDrawable(R.drawable.shape_circle_black));
        else  if (available_colors.get(position).getName().equals("اصفر"))
            holder.binding.circleColored.setBackgroundDrawable(activity.getDrawable(R.drawable.shape_circle_yellow));
        else  if (available_colors.get(position).getName().equals("اخضر"))
            holder.binding.circleColored.setBackgroundDrawable(activity.getDrawable(R.drawable.shape_circle_green));

        if (available_colors.get(position).isSelected()){
            holder.binding.underlineStorageLayout.setVisibility(View.VISIBLE);
        }else if (!available_colors.get(position).isSelected()){
            holder.binding.underlineStorageLayout.setVisibility(View.GONE);
        }
        holder.binding.colorLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorClick.colorClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return available_colors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ColorsItemBinding binding;

        private ViewHolder(ColorsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
