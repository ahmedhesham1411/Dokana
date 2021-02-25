package com.example.aldokana.viewModels;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.example.aldokana.Fragments.HomeFragment;
import com.example.aldokana.Model.AboutAppResponse;
import com.example.aldokana.Model.AddToBasketRequest;
import com.example.aldokana.Model.BasketItemsResponse;
import com.example.aldokana.Model.CategoriesResponse;
import com.example.aldokana.Model.EditProfileResponse;
import com.example.aldokana.Model.NotificationResponse;
import com.example.aldokana.Model.ProductsResponse;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class HomeFragmentViewModel extends ViewModel {
    HomeFragment fragment;
    Activity activity;
    AlertDialog alertDialog;
    String Token;
    Preferences preferences;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    public ObservableField<String> notificationsNum = new ObservableField<>("0");
    int position_plus,position_minus;
    public ObservableField<String> basket_counter = new ObservableField<>("0");
    public ObservableField<String> basket_money = new ObservableField<>("0");
    CategoriesResponse categoriesResponse2;
    ProductsResponse productsResponse2;
    BasketItemsResponse basketItemsResponse2 ;
    int idii,posii;

    public HomeFragmentViewModel(HomeFragment fragment) {
        this.fragment = fragment;
        activity=fragment.getActivity();

        Token = preferences.GetToken(activity);
        if (Token.equals("visitor")){}else {
            GetNotifications();
        }
        GetCategories();
    }

    private void GetNotifications() {
        //showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetNotification()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(NotificationResponse notificationResponse) {
        notificationsNum.set(String.valueOf(notificationResponse.getData().getNotifications().size()));
    }

    private void handleError(Throwable throwable) {
        Constant.handleErrors2(throwable,activity);
    }

    private void GetCategories() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .GetCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseCat, this::handleErrorCat));
    }

    private void handleResponseCat(CategoriesResponse categoriesResponse) {
        //alertDialog.dismiss();
        categoriesResponse2=categoriesResponse;
        fragment.initMenuRecycler(categoriesResponse2);

        try {
            GetHome();
        }catch (Exception e){
            alertDialog.dismiss();
        }
    }

    private void handleErrorCat(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void GetHome() {
        if (alertDialog.isShowing()){}else {
            showLoadingDialog(activity);
        }
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .GetHome()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseHome, this::handleErrorHome));
    }

    private void handleResponseHome(ProductsResponse productsResponse) {
        //alertDialog.dismiss();
        productsResponse2=productsResponse;
        if (Token.equals("visitor")){
            alertDialog.dismiss();
            fragment.initMenuRecycler(categoriesResponse2);
            fragment.initRecycler2(productsResponse2);
        }else{
            GetBasketItems();
        }
    }

    private void handleErrorHome(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void GetFromCat(int id) {
        if (alertDialog.isShowing()){}else {
            showLoadingDialog(activity);
        }
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetFromCat(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseFromCat, this::handleErrorFromCat));
    }

    private void handleResponseFromCat(ProductsResponse productsResponse) {
        alertDialog.dismiss();
        if (Token.equals("visitor")){
            fragment.initRecycler2(productsResponse);
        }else
        fragment.initRecycler(productsResponse,basketItemsResponse2);
    }

    private void handleErrorFromCat(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }


    public void showLoadingDialog(Context activity) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.loading, null, false);
        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity,R.style.SheetDialog);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
    }

    public void AddToBasket(int position,int id,int counter) {
        position_plus = position;
        showLoadingDialog(activity);
        //Toast.makeText(activity, String.valueOf(id + " + "+counter), Toast.LENGTH_SHORT).show();
        AddToBasketRequest addToBasketRequest = new AddToBasketRequest(id,counter);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .AddToBasket(addToBasketRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseAddToBasket, this::handleErrorAddToBasket));
    }

    private void handleResponseAddToBasket(EditProfileResponse editProfileResponse) {
        //alertDialog.dismiss();
        fragment.PlusDone(position_plus);
    }

    private void handleErrorAddToBasket(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void MinusBasket(int position,int id,int counter) {
        position_minus = position;
        showLoadingDialog(activity);
        AddToBasketRequest addToBasketRequest = new AddToBasketRequest(id,counter);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .AddToBasket(addToBasketRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseMinusBasket, this::handleErrorMinusBasket));
    }

    private void handleResponseMinusBasket(EditProfileResponse editProfileResponse) {
        //alertDialog.dismiss();
        fragment.MinusDone(position_minus);
    }

    private void handleErrorMinusBasket(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void GetBasketItems() {
        if (alertDialog.isShowing()){}else {
            showLoadingDialog(activity);
        }
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetUserBasket()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseBasketItems, this::handleErrorBasketItems));
    }

    private void handleResponseBasketItems(BasketItemsResponse basketItemsResponse) {
        basketItemsResponse2=basketItemsResponse;
        alertDialog.dismiss();
        double money = 0.0;
        fragment.initRecycler(productsResponse2,basketItemsResponse);
        basket_counter.set(String.valueOf(basketItemsResponse.getData().getBasket_items().size()));
        for (int i=0;i<basketItemsResponse.getData().getBasket_items().size();i++){
            money+=Double.parseDouble(basketItemsResponse.getData().getBasket_items().get(i).getTotal_cost());
        }
        basket_money.set(String.valueOf(money));
    }

    private void handleErrorBasketItems(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void RefreshBasketItems() {
        if (alertDialog.isShowing()){}else {
            showLoadingDialog(activity);
        }
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetUserBasket()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseRefresh, this::handleErrorRefresh));
    }

    private void handleResponseRefresh(BasketItemsResponse basketItemsResponse) {
        basketItemsResponse2=basketItemsResponse;
        alertDialog.dismiss();
        double money = 0.0;
        basket_counter.set(String.valueOf(basketItemsResponse.getData().getBasket_items().size()));
        for (int i=0;i<basketItemsResponse.getData().getBasket_items().size();i++){
            money+=Double.parseDouble(basketItemsResponse.getData().getBasket_items().get(i).getTotal_cost());
        }
        basket_money.set(String.valueOf(money));
    }

    private void handleErrorRefresh(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void RefreshBasketItems2(int pos,int id) {
        posii=pos;
        idii=id;
        if (alertDialog.isShowing()){}else {
            showLoadingDialog(activity);
        }
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetUserBasket()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseRefresh2, this::handleErrorRefresh2));
    }

    private void handleResponseRefresh2(BasketItemsResponse basketItemsResponse) {
        //alertDialog.dismiss();
        basketItemsResponse2=basketItemsResponse;
        double money = 0.0;
        basket_counter.set(String.valueOf(basketItemsResponse.getData().getBasket_items().size()));
        for (int i=0;i<basketItemsResponse.getData().getBasket_items().size();i++){
            money+=Double.parseDouble(basketItemsResponse.getData().getBasket_items().get(i).getTotal_cost());
        }
        basket_money.set(String.valueOf(money));

        if (posii==0){
            GetHome();
        }else {
            GetFromCat(idii);
        }
    }

    private void handleErrorRefresh2(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }
}
