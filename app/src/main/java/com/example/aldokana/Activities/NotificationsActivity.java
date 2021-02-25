package com.example.aldokana.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.aldokana.Adapter.NotificationAdapter;
import com.example.aldokana.Interfaces.NotificationClick;
import com.example.aldokana.Model.NotificationModel;
import com.example.aldokana.Model.NotificationResponse;
import com.example.aldokana.R;
import com.example.aldokana.databinding.ActivityNotificationsBinding;
import com.example.aldokana.viewModels.NotificationsViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends BaseActivity implements NotificationClick {

    public ActivityNotificationsBinding binding;
    NotificationAdapter notificationAdapter;
    NotificationsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notifications);

        viewModel=new NotificationsViewModel(this);
        binding.setNotificationVM(viewModel);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initRecycler(NotificationResponse notificationResponse){
        notificationAdapter = new NotificationAdapter(NotificationsActivity.this,notificationResponse.getData().getNotifications(),NotificationsActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.notificationRecycler.setLayoutManager(linearLayoutManager);
        binding.notificationRecycler.setHasFixedSize(true);
        binding.notificationRecycler.setAdapter(notificationAdapter);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel=new NotificationsViewModel(this);
        binding.setNotificationVM(viewModel);
    }

    @Override
    public void delete(int id) {
        viewModel.showDeleteDialog(this,id);
    }
}