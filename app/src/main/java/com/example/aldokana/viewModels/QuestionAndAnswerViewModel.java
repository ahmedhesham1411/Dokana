package com.example.aldokana.viewModels;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;

import com.example.aldokana.Activities.QuestionAndAnswerActivity;
import com.example.aldokana.Model.EditProfileResponse;
import com.example.aldokana.Model.QuestionAndAnswerResponse;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class QuestionAndAnswerViewModel extends ViewModel {
    QuestionAndAnswerActivity activity;
    AlertDialog alertDialog;
    String Token;
    Preferences preferences;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    public QuestionAndAnswerViewModel(QuestionAndAnswerActivity activity) {
        this.activity = activity;

        Token = preferences.GetToken(activity);
        GetQuestionsAndAnswers();
    }

    private void GetQuestionsAndAnswers() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .GetQuestionsAndAnswers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseDeleteAddress, this::handleErrorDeleteAddress));
    }

    private void handleResponseDeleteAddress(QuestionAndAnswerResponse questionAndAnswerResponse) {
        alertDialog.dismiss();
        activity.initQuestionsRecycler(questionAndAnswerResponse);
    }

    private void handleErrorDeleteAddress(Throwable throwable) {
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
