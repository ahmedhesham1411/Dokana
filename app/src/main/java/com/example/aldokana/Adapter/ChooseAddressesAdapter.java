package com.example.aldokana.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aldokana.Model.AddressesModel;
import com.example.aldokana.Model.UserAddressModel;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Preferences;
import com.example.aldokana.databinding.AddressesItem2Binding;
import com.example.aldokana.databinding.AddressesItemBinding;

import java.util.List;

public class ChooseAddressesAdapter extends RecyclerView.Adapter<ChooseAddressesAdapter.ViewHolder> {

    Activity activity;
    List<AddressesModel> addressesModels;
    Preferences preferences;
    List<UserAddressModel> user_addresses;

    public ChooseAddressesAdapter(Activity activity,List<UserAddressModel> user_addresses) {
        this.activity = activity;
        this.user_addresses = user_addresses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AddressesItem2Binding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.addresses_item2, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.notiTitle.setText(user_addresses.get(position).getAddress());
        holder.binding.name.setText(user_addresses.get(position).getFirst_name() + " "+user_addresses.get(position).getLast_name());
        holder.binding.addressMobile.setText(user_addresses.get(position).getPhone());

        holder.binding.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.SaveAddressId(activity,user_addresses.get(position).getId());
                preferences.SaveAddressTitle(activity,user_addresses.get(position).getAddress());
                preferences.SaveAddressName(activity,user_addresses.get(position).getFirst_name());
                preferences.SaveAddressPhone(activity,user_addresses.get(position).getPhone());
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return user_addresses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private AddressesItem2Binding binding;

        private ViewHolder(AddressesItem2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
