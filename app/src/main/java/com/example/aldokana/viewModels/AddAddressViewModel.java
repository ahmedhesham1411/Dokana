package com.example.aldokana.viewModels;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.aldokana.Activities.AddAddressActivity;
import com.example.aldokana.Model.AddAddressRequestModel;
import com.example.aldokana.Model.AllRegionsMasterModel;
import com.example.aldokana.Model.EditProfileResponse;
import com.example.aldokana.MyButtonBold;
import com.example.aldokana.MyTextViewCairo;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class AddAddressViewModel extends ViewModel {
    AddAddressActivity activity;
    public ObservableField<String> firstName = new ObservableField<>("");
    public ObservableField<String> lastName = new ObservableField<>("");
    public ObservableField<String> street = new ObservableField<>("");
    public ObservableField<String> tower = new ObservableField<>("");
    public ObservableField<String> phone = new ObservableField<>("");
    public int areaId = 0;
    public int cityId = 0;
    public ObservableField<String> AreaName = new ObservableField<>("");
    public ObservableField<String> cityName = new ObservableField<>("");
    public List<String> cityList = new ArrayList<>();
    public List<Integer> cityIdList = new ArrayList<>();
    public List<String> areaList = new ArrayList<>();
    public List<Integer> areaIdList = new ArrayList<>();
    AlertDialog alertDialog,alertDialog1;
    String Token;
    Preferences preferences;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    AllRegionsMasterModel allRegionsMasterModel2;


    public AddAddressViewModel(AddAddressActivity activity) {
        this.activity = activity;

        areaList.add(activity.getResources().getString(R.string.area));
        areaIdList.add(0);
        cityList.add(activity.getResources().getString(R.string.city));
        cityIdList.add(0);
        Token = preferences.GetToken(activity);

        GetRegions();

    }

    private void GetRegions() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetRegions()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(AllRegionsMasterModel allRegionsMasterModel) {
        alertDialog.dismiss();
        allRegionsMasterModel2 = allRegionsMasterModel;
        for (int i = 0; i < allRegionsMasterModel.getData().getRegions().size();i++) {
            areaList.add(allRegionsMasterModel.getData().getRegions().get(i).getName());
            areaIdList.add(allRegionsMasterModel.getData().getRegions().get(i).getId());
        }

    }

    private void handleError(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void getCities(int areaId){

        cityList.clear();
        cityIdList.clear();

        cityList.add(activity.getResources().getString(R.string.city));
        cityIdList.add(0);


        for (int i = 0; i < allRegionsMasterModel2.getData().getRegions().size();i++) {
            if (allRegionsMasterModel2.getData().getRegions().get(i).getId()==areaId){
                for (int j = 0; j < allRegionsMasterModel2.getData().getRegions().get(i).getCities().size();j++){
                    cityList.add(allRegionsMasterModel2.getData().getRegions().get(i).getCities().get(j).getName());
                    cityIdList.add(allRegionsMasterModel2.getData().getRegions().get(i).getCities().get(j).getId());
                }
            }
        }
        activity.initCities();

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
        if (firstName.get().isEmpty() ||
        lastName.get().isEmpty() ||
        street.get().isEmpty() ||
        tower.get().isEmpty() ||
        phone.get().isEmpty() ||
        areaId == 0 ||
        cityId == 0){
            Constant.showErrorDialog(activity.getString(R.string.allrequired),activity);
        }else if ( phone.get().length()!=11){
            Constant.showErrorDialog(activity.getString(R.string.incorrectphone),activity);
        }else {
            Constant.hideKeyboard(activity);
            AddAddress(firstName.get(),lastName.get(),areaId,cityId,street.get(),tower.get(),phone.get());
        }
    }


    private void AddAddress(String firstName,String lastName,int areaIdd,int cityIdd,String street,String tower,String phone) {
        showLoadingDialog(activity);
        AddAddressRequestModel addAddressRequestModel = new AddAddressRequestModel(firstName,lastName,street + " "+tower,"0.0","0.0",String.valueOf(areaId),"11865",phone,String.valueOf(cityId));
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .AddAddress(addAddressRequestModel)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseAddAddress, this::handleErrorAddAddress));
    }

    private void handleResponseAddAddress(EditProfileResponse editProfileResponse) {
        alertDialog.dismiss();
        showCodeDialog(activity.getString(R.string.addaddresssuccess),activity);

    }

    private void handleErrorAddAddress(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
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
