package com.example.aldokana.viewModels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.aldokana.Activities.EditProfileActivity;
import com.example.aldokana.Activities.MainPageActivity;
import com.example.aldokana.Model.EditProfileRequest;
import com.example.aldokana.Model.EditProfileResponse;
import com.example.aldokana.Model.LoginRequest;
import com.example.aldokana.Model.RegisterationResponse;
import com.example.aldokana.Model.User;
import com.example.aldokana.MyTextViewCairo;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class EditProfileViewModel extends ViewModel {
    EditProfileActivity activity;
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> phone = new ObservableField<>("");
    public ObservableField<String> email = new ObservableField<>("");
    Preferences preferences;
    AlertDialog alertDialog,alertDialog2;
    String Token;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    public EditProfileViewModel(EditProfileActivity activity) {
        this.activity = activity;

        Token = preferences.GetToken(activity);
        name.set(preferences.getName(activity));
        phone.set(preferences.getPhone(activity));
        email.set(preferences.GetEmail(activity));
        ProfileData();

    }

    public void Validate(String name,String phone,String email,MultipartBody.Part body) {
        if (name.equals("") ||
                phone.equals("") ||
                email.equals("") ){
            Constant.showErrorDialog(activity.getString(R.string.allrequired),activity);
        }
        else {
            EditProfile(name,
                    phone,
                    email,
                    body);
        }
    }

    private void EditProfile(String name, String phone, String email, MultipartBody.Part photo) {
        RequestBody namee = RequestBody.create(
                MediaType.parse("text/plain"),
                name);
        RequestBody phonee = RequestBody.create(
                MediaType.parse("text/plain"),
                phone);
        RequestBody emaill = RequestBody.create(
                MediaType.parse("text/plain"),
                email);
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .updateProfile(namee,phonee,emaill,photo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(EditProfileResponse editProfileResponse) {
        alertDialog.dismiss();
        showEditDialog2();
    }

    private void handleError(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
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

    private void showEditDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.custom_alert_edit_profile, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.ErrorDialog);
        builder.setView(view);

        alertDialog2 = builder.create();
        alertDialog2.show();
        alertDialog2.setCancelable(false);
        MyTextViewCairo home = alertDialog2.findViewById(R.id.home_txt);
        MyTextViewCairo profile = alertDialog2.findViewById(R.id.profile_txt2);
        AppCompatImageView close = alertDialog2.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog2.dismiss();
            }
        });

        home.setOnClickListener(v -> {
            Intent intent = new Intent(activity, MainPageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("key","1");
            activity.startActivity(intent);
            alertDialog2.dismiss();
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(activity, MainPageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("key","4");
            activity.startActivity(intent);
            alertDialog2.dismiss();
        });

    }

    public void showEditDialog2() {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.custom_alert_edit_profile, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.ErrorDialog);
        builder.setView(view);

        alertDialog2 = builder.create();
        alertDialog2.show();
        alertDialog2.setCancelable(false);
        MyTextViewCairo home = alertDialog2.findViewById(R.id.home_txt);
        MyTextViewCairo profile = alertDialog2.findViewById(R.id.profile_txt2);
        AppCompatImageView close = alertDialog2.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog2.dismiss();
            }
        });

        home.setOnClickListener(v -> {
            Intent intent = new Intent(activity, MainPageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("key","1");
            activity.startActivity(intent);
            alertDialog2.dismiss();
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(activity, MainPageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("key","4");
            activity.startActivity(intent);
            alertDialog2.dismiss();
        });

    }

    public void ProfileData() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse1, this::handleError1));
    }

    private void handleResponse1(User user) {
        alertDialog.dismiss();
        Log.d("my name",user.getUser().getName());

        try {
            preferences.saveId(activity,String.valueOf(user.getUser().getId()));
            preferences.saveName(activity,user.getUser().getName());
            preferences.saveEmail(activity,user.getUser().getEmail());
            preferences.savePhone(activity,user.getUser().getPhone());
            preferences.savePhoto(activity,user.getUser().getPhoto());
            preferences.saveStatus(activity,String.valueOf(user.getUser().getStatus()));
        }catch (Exception e){}
        try {
            Picasso.get().load(user.getUser().getPhoto()).placeholder(R.drawable.profile).into(activity.binding.profileImageeee);
        }catch (Exception e){}
    }

    private void handleError1(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrorsProfile(throwable,activity);
    }


}
