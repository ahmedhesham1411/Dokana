package com.example.aldokana.viewModels;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.aldokana.Activities.AboutAppActivity;
import com.example.aldokana.Model.AboutAppResponse;
import com.example.aldokana.Model.QuestionAndAnswerResponse;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class AboutAppViewModel extends ViewModel {
    AboutAppActivity activity;
    AlertDialog alertDialog;
    String Token;
    Preferences preferences;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();


    public AboutAppViewModel(AboutAppActivity activity) {
        this.activity = activity;

        Token = preferences.GetToken(activity);
        GetAboutApp();
    }
    private void GetAboutApp() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .GetAboutApp()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(AboutAppResponse aboutAppResponse) {
        alertDialog.dismiss();
        activity.initAbout(aboutAppResponse);
    }

    private void handleError(Throwable throwable) {
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
