package com.example.aldokana.viewModels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;

import com.example.aldokana.Activities.Login;
import com.example.aldokana.Activities.Register;
import com.example.aldokana.Activities.VerificationCode;
import com.example.aldokana.Model.RegisterationResponse;
import com.example.aldokana.Model.RegistrationRequest;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class RegisterViewModel extends ViewModel {
    Register activity;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    AlertDialog alertDialog;
    String email;
    Preferences preferences;

    public RegisterViewModel(Register activity) {
        this.activity = activity;

    }

    public void Validate(){
        Constant.hideKeyboard(activity);
        if (activity.binding.nameTxt.getText().toString().equals("") || 
                activity.binding.emailTxt.getText().toString().equals("")||
                activity.binding.phoneTxt.getText().toString().equals("")||
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
            String name = activity.binding.nameTxt.getText().toString();
            String phone = activity.binding.phoneTxt.getText().toString();
            email = activity.binding.emailTxt.getText().toString();
            String password = activity.binding.passwordTxt.getText().toString();
            CanRegister(name,phone,email,password);
        }
        
    }

    private void CanRegister(String name,String phone,String email,String password) {
        showLoadingDialog(activity);
        RegistrationRequest registrationRequest = new RegistrationRequest(name, phone,email,password);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                .Register(registrationRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(RegisterationResponse registerationResponse) {
        alertDialog.dismiss();
        preferences.saveEmail(activity,email);
        Intent intent = new Intent(activity, VerificationCode.class);
        activity.startActivity(intent);
    }

    private void handleError(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrorsaa(throwable,activity);
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
