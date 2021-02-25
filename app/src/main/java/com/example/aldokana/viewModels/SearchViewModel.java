package com.example.aldokana.viewModels;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

import com.example.aldokana.Activities.SearchActivity;
import com.example.aldokana.Model.AddToBasketRequest;
import com.example.aldokana.Model.BasketItemsResponse;
import com.example.aldokana.Model.EditProfileResponse;
import com.example.aldokana.Model.ProductsResponse;
import com.example.aldokana.Model.SearchRequest;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SearchViewModel extends ViewModel {
    SearchActivity activity;
    public ObservableBoolean isData = new ObservableBoolean(false);
    AlertDialog alertDialog;
    String Token;
    Preferences preferences;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    BasketItemsResponse basketItemsResponse2;
    ProductsResponse productsResponse2;
    String sss;
    int position_plus,position_minus;

    public SearchViewModel(SearchActivity activity) {
        this.activity = activity;
        Token = preferences.GetToken(activity);
        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity,R.style.SheetDialog);
        alertDialog = builder.create();
        isData.set(false);

    }

    public void Search(String search) {
        if (alertDialog.isShowing()){}else {
            showLoadingDialog(activity);
        }
        SearchRequest searchRequest = new SearchRequest(search);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .Search(searchRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseHome, this::handleErrorHome));
    }

    private void handleResponseHome(ProductsResponse productsResponse) {
        alertDialog.dismiss();
        productsResponse2=productsResponse;
        if (Token.equals("visitor")){
            if (productsResponse.getData().getProducts().size()!=0){
                isData.set(true);
                activity.initRecycler(productsResponse);
            }
        }else {

            if (productsResponse.getData().getProducts().size()!=0){
                isData.set(true);
                activity.initRecycler2(productsResponse,basketItemsResponse2);
            }
        }
    }

    private void handleErrorHome(Throwable throwable) {
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

    public void GetBasketItems(String search) {
        sss = search;
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
        //alertDialog.dismiss();
        double money = 0.0;
        Search(sss);
    }

    private void handleErrorBasketItems(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
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
        activity.PlusDone(position_plus);
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
        activity.MinusDone(position_minus);
    }

    private void handleErrorMinusBasket(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

}
