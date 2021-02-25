package com.example.aldokana.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aldokana.Interfaces.DeleteAddressClick;
import com.example.aldokana.Model.AddressesModel;
import com.example.aldokana.Model.NotificationModel;
import com.example.aldokana.Model.UserAddressModel;
import com.example.aldokana.Model.UserAddressesModel;
import com.example.aldokana.R;
import com.example.aldokana.databinding.AddressesItemBinding;
import com.example.aldokana.databinding.NotificationItemBinding;

import java.util.List;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.ViewHolder> {

    Activity activity;
    List<UserAddressModel> user_addresses;
    DeleteAddressClick deleteAddressClick;

    public AddressesAdapter(Activity activity, List<UserAddressModel> user_addresses,DeleteAddressClick deleteAddressClick) {
        this.activity = activity;
        this.user_addresses = user_addresses;
        this.deleteAddressClick = deleteAddressClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AddressesItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.addresses_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.notiTitle.setText(user_addresses.get(position).getAddress());
        holder.binding.addressName.setText(user_addresses.get(position).getFirst_name() + " "+user_addresses.get(position).getLast_name());
        holder.binding.addressMobile.setText(user_addresses.get(position).getPhone());
        holder.binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAddressClick.delete(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return user_addresses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private AddressesItemBinding binding;

        private ViewHolder(AddressesItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
