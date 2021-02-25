package com.example.aldokana.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aldokana.Activities.CartActivity;
import com.example.aldokana.Activities.NotificationsActivity;
import com.example.aldokana.Activities.ProductDetailsActivity;
import com.example.aldokana.Activities.SearchActivity;
import com.example.aldokana.Adapter.HomeAdapter;
import com.example.aldokana.Adapter.MenuAdapter;
import com.example.aldokana.Adapter.OrdersAdapter;
import com.example.aldokana.Interfaces.HomeItemClick;
import com.example.aldokana.Interfaces.MenuClick;
import com.example.aldokana.Model.BasketItemsResponse;
import com.example.aldokana.Model.CategoriesDataModel;
import com.example.aldokana.Model.CategoriesResponse;
import com.example.aldokana.Model.HomeItemModel;
import com.example.aldokana.Model.MenuModel;
import com.example.aldokana.Model.OrdersModel;
import com.example.aldokana.Model.ProductsDataModel;
import com.example.aldokana.Model.ProductsResponse;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;
import com.example.aldokana.databinding.FragmentHomeBinding;
import com.example.aldokana.viewModels.HomeFragmentViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements MenuClick, HomeItemClick {

    public FragmentHomeBinding binding;
    MenuAdapter menuAdapter;
    List<MenuModel> menuModels;
    List<CategoriesDataModel> categoriesDataModels;
    List<HomeItemModel> homeItemModels;
    HomeAdapter homeAdapter;
    HomeFragmentViewModel viewModel;
    List<ProductsDataModel> productsDataModels;
    int test ;
    String Token;
    Preferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        viewModel = new HomeFragmentViewModel(HomeFragment.this);
        binding.setHomeVM(viewModel);
        Token = preferences.GetToken(getContext());

        test=0;
        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test =1;

                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        binding.notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Token.equals("visitor")){
                    Constant.logInDialog("a",getActivity());
                }else {
                    test = 1;
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
                    test = 1;
                    Intent intent = new Intent(getContext(), CartActivity.class);
                    startActivity(intent);
                }
            }
        });
        return binding.getRoot();

    }

    public void initMenuRecycler(CategoriesResponse categoriesResponse){
        categoriesDataModels = new ArrayList<>();
        categoriesDataModels.add(new CategoriesDataModel(1,"الكل",true));
        categoriesDataModels.addAll(categoriesResponse.getData().getCategories());
        menuAdapter = new MenuAdapter(getActivity(),categoriesDataModels,HomeFragment.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerMenu.setLayoutManager(linearLayoutManager);
        binding.recyclerMenu.setHasFixedSize(true);
        binding.recyclerMenu.setAdapter(menuAdapter);
    }

    public void initRecycler(ProductsResponse productsResponse,BasketItemsResponse basketItemsResponse){
       /* productsDataModels = new ArrayList<>();
        productsDataModels.clear();
        productsDataModels.addAll(productsResponse.getData().getProducts());*/
       productsDataModels=productsResponse.getData().getProducts();

        for (int i=0;i<basketItemsResponse.getData().getBasket_items().size();i++){
            for (int j=0;j<productsDataModels.size();j++){
                if (productsDataModels.get(j).getId()==basketItemsResponse.getData().getBasket_items().get(i).getProduct_id()){
                    //Toast.makeText(getContext(), String.valueOf(productsDataModels.get(j).getId()), Toast.LENGTH_SHORT).show();
                    productsDataModels.get(j).setCounter(Integer.parseInt(String.valueOf(basketItemsResponse.getData().getBasket_items().get(i).getCount())));
                    Log.d("dddd", String.valueOf(productsDataModels.get(j).getId()));
                }
            }
        }

        homeAdapter = new HomeAdapter(getActivity(),productsDataModels,HomeFragment.this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        binding.itemsRecycler.setLayoutManager(layoutManager);
        binding.itemsRecycler.setHasFixedSize(true);
        binding.itemsRecycler.setAdapter(homeAdapter);
    }

    public void initRecycler2(ProductsResponse productsResponse){
        productsDataModels =productsResponse.getData().getProducts();

        homeAdapter = new HomeAdapter(getActivity(),productsDataModels,HomeFragment.this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        binding.itemsRecycler.setLayoutManager(layoutManager);
        binding.itemsRecycler.setHasFixedSize(true);
        binding.itemsRecycler.setAdapter(homeAdapter);
    }

    @Override
    public void MenuClick(int position) {

        for (int i=0;i<categoriesDataModels.size();i++){
            if (i == position){
                categoriesDataModels.get(i).setSelected(true);
            }
            else{
                categoriesDataModels.get(i).setSelected(false);
            }
        }
        menuAdapter.notifyDataSetChanged();
        if (position==0){
            if (Token.equals("visitor")){
                viewModel.GetHome();
            }else
            viewModel.RefreshBasketItems2(position,categoriesDataModels.get(position).getId());
            //viewModel.GetHome();
        }else if (position!=0){
            if (Token.equals("visitor")){
                viewModel.GetFromCat(categoriesDataModels.get(position).getId());
            }else
                viewModel.RefreshBasketItems2(position,categoriesDataModels.get(position).getId());
            //viewModel.GetFromCat(categoriesDataModels.get(position).getId());
        }
    }

    @Override
    public void plus(int position) {
        if (Token.equals("visitor")){
            Constant.logInDialog("a",getActivity());
        }else {
            int counter = productsDataModels.get(position).getCounter();
            viewModel.AddToBasket(position, productsDataModels.get(position).getId(), 1);
        }
    }

    public void PlusDone(int position){
        int counter = productsDataModels.get(position).getCounter();
        productsDataModels.get(position).setCounter(counter+1);
        homeAdapter.notifyDataSetChanged();
        viewModel.RefreshBasketItems();
    }

    @Override
    public void minus(int position) {
        if (Token.equals("visitor")){
            Constant.logInDialog("a",getActivity());
        }else {
            int counter = productsDataModels.get(position).getCounter();
            if (counter == 0) {
            } else {
                viewModel.MinusBasket(position, productsDataModels.get(position).getId(), -1);
            }
        }
    }

    public void MinusDone(int position){
        int counter = productsDataModels.get(position).getCounter();
        productsDataModels.get(position).setCounter(counter-1);
        homeAdapter.notifyDataSetChanged();
        viewModel.RefreshBasketItems();
    }

    @Override
    public void itemClick(int position) {
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
        productsDataModel.setSerial_number(productsDataModels.get(position).getSerial_number());
        productsDataModel.setCounter(productsDataModels.get(position).getCounter());
        productsDataModel.setAvailable_storages(productsDataModels.get(position).getAvailable_storages());
        productsDataModel.setAvailable_colors(productsDataModels.get(position).getAvailable_colors());

        String myJson = gson.toJson(productsDataModel);
        intent.putExtra("myjson", myJson);
        test =1;

        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (test==1){
            viewModel = new HomeFragmentViewModel(HomeFragment.this);
            binding.setHomeVM(viewModel);
        }
    }
}