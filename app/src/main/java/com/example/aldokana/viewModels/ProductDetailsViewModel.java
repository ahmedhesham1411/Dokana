package com.example.aldokana.viewModels;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.aldokana.Activities.ProductDetailsActivity;
import com.example.aldokana.Activities.QuestionAndAnswerActivity;
import com.example.aldokana.Adapter.QuestionsAdapter;
import com.example.aldokana.Adapter.SpecifcationsAdapter;
import com.example.aldokana.Model.AddToBasketRequest;
import com.example.aldokana.Model.BasketItemsResponse;
import com.example.aldokana.Model.EditProfileResponse;
import com.example.aldokana.Model.ProductsDataModel;
import com.example.aldokana.Model.ProductsResponse;
import com.example.aldokana.Model.RateRequest;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ProductDetailsViewModel extends ViewModel {
    ProductDetailsActivity activity;
    public ObservableField<String> counter = new ObservableField<>("0");
    ProductsDataModel productsDataModel;
    public ObservableField<String> price = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> disc = new ObservableField<>("");
    public ObservableField<String> brand = new ObservableField<>("");
    public ObservableField<String> basket_money = new ObservableField<>("0");
    SpecifcationsAdapter specifcationsAdapter;
    public ObservableBoolean isSpecifications = new ObservableBoolean(false);
    public ObservableBoolean isSimilar = new ObservableBoolean(false);
    AlertDialog alertDialog,alertDialog2;
    String Token;
    Preferences preferences;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    double money ;
    int counterrr;
    BasketItemsResponse basketItemsResponse2;

    public ProductDetailsViewModel(ProductDetailsActivity activity, ProductsDataModel productsDataModel) {
        this.activity = activity;
        this.productsDataModel = productsDataModel;
        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity,R.style.SheetDialog);
        alertDialog = builder.create();
        Token = preferences.GetToken(activity);


        name.set(productsDataModel.getName());
        brand.set(productsDataModel.getCompany());
        price.set(String.valueOf(productsDataModel.getPrice()));
        activity.binding.rating.setRating((float) productsDataModel.getRate());

        try {
            disc.set(productsDataModel.getDescription().get(0).getMain_description());
        }catch (Exception e){}
        if (productsDataModel.getSpecifications().size()!=0){
            isSpecifications.set(true);
            specifcationsAdapter = new SpecifcationsAdapter(activity,productsDataModel.getSpecifications());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
            activity.binding.specificationsRecycler.setLayoutManager(linearLayoutManager);
            activity.binding.specificationsRecycler.setHasFixedSize(false);
            activity.binding.specificationsRecycler.setAdapter(specifcationsAdapter);
        }else {isSpecifications.set(false);}
        GetBasketItems();


    }


    public void GetSimilar(String serial) {
/*        if (alertDialog.isShowing()){}else {
            showLoadingDialog(activity);
        }*/
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .GetSimilar(serial)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseHome, this::handleErrorHome));
    }

    private void handleResponseHome(ProductsResponse productsResponse) {
        alertDialog.dismiss();
        if (productsResponse.getData().getProducts().size()!=0){
            isSimilar.set(true);
            activity.initRecycler(productsResponse,basketItemsResponse2);
        }else {
            isSimilar.set(false);
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


    public void AddToBasket(int id,int counter1) {
        showLoadingDialog(activity);
        AddToBasketRequest addToBasketRequest = new AddToBasketRequest(id,1);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .AddToBasket(addToBasketRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseAddToBasket, this::handleErrorAddToBasket));
    }

    private void handleResponseAddToBasket(EditProfileResponse editProfileResponse) {
        //alertDialog.dismiss();
        counterrr=Integer.parseInt(counter.get());
        counterrr=counterrr+1;
        counter.set(String.valueOf(counterrr));
        //counter.set(String.valueOf(Integer.parseInt(String.valueOf(counter)) + 1));
        GetBasketItems();
    }

    private void handleErrorAddToBasket(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void MinusBasket(int id,int counter2) {
        if (counter.get().equals("0")){}else {
            showLoadingDialog(activity);
            AddToBasketRequest addToBasketRequest = new AddToBasketRequest(id,-1);
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                    .AddToBasket(addToBasketRequest)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseMinusBasket, this::handleErrorMinusBasket));
        }

    }

    private void handleResponseMinusBasket(EditProfileResponse editProfileResponse) {
        //alertDialog.dismiss();
        if (counter.get().equals("0")){}else {
            counterrr=Integer.parseInt(counter.get());
            counterrr=counterrr-1;
            counter.set(String.valueOf(counterrr));
        }
        GetBasketItems();
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
        //alertDialog.dismiss();
        basketItemsResponse2=basketItemsResponse;
        money = 0;

        for (int i=0;i<basketItemsResponse.getData().getBasket_items().size();i++){
            basket_money.set("");
            money+=Double.parseDouble(basketItemsResponse.getData().getBasket_items().get(i).getTotal_cost());
        }
        basket_money.set(String.valueOf(money));

        for (int i=0;i<basketItemsResponse.getData().getBasket_items().size();i++){
            if (basketItemsResponse.getData().getBasket_items().get(i).getProduct_id()==productsDataModel.getId()){
                int c = Integer.parseInt(basketItemsResponse.getData().getBasket_items().get(i).getCount());
                productsDataModel.setCounter(c);
                counter.set(String.valueOf(c));
            }
        }

        GetSimilar(productsDataModel.getSerial_number());

    }

    private void handleErrorBasketItems(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void Rate(int id,float rate,AlertDialog alertDialog5) {
        alertDialog2=alertDialog5;
        showLoadingDialog(activity);
        RateRequest rateRequest = new RateRequest(id,rate);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .Rate(rateRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseRate, this::handleErrorRate));
    }

    private void handleResponseRate(EditProfileResponse editProfileResponse) {
        alertDialog2.dismiss();
        alertDialog.dismiss();
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.my_toast,
                (ViewGroup)activity.findViewById(R.id.relativeLayout1));

        Toast toast = new Toast(activity);
        toast.setView(view);
        toast.show();
    }

    private void handleErrorRate(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

}
