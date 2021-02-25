package com.example.aldokana.viewModels;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.aldokana.Activities.CallUsActivity;
import com.example.aldokana.Activities.CallUsRequest;
import com.example.aldokana.Model.EditProfileResponse;
import com.example.aldokana.Model.TermsResponse;
import com.example.aldokana.MyButtonBold;
import com.example.aldokana.MyTextViewCairo;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class CallUsViewModel extends ViewModel {
    CallUsActivity activity;
    AlertDialog alertDialog,alertDialog1;
    String Token;
    Preferences preferences;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> phone = new ObservableField<>("");
    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> cause = new ObservableField<>("");
    public ObservableField<String> details = new ObservableField<>("");


    public CallUsViewModel(CallUsActivity activity) {
        this.activity = activity;
        Token = preferences.GetToken(activity);


    }

    private void CallUs(String name,String phone,String email,String cause,String details) {
        showLoadingDialog(activity);
        CallUsRequest callUsRequest = new CallUsRequest(name,phone,cause,details);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .CallUs(callUsRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(EditProfileResponse editProfileResponse) {
        alertDialog.dismiss();
        showCodeDialog(activity.getString(R.string.callussuccess),activity);
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


    public void Validate() {
        if (name.get().isEmpty() ||
        phone.get().isEmpty() ||
        email.get().isEmpty() ||
        cause.get().isEmpty() ||
        details.get().isEmpty()){
            Constant.showErrorDialog(activity.getString(R.string.allrequired),activity);
        }else {
            Constant.hideKeyboard(activity);
            CallUs(name.get(),phone.get(),email.get(),cause.get(),details.get());
        }
    }

    public void showCodeDialog(String code, Context context) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_alert_code, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog1 = builder.create();
        alertDialog1.setCancelable(false);
        alertDialog1.show();
        MyTextViewCairo error = view.findViewById(R.id.error_message);
        error.setText(code);
        MyButtonBold ok;
        ok = view.findViewById(R.id.btn_error_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
                activity.finish();
            }
        });
    }
}
