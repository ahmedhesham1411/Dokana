package com.example.aldokana.Adapter;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aldokana.Model.SpecificationsDataModel;
import com.example.aldokana.Model.SubSpecificationsDataModel;
import com.example.aldokana.R;
import com.example.aldokana.databinding.QuestionsItemBinding;
import com.example.aldokana.databinding.SpecificationsExpandedRecyclerItemBinding;

import java.util.List;

public class SpecifcationsExpandableAdapter extends RecyclerView.Adapter<SpecifcationsExpandableAdapter.ViewHolder> {

    Activity activity;
    List<SubSpecificationsDataModel> subSpecificationsDataModels;
    Boolean expandable=false;
    public SpecifcationsExpandableAdapter(Activity activity, List<SubSpecificationsDataModel> subSpecificationsDataModels) {
        this.activity = activity;
        this.subSpecificationsDataModels = subSpecificationsDataModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SpecificationsExpandedRecyclerItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.specifications_expanded_recycler_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.key.setText(subSpecificationsDataModels.get(position).getKey());
        holder.binding.value.setText(subSpecificationsDataModels.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return subSpecificationsDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SpecificationsExpandedRecyclerItemBinding binding;

        private ViewHolder(SpecificationsExpandedRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
