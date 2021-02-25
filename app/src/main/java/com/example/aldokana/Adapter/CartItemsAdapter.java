package com.example.aldokana.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aldokana.Interfaces.CartItemPlusMinus;
import com.example.aldokana.Interfaces.ColorClick;
import com.example.aldokana.Model.BasketItemDataModel;
import com.example.aldokana.Model.ColorsModel;
import com.example.aldokana.Model.HomeItemModel;
import com.example.aldokana.R;
import com.example.aldokana.databinding.CartItemBinding;
import com.example.aldokana.databinding.ColorsItemBinding;

import java.util.List;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.ViewHolder> {

    Activity activity;
    CartItemPlusMinus cartItemPlusMinus;
    List<BasketItemDataModel> basketItemDataModels;

    public CartItemsAdapter(Activity activity, List<BasketItemDataModel> basketItemDataModels,CartItemPlusMinus cartItemPlusMinus) {
        this.activity = activity;
        this.basketItemDataModels = basketItemDataModels;
        this.cartItemPlusMinus = cartItemPlusMinus;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.cart_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.title.setText(basketItemDataModels.get(position).getProduct());
        holder.binding.disc.setText(basketItemDataModels.get(position).getDescription());
        holder.binding.price.setText(String.valueOf(basketItemDataModels.get(position).getTotal_cost()));
        holder.binding.counterTxt.setText(String.valueOf(basketItemDataModels.get(position).getCount()));
        holder.binding.plusLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItemPlusMinus.plus(basketItemDataModels.get(position).getProduct_id());
            }
        });
        holder.binding.minusLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItemPlusMinus.minus(basketItemDataModels.get(position).getProduct_id());
            }
        });
        holder.binding.deleteItemCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItemPlusMinus.delete(basketItemDataModels.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return basketItemDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CartItemBinding binding;

        private ViewHolder(CartItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
