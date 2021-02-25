package com.example.aldokana.viewModels;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;

import com.example.aldokana.Activities.ChooseAddressActivity;
import com.example.aldokana.Model.UserAddressesResponse;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ChooseAddressViewModel extends ViewModel {
    ChooseAddressActivity activity;
    AlertDialog alertDialog,alertDialog1,alertDialog2;
    String Token;
    Preferences preferences;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();


    public ChooseAddressViewModel(ChooseAddressActivity activity) {
        this.activity = activity;

        Token = preferences.GetToken(activity);
        GetAddresses();

    }

    private void GetAddresses() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetUserAddresses()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseAddAddress, this::handleErrorAddAddress));
    }

    private void handleResponseAddAddress(UserAddressesResponse userAddressesResponse) {
        alertDialog.dismiss();
        activity.initRecycler(userAddressesResponse.getData());
    }

    private void handleErrorAddAddress(Throwable throwable) {
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

}
