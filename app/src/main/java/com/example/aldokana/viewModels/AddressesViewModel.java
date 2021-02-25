package com.example.aldokana.viewModels;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.ViewModel;

import com.example.aldokana.Activities.AddressesActivity;
import com.example.aldokana.Model.AddAddressRequestModel;
import com.example.aldokana.Model.EditProfileResponse;
import com.example.aldokana.Model.UserAddressesResponse;
import com.example.aldokana.MyButtonBold;
import com.example.aldokana.MyTextViewCairo;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class AddressesViewModel extends ViewModel {
    AddressesActivity activity;
    AlertDialog alertDialog,alertDialog1,alertDialog2;
    String Token;
    Preferences preferences;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    public AddressesViewModel(AddressesActivity activity) {
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

    public void showDeleteDialog(Context activity,int id) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.custom_alert_delete_item_address, null, false);
        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog1 = builder.create();
        alertDialog1.show();
        alertDialog1.setCancelable(false);
        MyTextViewCairo delete,cancel;
        delete = alertDialog1.findViewById(R.id.delete);
        cancel = alertDialog1.findViewById(R.id.cancel);
        AppCompatImageView close;
        close = alertDialog1.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog1.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog1.dismiss();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteAddresses(id);
                alertDialog1.dismiss();
            }
        });
    }

    private void DeleteAddresses(int id) {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .DeleteAddress(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseDeleteAddress, this::handleErrorDeleteAddress));
    }

    private void handleResponseDeleteAddress(EditProfileResponse editProfileResponse) {
        alertDialog.dismiss();
        showCodeDialog(activity.getString(R.string.deleteaddresssuccess),activity);
    }

    private void handleErrorDeleteAddress(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void showCodeDialog(String code, Context context) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_alert_code, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog2 = builder.create();
        alertDialog2.setCancelable(false);
        alertDialog2.show();
        MyTextViewCairo error = view.findViewById(R.id.error_message);
        error.setText(code);
        MyButtonBold ok;
        ok = view.findViewById(R.id.btn_error_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog2.dismiss();
                GetAddresses();
            }
        });
    }

}
