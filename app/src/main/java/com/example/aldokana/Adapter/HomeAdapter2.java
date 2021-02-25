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
import com.example.aldokana.Model.ProductsDataModel;
import com.example.aldokana.R;
import com.example.aldokana.databinding.HomeItem2Binding;
import com.example.aldokana.databinding.HomeItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter2 extends RecyclerView.Adapter<HomeAdapter2.ViewHolder> {

    Activity activity;
    HomeItemClick homeItemClick;
    List<ProductsDataModel> productsDataModels;

    public HomeAdapter2(Activity activity, List<ProductsDataModel> productsDataModels, HomeItemClick homeItemClick) {
        this.activity = activity;
        this.productsDataModels = productsDataModels;
        this.homeItemClick = homeItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomeItem2Binding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.home_item2, parent, false);
        return new ViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            Picasso.get().load(productsDataModels.get(position).getImages().get(0).getPhoto()).into(holder.binding.cardImg);
        }catch (Exception e){}
        holder.binding.title.setText(productsDataModels.get(position).getName());
        holder.binding.price.setText(String.valueOf(productsDataModels.get(position).getPrice() - productsDataModels.get(position).getDiscount()));
        holder.binding.priceBeforeSale.setText(String.valueOf(productsDataModels.get(position).getPrice()));
        holder.binding.counterTxt.setText(String.valueOf(productsDataModels.get(position).getCounter()));

        try {
            holder.binding.discr.setText(productsDataModels.get(position).getDescription().get(0).getMain_description());
        }catch (Exception e){}
        if (productsDataModels.get(position).getDiscount()!=0){
            holder.binding.saleImg.setVisibility(View.VISIBLE);
            holder.binding.textSaleCurrency.setVisibility(View.VISIBLE);
            holder.binding.priceBeforeSale.setVisibility(View.VISIBLE);
        }else if (productsDataModels.get(position).getDiscount()==0){
            holder.binding.saleImg.setVisibility(View.GONE);
            holder.binding.textSaleCurrency.setVisibility(View.GONE);
            holder.binding.priceBeforeSale.setVisibility(View.GONE);
        }

        if (productsDataModels.get(position).getCounter()==0){
            holder.binding.layoutPlusMinus.setBackgroundDrawable(activity.getDrawable(R.drawable.shape_border_home));
            holder.binding.plus.setBackgroundDrawable(activity.getDrawable(R.drawable.inactiveplus));
            holder.binding.minus.setBackgroundDrawable(activity.getDrawable(R.drawable.inactiveminus));
            holder.binding.counterTxt.setTextColor(activity.getColor(R.color.grey));

        }else if (productsDataModels.get(position).getCounter()!=0){
            holder.binding.layoutPlusMinus.setBackgroundDrawable(activity.getDrawable(R.drawable.shape_border_home_active));
            holder.binding.plus.setBackgroundDrawable(activity.getDrawable(R.drawable.activeplus));
            holder.binding.minus.setBackgroundDrawable(activity.getDrawable(R.drawable.activeminus));
            holder.binding.counterTxt.setTextColor(activity.getColor(R.color.red));
        }

        holder.binding.plusLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeItemClick.plus(position);
            }
        });

        holder.binding.minusLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeItemClick.minus(position);
            }
        });

        holder.binding.itemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeItemClick.itemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private HomeItem2Binding binding;

        private ViewHolder(HomeItem2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
