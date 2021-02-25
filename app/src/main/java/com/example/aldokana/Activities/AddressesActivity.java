package com.example.aldokana.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aldokana.Activities.BaseActivity;
import com.example.aldokana.Adapter.AddressesAdapter;
import com.example.aldokana.Adapter.NotificationAdapter;
import com.example.aldokana.Interfaces.DeleteAddressClick;
import com.example.aldokana.Model.AddressesModel;
import com.example.aldokana.Model.NotificationModel;
import com.example.aldokana.Model.UserAddressesModel;
import com.example.aldokana.Model.UserAddressesResponse;
import com.example.aldokana.R;
import com.example.aldokana.databinding.ActivityAddressesBinding;
import com.example.aldokana.viewModels.AddressesViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddressesActivity extends BaseActivity implements DeleteAddressClick {

    public ActivityAddressesBinding binding;
    AddressesAdapter addressesAdapter;
    AddressesViewModel viewModel;
    UserAddressesModel userAddressesModel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_addresses);

        viewModel = new AddressesViewModel(this);
        binding.setAddressesVM(viewModel);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.addNewAddressTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddressesActivity.this,AddAddressActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initRecycler(UserAddressesModel userAddressesModel){
        userAddressesModel2 = userAddressesModel;
        addressesAdapter = new AddressesAdapter(AddressesActivity.this,userAddressesModel.getUser_addresses(),AddressesActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.myAddressesRecycler.setLayoutManager(linearLayoutManager);
        binding.myAddressesRecycler.setHasFixedSize(true);
        binding.myAddressesRecycler.setAdapter(addressesAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel = new AddressesViewModel(this);
        binding.setAddressesVM(viewModel);
    }

    @Override
    public void delete(int position) {
        viewModel.showDeleteDialog(AddressesActivity.this,userAddressesModel2.getUser_addresses().get(position).getId());
    }
}