package com.example.aldokana.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aldokana.Activities.NotificationsActivity;
import com.example.aldokana.Interfaces.NotificationClick;
import com.example.aldokana.Model.NotificationDataModel;
import com.example.aldokana.Model.NotificationModel;
import com.example.aldokana.R;
import com.example.aldokana.databinding.NotificationItemBinding;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    NotificationsActivity activity;
    List<NotificationDataModel> notificationModels;
    NotificationClick notificationClick;

    public NotificationAdapter(NotificationsActivity activity, List<NotificationDataModel> notificationModels,NotificationClick notificationClick) {
        this.activity = activity;
        this.notificationModels = notificationModels;
        this.notificationClick = notificationClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NotificationItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.notification_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.notiTitle.setText(notificationModels.get(position).getTitle());
        holder.binding.notiDesc.setText(notificationModels.get(position).getBody());
        int status = notificationModels.get(position).getType();
        if (status == 0){
            holder.binding.circleColored.setBackgroundDrawable(activity.getDrawable(R.drawable.shape_circle_grey));
        }
        if (status == 2){
            holder.binding.circleColored.setBackgroundDrawable(activity.getDrawable(R.drawable.shape_circle_yellow));
        }
        if (status == 3){
            holder.binding.circleColored.setBackgroundDrawable(activity.getDrawable(R.drawable.shape_circle_red));
        }
        if (status == 4){
            holder.binding.circleColored.setBackgroundDrawable(activity.getDrawable(R.drawable.shape_circle_moov));
        }
        if (status == 5){
            holder.binding.circleColored.setBackgroundDrawable(activity.getDrawable(R.drawable.shape_circle_black));
        }
        holder.binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               notificationClick.delete(notificationModels.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return notificationModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private NotificationItemBinding binding;

        private ViewHolder(NotificationItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
