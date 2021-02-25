package com.example.aldokana.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.example.aldokana.Activities.BaseActivity;
import com.example.aldokana.Adapter.HomeAdapter;
import com.example.aldokana.Adapter.HomeAdapter2;
import com.example.aldokana.Fragments.HomeFragment;
import com.example.aldokana.Interfaces.HomeItemClick;
import com.example.aldokana.Model.BasketItemsResponse;
import com.example.aldokana.Model.ProductsDataModel;
import com.example.aldokana.Model.ProductsResponse;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;
import com.example.aldokana.Utils.Utilities;
import com.example.aldokana.databinding.ActivitySearchBinding;
import com.example.aldokana.viewModels.SearchViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements HomeItemClick {

    public ActivitySearchBinding binding;
    SearchViewModel viewModel;
    HomeAdapter2 homeAdapter;
    List<ProductsDataModel> productsDataModels;
    String Token;
    Preferences preferences;
    String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        viewModel = new SearchViewModel(this);
        binding.setSearchsVM(viewModel);

        Token = preferences.GetToken(getApplicationContext());
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.searchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilities.hideKeyboard(SearchActivity.this);
                binding.ssssssssss.setVisibility(View.GONE);
                binding.aaaaaaa.setVisibility(View.VISIBLE);
                binding.aaaaaaa22.setVisibility(View.VISIBLE);
                search = binding.searchEditTxt.getText().toString();
                if (Token.equals("visitor")){
                    viewModel.Search(search);
                }else {
                    viewModel.GetBasketItems(search);
                }
            }
        });

        binding.searchEditTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Utilities.hideKeyboard(SearchActivity.this);
                    binding.ssssssssss.setVisibility(View.GONE);
                    binding.aaaaaaa.setVisibility(View.VISIBLE);
                    binding.aaaaaaa22.setVisibility(View.VISIBLE);
                    search = binding.searchEditTxt.getText().toString();
                    if (Token.equals("visitor")){
                        viewModel.Search(search);
                    }else {
                        viewModel.GetBasketItems(search);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void initRecycler(ProductsResponse productsResponse){
        productsDataModels = productsResponse.getData().getProducts();
        homeAdapter = new HomeAdapter2(this,productsDataModels, SearchActivity.this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        binding.searchRecycler.setLayoutManager(layoutManager);
        binding.searchRecycler.setHasFixedSize(true);
        binding.searchRecycler.setAdapter(homeAdapter);
    }

    public void initRecycler2(ProductsResponse productsResponse,BasketItemsResponse basketItemsResponse){
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

        homeAdapter = new HomeAdapter2(this,productsDataModels, SearchActivity.this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        binding.searchRecycler.setLayoutManager(layoutManager);
        binding.searchRecycler.setHasFixedSize(true);
        binding.searchRecycler.setAdapter(homeAdapter);
    }

    @Override
    public void plus(int position) {
        if (Token.equals("visitor")){
            Constant.logInDialog("a",SearchActivity.this);
        }else {
            int counter = productsDataModels.get(position).getCounter();
            viewModel.AddToBasket(position, productsDataModels.get(position).getId(), 1);
        }
    }

    public void PlusDone(int position){
        int counter = productsDataModels.get(position).getCounter();
        productsDataModels.get(position).setCounter(counter+1);
        homeAdapter.notifyDataSetChanged();
        viewModel.GetBasketItems(search);
    }

    @Override
    public void minus(int position) {
        if (Token.equals("visitor")){
            Constant.logInDialog("a",SearchActivity.this);
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
        viewModel.GetBasketItems(search);
    }

    @Override
    public void itemClick(int position) {
        Intent intent = new Intent(SearchActivity.this, ProductDetailsActivity.class);
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

        String myJson = gson.toJson(productsDataModel);
        intent.putExtra("myjson", myJson);

        startActivity(intent);
    }
}