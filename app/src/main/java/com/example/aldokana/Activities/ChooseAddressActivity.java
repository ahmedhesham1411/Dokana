package com.example.aldokana.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aldokana.Activities.AddAddressActivity;
import com.example.aldokana.Activities.AddressesActivity;
import com.example.aldokana.Adapter.AddressesAdapter;
import com.example.aldokana.Adapter.ChooseAddressesAdapter;
import com.example.aldokana.Model.AddressesModel;
import com.example.aldokana.Model.UserAddressesModel;
import com.example.aldokana.R;
import com.example.aldokana.databinding.ActivityChooseAddressBinding;
import com.example.aldokana.viewModels.ChooseAddressViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChooseAddressActivity extends BaseActivity {

    public ActivityChooseAddressBinding binding;
    ChooseAddressesAdapter chooseAddressesAdapter;
    ChooseAddressViewModel viewModel;
    UserAddressesModel userAddressesModel2;
    int test = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_address);

        viewModel = new ChooseAddressViewModel(this);
        binding.setChooseAddressVM(viewModel);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.addNewAddressTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseAddressActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initRecycler(UserAddressesModel userAddressesModel){
        userAddressesModel2 = userAddressesModel;
        chooseAddressesAdapter = new ChooseAddressesAdapter(ChooseAddressActivity.this,userAddressesModel2.getUser_addresses());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.myAddressesRecycler.setLayoutManager(linearLayoutManager);
        binding.myAddressesRecycler.setHasFixedSize(true);
        binding.myAddressesRecycler.setAdapter(chooseAddressesAdapter);
    }

    @Override
    protected void onPause() {
        test =1;
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (test==1){
            viewModel = new ChooseAddressViewModel(this);
            binding.setChooseAddressVM(viewModel);
        }
    }

}