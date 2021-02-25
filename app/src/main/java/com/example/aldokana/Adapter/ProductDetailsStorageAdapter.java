package com.example.aldokana.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aldokana.Interfaces.ProductStorageClick;
import com.example.aldokana.Model.OffersModel;
import com.example.aldokana.Model.ProductDetailsStorageModel;
import com.example.aldokana.R;
import com.example.aldokana.databinding.OffersItemBinding;
import com.example.aldokana.databinding.StorageMenuBinding;

import java.util.List;

public class ProductDetailsStorageAdapter extends RecyclerView.Adapter<ProductDetailsStorageAdapter.ViewHolder> {

    Activity activity;
    List<ProductDetailsStorageModel> productDetailsStorageModels;
    ProductStorageClick productStorageClick;
    List<ProductDetailsStorageModel> available_storages;

    public ProductDetailsStorageAdapter(Activity activity, List<ProductDetailsStorageModel> productDetailsStorageModels,ProductStorageClick productStorageClick,List<ProductDetailsStorageModel> available_storages) {
        this.activity = activity;
        this.productDetailsStorageModels = productDetailsStorageModels;
        this.productStorageClick = productStorageClick;
        this.available_storages=available_storages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StorageMenuBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.storage_menu, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.storageTxt.setText(String.valueOf(available_storages.get(position).getStorage()));
        if (available_storages.get(position).getSelected()){
            holder.binding.underlineStorageLayout.setVisibility(View.VISIBLE);
        }else {
            holder.binding.underlineStorageLayout.setVisibility(View.GONE);
        }
        holder.binding.storageLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productStorageClick.clickStorage(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return available_storages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private StorageMenuBinding binding;

        private ViewHolder(StorageMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
