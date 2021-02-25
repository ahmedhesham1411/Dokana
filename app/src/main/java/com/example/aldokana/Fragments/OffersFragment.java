package com.example.aldokana.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aldokana.Activities.CartActivity;
import com.example.aldokana.Activities.NotificationsActivity;
import com.example.aldokana.Activities.ProductDetailsActivity;
import com.example.aldokana.Adapter.NotificationAdapter;
import com.example.aldokana.Adapter.OffersAdapter;
import com.example.aldokana.Interfaces.OffersItemClick;
import com.example.aldokana.Model.BasketItemsResponse;
import com.example.aldokana.Model.NotificationModel;
import com.example.aldokana.Model.OffersModel;
import com.example.aldokana.Model.OffersResponse;
import com.example.aldokana.Model.ProductsDataModel;
import com.example.aldokana.Model.ProductsResponse;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;
import com.example.aldokana.databinding.FragmentOffersBinding;
import com.example.aldokana.viewModels.OffersFragmentViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class OffersFragment extends Fragment implements OffersItemClick {

    public FragmentOffersBinding binding;
    OffersAdapter offersAdapter;
    OffersFragmentViewModel viewModel;
    List<ProductsDataModel> productsDataModels;
    String Token;
    Preferences preferences;
    int test = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_offers, container, false);

        viewModel = new OffersFragmentViewModel(OffersFragment.this);
        binding.setOffersVM(viewModel);

        Token = preferences.GetToken(getContext());
        binding.notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Token.equals("visitor")){
                    Constant.logInDialog("a",getActivity());
                }else {
                    Intent intent = new Intent(getContext(), NotificationsActivity.class);
                    startActivity(intent);
                }
            }
        });

        binding.goToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Token.equals("visitor")){
                    Constant.logInDialog("a",getActivity());
                }else {
                    Intent intent = new Intent(getContext(), CartActivity.class);
                    startActivity(intent);
                }
            }
        });
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (test==1) {
            viewModel = new OffersFragmentViewModel(OffersFragment.this);
            binding.setOffersVM(viewModel);
        }
    }


    public void initRecycler(ProductsResponse productsResponse,BasketItemsResponse basketItemsResponse){
        productsDataModels = productsResponse.getData().getProducts();

        for (int i=0;i<basketItemsResponse.getData().getBasket_items().size();i++){
            for (int j=0;j<productsDataModels.size();j++){
                if (productsDataModels.get(j).getId()==basketItemsResponse.getData().getBasket_items().get(i).getProduct_id()){
                    //Toast.makeText(getContext(), String.valueOf(productsDataModels.get(j).getId()), Toast.LENGTH_SHORT).show();
                    productsDataModels.get(j).setCounter(Integer.parseInt(String.valueOf(basketItemsResponse.getData().getBasket_items().get(i).getCount())));
                    Log.d("dddd", String.valueOf(productsDataModels.get(j).getId()));
                }
            }
        }

        offersAdapter = new OffersAdapter(getActivity(),productsDataModels,OffersFragment.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.offersRecycler.setLayoutManager(linearLayoutManager);
        binding.offersRecycler.setHasFixedSize(true);
        binding.offersRecycler.setAdapter(offersAdapter);

    }

    public void initRecycler2(ProductsResponse productsResponse){
        productsDataModels = productsResponse.getData().getProducts();

        offersAdapter = new OffersAdapter(getActivity(),productsDataModels,OffersFragment.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.offersRecycler.setLayoutManager(linearLayoutManager);
        binding.offersRecycler.setHasFixedSize(true);
        binding.offersRecycler.setAdapter(offersAdapter);

    }

    @Override
    public void itemClick(int position) {
        test=1;
        Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
        Gson gson = new Gson();
        ProductsDataModel productsDataModel = new ProductsDataModel();

        productsDataModel.setId(productsDataModels.get(position).getId());
        productsDataModel.setName(productsDataModels.get(position).getName());
        productsDataModel.setPrice(productsDataModels.get(position).getPrice());
        productsDataModel.setColor(productsDataModels.get(position).getColor());
        productsDataModel.setStorage(productsDataModels.get(position).getStorage());
        productsDataModel.setCompany(productsDataModels.get(position).getCompany());
        productsDataModel.setStock(productsDataModels.get(position).getStock());
        productsDataModel.setType(productsDataModels.get(position).getType());
        productsDataModel.setRate(productsDataModels.get(position).getRate());
        productsDataModel.setImages(productsDataModels.get(position).getImages());
        productsDataModel.setDescription(productsDataModels.get(position).getDescription());
        productsDataModel.setSpecifications(productsDataModels.get(position).getSpecifications());
        productsDataModel.setCounter(productsDataModels.get(position).getCounter());

        String myJson = gson.toJson(productsDataModel);
        intent.putExtra("myjson", myJson);
        startActivity(intent);
    }
}