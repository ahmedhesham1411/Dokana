package com.example.aldokana.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aldokana.Interfaces.OffersItemClick;
import com.example.aldokana.Model.AddressesModel;
import com.example.aldokana.Model.OffersDataModel;
import com.example.aldokana.Model.OffersModel;
import com.example.aldokana.Model.ProductsDataModel;
import com.example.aldokana.R;
import com.example.aldokana.databinding.AddressesItemBinding;
import com.example.aldokana.databinding.OffersItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder> {

    Activity activity;
    List<OffersDataModel> offersModels;
    List<ProductsDataModel> productsDataModels;
    OffersItemClick offersItemClick;

    public OffersAdapter(Activity activity, List<ProductsDataModel> productsDataModels,OffersItemClick offersItemClick) {
        this.activity = activity;
        this.productsDataModels = productsDataModels;
        this.offersItemClick=offersItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OffersItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.offers_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.disc.setText(productsDataModels.get(position).getName());
        //holder.binding.disc.setText(offersModels.get(position).getDisc());
        try {
            Picasso.get().load(productsDataModels.get(position).getImages().get(1).getPhoto()).into(holder.binding.cardImg);
        }catch (Exception e){}
        holder.binding.itemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offersItemClick.itemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productsDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private OffersItemBinding binding;

        private ViewHolder(OffersItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
