package com.example.aldokana.viewModels;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.aldokana.Activities.MainPageActivity;
import com.example.aldokana.Activities.VerificationCode;
import com.example.aldokana.Model.CodeRequest;
import com.example.aldokana.Model.CodeResponse;
import com.example.aldokana.Model.LoginRequest;
import com.example.aldokana.Model.RegisterationResponse;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.example.aldokana.Utils.Constant.showLoadingDialog;

public class CodeViewModel extends ViewModel {
    VerificationCode activity;
    public ObservableField<String> num1 = new ObservableField<>("");
    public ObservableField<String> num2 = new ObservableField<>("");
    public ObservableField<String> num3 = new ObservableField<>("");
    public ObservableField<String> num4 = new ObservableField<>("");
    public ObservableField<String> num5 = new ObservableField<>("");
    public ObservableField<String> codee = new ObservableField<>("");

    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    public String code,entered_code;

    public CodeViewModel(VerificationCode activity) {
        this.activity = activity;

    }

    public void GetCode(String email) {
        Log.d("email :",email);
        Constant.showLoadingDialog(activity);
        CodeRequest codeRequest = new CodeRequest(email);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .SendCode(codeRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    public void handleResponse(CodeResponse codeResponse) {
        Constant.dismissDialog(activity);
        Constant.showCodeDialog("Code is : "+codeResponse.getData().getCode(),activity);
        code = String.valueOf(codeResponse.getData().getCode());
        Log.d("code :",String.valueOf(code));
        Log.d("code is:",String.valueOf(activity.code));
    }

    public void handleError(Throwable throwable) {
        Constant.dismissDialog(activity);
        Constant.handleErrors(throwable,activity);
    }

}
