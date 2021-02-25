package com.example.aldokana.viewModels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.example.aldokana.Activities.Login;
import com.example.aldokana.Activities.MainPageActivity;
import com.example.aldokana.Fragments.ProfileFragment;
import com.example.aldokana.Model.NotificationResponse;
import com.example.aldokana.Model.User;
import com.example.aldokana.MyButtonBold;
import com.example.aldokana.MyTextViewCairo;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;
import com.squareup.picasso.Picasso;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ProfileFragmentViewModel extends ViewModel {
    ProfileFragment activity;
    public ObservableField<String> name = new ObservableField<>("");
    Preferences preferences;
    String imageUrl;
    String Token;
    AlertDialog alertDialog1;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    public ObservableField<String> notificationsNum = new ObservableField<>("");

    public ProfileFragmentViewModel(ProfileFragment activity) {
        this.activity = activity;
        Token = preferences.GetToken(activity.getActivity());

        if (Token.equals("visitor")){
            name.set("Guest");
            notificationsNum.set("0");
            activity.binding.profileImage.setImageResource(R.drawable.profile);
        }else {
            name.set(preferences.getName(activity.getActivity()));
            try {
                imageUrl = preferences.getPhoto(activity.getActivity());
                Picasso.get().load(imageUrl).placeholder(R.drawable.profile).into(activity.binding.profileImage);
            }catch (Exception e){
            }
            ProfileData(Token);
            GetNotifications();
        }

    }

    private void ProfileData(String token) {
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(token)
                .GetProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(User user) {
        Log.d("my name",user.getUser().getName());

        try {
            preferences.saveId(activity.getActivity(),String.valueOf(user.getUser().getId()));
            preferences.saveName(activity.getActivity(),user.getUser().getName());
            preferences.saveEmail(activity.getActivity(),user.getUser().getEmail());
            preferences.savePhone(activity.getActivity(),user.getUser().getPhone());
            preferences.savePhoto(activity.getActivity(),user.getUser().getPhoto());
            preferences.saveStatus(activity.getActivity(),String.valueOf(user.getUser().getStatus()));
        }catch (Exception e){}
        Picasso.get().load(user.getUser().getPhoto()).placeholder(R.drawable.profile).into(activity.binding.profileImage);

    }

    private void handleError(Throwable throwable) {
        //Constant.handleErrorsProfile(throwable,activity.getActivity());
        showErrorDialog(activity.getString(R.string.loginagain),activity.getActivity());
    }

    public void showErrorDialog(String errorMsg, Context context) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_alert_error, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog1 = builder.create();
        alertDialog1.setCancelable(false);
        alertDialog1.show();
        MyTextViewCairo error = view.findViewById(R.id.error_message);
        error.setText(errorMsg);
        MyButtonBold ok;
        ok = view.findViewById(R.id.btn_error_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
                preferences.Clear(activity.getActivity());
                Intent intent = new Intent(activity.getActivity(), Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity.getActivity().startActivity(intent);
            }
        });
    }

    private void GetNotifications() {

        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetNotification()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleErrora));
    }

    private void handleResponse(NotificationResponse notificationResponse) {
        notificationsNum.set(String.valueOf(notificationResponse.getData().getNotifications().size()));

    }

    private void handleErrora(Throwable throwable) {
        Constant.handleErrors2(throwable,activity.getActivity());
    }

}
