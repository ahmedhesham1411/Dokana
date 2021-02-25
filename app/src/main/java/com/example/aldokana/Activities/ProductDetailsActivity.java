package com.example.aldokana.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.aldokana.Activities.BaseActivity;
import com.example.aldokana.Activities.LocaleManeger;
import com.example.aldokana.Adapter.ColorsAdapter;
import com.example.aldokana.Adapter.HomeAdapter;
import com.example.aldokana.Adapter.HomeAdapter2;
import com.example.aldokana.Adapter.Home_slider_adapter;
import com.example.aldokana.Adapter.MenuAdapter;
import com.example.aldokana.Adapter.ProductDetailsSliderAdapter;
import com.example.aldokana.Adapter.ProductDetailsStorageAdapter;
import com.example.aldokana.Adapter.SimilarAdapter;
import com.example.aldokana.Fragments.HomeFragment;
import com.example.aldokana.Interfaces.ColorClick;
import com.example.aldokana.Interfaces.HomeItemClick;
import com.example.aldokana.Interfaces.ProductStorageClick;
import com.example.aldokana.Model.BasketItemsResponse;
import com.example.aldokana.Model.ColorsModel;
import com.example.aldokana.Model.HomeItemModel;
import com.example.aldokana.Model.MenuModel;
import com.example.aldokana.Model.ProductDetailsStorageModel;
import com.example.aldokana.Model.ProductsDataModel;
import com.example.aldokana.Model.ProductsResponse;
import com.example.aldokana.MyTextViewCairo;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;
import com.example.aldokana.databinding.ActivityProductDetailsBinding;
import com.example.aldokana.viewModels.CartViewModel;
import com.example.aldokana.viewModels.ProductDetailsViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends BaseActivity implements ProductStorageClick, ColorClick, HomeItemClick {

    public ActivityProductDetailsBinding binding;
    List<String> imgs;
    List<ProductDetailsStorageModel> productDetailsStorageModels;
    ProductDetailsStorageAdapter productDetailsStorageAdapter;
    ColorsAdapter colorsAdapter;
    List<ColorsModel> colorsModels;
    List<HomeItemModel> homeItemModels;
    SimilarAdapter similarAdapter;
    int counter = 0;
    public ProductDetailsViewModel viewModel;
    AlertDialog alertDialog;
    float rate;
    HomeAdapter2 homeAdapter;
    List<ProductsDataModel> productsDataModels;
    ProductsDataModel productsDataModel;
    String Token;
    Preferences preferences;
    int test;
    List<ProductDetailsStorageModel> available_storage;
    List<ColorsModel> available_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details);

        Gson gson = new Gson();
        productsDataModel = gson.fromJson(getIntent().getStringExtra("myjson"), ProductsDataModel.class);

        viewModel = new ProductDetailsViewModel(this,productsDataModel);
        binding.setDetailsVM(viewModel);

        viewModel.counter.set(String.valueOf(productsDataModel.getCounter()));
        Token = preferences.GetToken(ProductDetailsActivity.this);
        available_storage = productsDataModel.getAvailable_storages();
        available_color = productsDataModel.getAvailable_colors();
        initStorages(available_storage);
        initColors(available_color);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        try {
            imgs=new ArrayList<>();
            for (int i=1;i<productsDataModel.getImages().size();i++){
                imgs.add(productsDataModel.getImages().get(i).getPhoto());
            }
        }catch (Exception e){}

        ProductDetailsSliderAdapter adapterr = new ProductDetailsSliderAdapter(ProductDetailsActivity.this,imgs);
        binding.imageSliderHome.setSliderAdapter(adapterr);

        binding.GeneralspecificationsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!binding.expandableGeneral.isExpanded()){
                    //TransitionManager.beginDelayedTransition(holder.binding.fullLayout);
                    binding.expandableGeneral.expand();
                    binding.plusimggeneral.setImageResource(R.drawable.minus);
                    binding.fullLayoutGeneral.setBackground(getDrawable(R.drawable.shapeeee));
                }else if (binding.expandableGeneral.isExpanded()){
                    binding.expandableGeneral.collapse(true);
                    //holder.binding.txtAnswer.setVisibility(View.GONE);
                    binding.plusimggeneral.setImageResource(R.drawable.plus);
                    final Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.fullLayoutGeneral.setBackground(getDrawable(R.drawable.shape_rounded_grey));
                        }
                    }, 1000);
                }

            }
        });

        binding.OtherspecificationsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!binding.otherExpandable.isExpanded()){
                    //TransitionManager.beginDelayedTransition(holder.binding.fullLayout);
                    binding.otherExpandable.expand();
                    binding.plusimgOther.setImageResource(R.drawable.minus);
                    binding.fullLayoutOther.setBackground(getDrawable(R.drawable.shapeeee));
                }else if (binding.otherExpandable.isExpanded()){
                    binding.otherExpandable.collapse(true);
                    //holder.binding.txtAnswer.setVisibility(View.GONE);
                    binding.plusimgOther.setImageResource(R.drawable.plus);
                    final Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.fullLayoutOther.setBackground(getDrawable(R.drawable.shape_rounded_grey));
                        }
                    }, 1000);
                }

            }
        });

        binding.informationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!binding.expandableInformations.isExpanded()){
                    //TransitionManager.beginDelayedTransition(holder.binding.fullLayout);
                    binding.expandableInformations.expand();
                    binding.plusimgInformation.setImageResource(R.drawable.minus);
                    binding.fullLayoutInfo.setBackground(getDrawable(R.drawable.shapeeee));
                }else if (binding.expandableInformations.isExpanded()){
                    binding.expandableInformations.collapse(true);
                    //holder.binding.txtAnswer.setVisibility(View.GONE);
                    binding.plusimgInformation.setImageResource(R.drawable.plus);
                    final Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.fullLayoutInfo.setBackground(getDrawable(R.drawable.shape_rounded_grey));
                        }
                    }, 1000);
                }

            }
        });

        binding.rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(),Float.toString(rating),Toast.LENGTH_LONG).show();

            }

        });


        binding.plusLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Token.equals("visitor")){
                    Constant.logInDialog("a",ProductDetailsActivity.this);
                }else
                viewModel.AddToBasket(productsDataModel.getId(),1);
            }
        });

        binding.minusLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Token.equals("visitor")){
                    Constant.logInDialog("a",ProductDetailsActivity.this);
                }else
                viewModel.MinusBasket(productsDataModel.getId(),-1);
            }
        });

/*        binding.plusLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int counter = Integer.parseInt(viewModel.counter.get());
                viewModel.counter.set(String.valueOf(counter+1));
            }
        });
        binding.minusLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int counter = Integer.parseInt(viewModel.counter.get());
                if (counter==0){}else
                viewModel.counter.set(String.valueOf(counter-1));
            }
        });*/
        binding.rateClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Token.equals("visitor")){
                    Constant.logInDialog("a",ProductDetailsActivity.this);
                }else
                showRateDialog();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void clickStorage(int position) {
        for (int i=0;i<available_storage.size();i++) {
            if (i == position) {
                available_storage.get(i).setSelected(true);
            } else {
                available_storage.get(i).setSelected(false);
            }
        }
        productDetailsStorageAdapter.notifyDataSetChanged();
    }

    @Override
    public void colorClick(int position) {
        for (int i=0;i<available_color.size();i++) {
            if (i == position) {
                available_color.get(i).setSelected(true);
            } else {
                available_color.get(i).setSelected(false);
            }
        }
        colorsAdapter.notifyDataSetChanged();
    }

    private void showRateDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.custom_alert_rate, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.ErrorDialog);
        builder.setView(view);

        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(true);
        MyTextViewCairo yes = alertDialog.findViewById(R.id.yes_txt3);
        MyTextViewCairo no = alertDialog.findViewById(R.id.no_txt3);
        AppCompatImageView close = alertDialog.findViewById(R.id.close);
        RatingBar ratingBar = alertDialog.findViewById(R.id.rating);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
             rate = rating;
            }

        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        yes.setOnClickListener(v -> {
            viewModel.Rate(productsDataModel.getId(),rate,alertDialog);
            //Toast.makeText(this, String.valueOf(rate), Toast.LENGTH_SHORT).show();
            //alertDialog.dismiss();
            //rate=5;
        });

        no.setOnClickListener(v -> { ;
            alertDialog.dismiss();
        });


    }

    public void initRecycler(ProductsResponse productsResponse,BasketItemsResponse basketItemsResponse){
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

        homeAdapter = new HomeAdapter2(this,productsResponse.getData().getProducts(),ProductDetailsActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.similarRecycler.setLayoutManager(linearLayoutManager);
        binding.similarRecycler.setHasFixedSize(true);
        binding.similarRecycler.setAdapter(homeAdapter);
    }

    @Override
    public void plus(int position) {

    }

    @Override
    public void minus(int position) {

    }

    @Override
    public void itemClick(int position) {
        test=1;
        Intent intent = new Intent(ProductDetailsActivity.this, ProductDetailsActivity.class);
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

        String myJson = gson.toJson(productsDataModel);
        intent.putExtra("myjson", myJson);

        startActivity(intent);
    }


    public void PlusDone(){
        viewModel.counter.set(String.valueOf(Integer.parseInt(String.valueOf(viewModel.counter)) + 1));
        viewModel.GetBasketItems();
    }

    public void MinusDone(){
        if (viewModel.counter.get().equals("0")){}else {
            viewModel.counter.set(String.valueOf(Integer.parseInt(String.valueOf(viewModel.counter)) - 1));
            viewModel.GetBasketItems();
        }
    }

    public void initStorages(List<ProductDetailsStorageModel> available_storages){
        for (int i=0;i<available_storages.size();i++){
            available_storages.get(i).setSelected(false);
        }
        productDetailsStorageAdapter = new ProductDetailsStorageAdapter(ProductDetailsActivity.this,productDetailsStorageModels,ProductDetailsActivity.this,available_storages);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        binding.storageRecycler.setLayoutManager(linearLayoutManager);
        binding.storageRecycler.setHasFixedSize(true);
        binding.storageRecycler.setAdapter(productDetailsStorageAdapter);
    }

    public void initColors(List<ColorsModel> available_colors){
        for (int i=0;i<available_colors.size();i++){
            available_colors.get(i).setSelected(false);
        }
        colorsAdapter = new ColorsAdapter(ProductDetailsActivity.this,colorsModels,ProductDetailsActivity.this,available_colors);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(ProductDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        binding.colorsRecycler.setLayoutManager(linearLayoutManager1);
        binding.colorsRecycler.setHasFixedSize(true);
        binding.colorsRecycler.setAdapter(colorsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (test==1){
            viewModel = new ProductDetailsViewModel(this,productsDataModel);
            binding.setDetailsVM(viewModel);
        }
    }
}