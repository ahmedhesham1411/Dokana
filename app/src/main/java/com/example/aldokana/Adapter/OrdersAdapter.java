package com.example.aldokana.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aldokana.Activities.Login;
import com.example.aldokana.Interfaces.OrdersClick;
import com.example.aldokana.Model.OffersModel;
import com.example.aldokana.Model.OrdersDataModel;
import com.example.aldokana.Model.OrdersModel;
import com.example.aldokana.MyTextViewCairo;
import com.example.aldokana.R;
import com.example.aldokana.databinding.OffersItemBinding;
import com.example.aldokana.databinding.OrdersItemBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    Activity activity;
    List<OrdersDataModel> ordersModels;
    AlertDialog alertDialog;
    Date date;
    OrdersClick ordersClick;

    public OrdersAdapter(Activity activity, List<OrdersDataModel> ordersModels,OrdersClick ordersClick) {
        this.activity = activity;
        this.ordersModels = ordersModels;
        this.ordersClick = ordersClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrdersItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.orders_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        double cost = ordersModels.get(position).getOrder_cost() - ordersModels.get(position).getTotal_discount();
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssssss'Z'");
        String output = ordersModels.get(position).getOrder_creation_date().substring(0, ordersModels.get(position).getOrder_creation_date().indexOf('T'));
/*

        try {
            date = format.parse(ordersModels.get(position).getOrder_creation_date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
*/

        holder.binding.number.setText(String.valueOf(ordersModels.get(position).getOrder_items_count()));
        holder.binding.orderNumber.setText(ordersModels.get(position).getCode());
        holder.binding.money.setText(String.valueOf(cost));
        holder.binding.dateTxt.setText(output);

        if (ordersModels.get(position).getStatus().equals("2")){
            holder.binding.linearColor.setBackground(activity.getDrawable(R.drawable.shape_rounded_grey2));
            holder.binding.statusTxt.setText(activity.getString(R.string.canceled));
            holder.binding.deleteOrder.setVisibility(View.GONE);
        }
        if (ordersModels.get(position).getStatus().equals("0")){
            holder.binding.linearColor.setBackground(activity.getDrawable(R.drawable.shape_rounded_yellow));
            holder.binding.statusTxt.setText(activity.getString(R.string.waiting));
            holder.binding.deleteOrder.setVisibility(View.VISIBLE);

        }
        if (ordersModels.get(position).getStatus().equals("3")){
            holder.binding.linearColor.setBackground(activity.getDrawable(R.drawable.shape_rounded_green));
            holder.binding.statusTxt.setText(activity.getString(R.string.delivery_done));
            holder.binding.deleteOrder.setVisibility(View.VISIBLE);

        }
        if (ordersModels.get(position).getStatus().equals("4")){
            holder.binding.linearColor.setBackground(activity.getDrawable(R.drawable.shape_rounded_red));
            holder.binding.statusTxt.setText(activity.getString(R.string.delivering));
            holder.binding.deleteOrder.setVisibility(View.VISIBLE);

        }

        holder.binding.deleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ordersClick.DeleteOrder(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ordersModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private OrdersItemBinding binding;

        private ViewHolder(OrdersItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }



}
