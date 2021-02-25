package com.example.aldokana.Adapter;

import android.app.Activity;
import android.bluetooth.BluetoothAssignedNumbers;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aldokana.Interfaces.MenuClick;
import com.example.aldokana.Model.AddressesModel;
import com.example.aldokana.Model.CategoriesDataModel;
import com.example.aldokana.Model.MenuModel;
import com.example.aldokana.R;
import com.example.aldokana.databinding.AddressesItemBinding;
import com.example.aldokana.databinding.ItemMenuBinding;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    Activity activity;
    List<CategoriesDataModel> categoriesDataModels;
    MenuClick menuClick;

    public MenuAdapter(Activity activity, List<CategoriesDataModel> categoriesDataModels,MenuClick menuClick) {
        this.activity = activity;
        this.categoriesDataModels = categoriesDataModels;
        this.menuClick = menuClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMenuBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_menu, parent, false);
        return new ViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.menuName.setText(categoriesDataModels.get(position).getName());
/*

        for (int i = 0; i < menuModels.size(); i++) {
            if (position != menuModels.get(i).getId())
                menuModels.get(i).setSelected(false);
            else
                menuModels.get(i).setSelected(true);
        }
*/

        if (categoriesDataModels.get(position).isSelected()) {
            holder.binding.menuLin.setBackgroundResource(R.drawable.shape_rounded_red);
            holder.binding.menuName.setTextColor(activity.getColor(R.color.white));
        } else {
            holder.binding.menuLin.setBackgroundColor(Color.TRANSPARENT);
            holder.binding.menuName.setTextColor(activity.getColor(R.color.colorTextHint));
        }

        holder.binding.menuLin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                menuClick.MenuClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemMenuBinding binding;

        private ViewHolder(ItemMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
