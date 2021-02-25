package com.example.aldokana.viewModels;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;

import com.example.aldokana.Activities.Login;
import com.example.aldokana.Activities.MainPageActivity;
import com.example.aldokana.Model.LoginRequest;
import com.example.aldokana.Model.LoginResposne;
import com.example.aldokana.Model.RegisterationResponse;
import com.example.aldokana.Model.RegistrationRequest;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class LoginViewModel extends ViewModel {
    Login activity;
    AlertDialog alertDialog;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    Preferences preferences;
    public LoginViewModel(Login activity) {
        this.activity = activity;
    }

    public void Validate(){
        Constant.hideKeyboard(activity);

        if (activity.binding.phoneTxt.getText().toString().equals("") ||
                activity.binding.passwordTxt.getText().toString().equals("")){
            Constant.showErrorDialog(activity.getString(R.string.allrequired),activity);
        }
        else if (activity.binding.passwordTxt.getText().toString().length()<8){
            Constant.showErrorDialog(activity.getString(R.string.weakpass),activity);
        }
        else if (activity.binding.phoneTxt.getText().toString().length()!=11){
            Constant.showErrorDialog(activity.getString(R.string.incorrectphone),activity);
        }
        else {
            String phone = activity.binding.phoneTxt.getText().toString();
            String password = activity.binding.passwordTxt.getText().toString();
            CanLogin(phone,password);
        }

    }

    private void CanLogin(String phone, String password) {
        showLoadingDialog(activity);
        LoginRequest loginRequest = new LoginRequest(phone,password);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .Login(loginRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(LoginResposne loginResposne) {
        alertDialog.dismiss();
        String token = loginResposne.getData().getUser().getToken();
        String final_token = loginResposne.getData().getUser().getToken().replace("Bearer ","");

        preferences.saveToken(activity,final_token);
        Intent intent = new Intent(activity,MainPageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("key","1");
        activity.startActivity(intent);
    }

    private void handleError(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,activity);
    }

    public void showLoadingDialog(Context activity) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.loading, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.SheetDialog);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
    }

    public void dismissDialog(Context activity) {
        alertDialog.dismiss();
    }

}
