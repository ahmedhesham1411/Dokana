package com.example.aldokana.viewModels;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.example.aldokana.Fragments.OrdersFragment;
import com.example.aldokana.Model.BasketItemsResponse;
import com.example.aldokana.Model.EditProfileResponse;
import com.example.aldokana.Model.NotificationResponse;
import com.example.aldokana.Model.OrdersResponse;
import com.example.aldokana.MyTextViewCairo;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class OrdersFragmentViewModel extends ViewModel {
    OrdersFragment fragment;
    Activity activity;
    AlertDialog alertDialog,alertDialog1;
    String Token;
    Preferences preferences;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    public ObservableField<String> notificationsNum = new ObservableField<>("");
    public ObservableField<String> basket_counter = new ObservableField<>("");
    public ObservableField<String> basket_money = new ObservableField<>("");
    OrdersResponse ordersResponse2;
    NotificationResponse notificationResponse2;

    public OrdersFragmentViewModel(OrdersFragment fragment) {
        this.fragment = fragment;
        activity=fragment.getActivity();

        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity,R.style.SheetDialog);
        alertDialog = builder.create();
        Token = preferences.GetToken(activity);
        GetMyOrders();
    }

    private void GetNotifications() {
        if (alertDialog.isShowing()){}else {
            showLoadingDialog(activity);
        }
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetNotification()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(NotificationResponse notificationResponse) {
        notificationResponse2=notificationResponse;
        GetBasketItems();
    }

    private void handleError(Throwable throwable) {
        Constant.handleErrors2(throwable,activity);
    }

    private void GetMyOrders() {
        if (alertDialog.isShowing()){}else {
            showLoadingDialog(activity);
        }
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetOrders()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseOrders, this::handleErrorOrders));
    }

    private void handleResponseOrders(OrdersResponse ordersResponse) {
        //alertDialog.dismiss();
        ordersResponse2=ordersResponse;
        GetNotifications();

    }

    private void handleErrorOrders(Throwable throwable) {
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
        alertDialog.dismiss();
        fragment.initRecycler(ordersResponse2);
        notificationsNum.set(String.valueOf(notificationResponse2.getData().getNotifications().size()));
        double money = 0.0;
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


    public void showDeleteDialog(int id) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.custom_alert_delete_item_orders, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.ErrorDialog);
        builder.setView(view);

        alertDialog1 = builder.create();
        alertDialog1.show();
        alertDialog1.setCancelable(false);
        MyTextViewCairo yes = alertDialog1.findViewById(R.id.delete);
        MyTextViewCairo no = alertDialog1.findViewById(R.id.cancel);
        AppCompatImageView close = alertDialog1.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog1.dismiss();
            }
        });

        yes.setOnClickListener(v -> {
            DeleteOrder(id);
        });

        no.setOnClickListener(v -> { ;
            alertDialog1.dismiss();
        });

    }

    public void DeleteOrder(int id) {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .CancelOrder(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseDeleteOrder, this::handleErrorDeleteOrder));
    }

    private void handleResponseDeleteOrder(EditProfileResponse editProfileResponse) {
        //alertDialog.dismiss();
        alertDialog1.dismiss();
        GetMyOrders();
    }

    private void handleErrorDeleteOrder(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

}
