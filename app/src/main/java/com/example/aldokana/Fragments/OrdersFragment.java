package com.example.aldokana.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aldokana.Activities.CartActivity;
import com.example.aldokana.Activities.NotificationsActivity;
import com.example.aldokana.Adapter.OrdersAdapter;
import com.example.aldokana.Interfaces.OrdersClick;
import com.example.aldokana.Model.OrdersDataModel;
import com.example.aldokana.Model.OrdersModel;
import com.example.aldokana.Model.OrdersResponse;
import com.example.aldokana.R;
import com.example.aldokana.Activities.SearchActivity;
import com.example.aldokana.databinding.FragmentOrdersBinding;
import com.example.aldokana.viewModels.OrdersFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment implements OrdersClick {

    public FragmentOrdersBinding binding;
    OrdersAdapter ordersAdapter;
    OrdersFragmentViewModel viewModel;
    List<OrdersDataModel> ordersDataModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_orders, container, false);

        viewModel= new OrdersFragmentViewModel(OrdersFragment.this);
        binding.setOrdersVM(viewModel);


        binding.notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NotificationsActivity.class);
                startActivity(intent);
            }
        });

        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        binding.goToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CartActivity.class);
                startActivity(intent);
            }
        });


        return binding.getRoot();
    }

    public void initRecycler(OrdersResponse ordersResponse){
        ordersDataModels = ordersResponse.getData().getOrders();
        ordersAdapter = new OrdersAdapter(getActivity(),ordersDataModels,OrdersFragment.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.ordersRecycler.setLayoutManager(linearLayoutManager);
        binding.ordersRecycler.setHasFixedSize(true);
        binding.ordersRecycler.setAdapter(ordersAdapter);

    }

    @Override
    public void DeleteOrder(int position) {
        viewModel.showDeleteDialog(ordersDataModels.get(position).getId());
    }
/*    @Override
    public void onResume() {
        super.onResume();
        viewModel= new OrdersFragmentViewModel(OrdersFragment.this);
        binding.setOrdersVM(viewModel);
    }*/
}