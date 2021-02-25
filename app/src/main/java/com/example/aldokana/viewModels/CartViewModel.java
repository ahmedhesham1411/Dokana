package com.example.aldokana.viewModels;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.aldokana.Activities.CartActivity;
import com.example.aldokana.Model.AddToBasketRequest;
import com.example.aldokana.Model.BasketItemsResponse;
import com.example.aldokana.Model.BasketResponse;
import com.example.aldokana.Model.CouponRequest;
import com.example.aldokana.Model.CouponResponse;
import com.example.aldokana.Model.DeleteBasketItemRequest;
import com.example.aldokana.Model.EditProfileResponse;
import com.example.aldokana.Model.FeesRequest;
import com.example.aldokana.Model.FeesResponse;
import com.example.aldokana.Model.SaveOrderRequest;
import com.example.aldokana.MyButtonBold;
import com.example.aldokana.MyTextViewCairo;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;
import com.example.aldokana.Utils.Utilities;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class CartViewModel extends ViewModel {
    CartActivity activity;
    Preferences preferences;
    public ObservableField<String> productsCost = new ObservableField<>("0");
    public ObservableField<String> totalCostFinal = new ObservableField<>("0");
    public ObservableField<String> fees = new ObservableField<>("0");
    public ObservableField<String> discount = new ObservableField<>("0");
    public ObservableField<String> basketCounter = new ObservableField<>("");
    public ObservableField<String> addressTitle = new ObservableField<>("0");
    public ObservableField<String> addressid = new ObservableField<>("0");
    public ObservableField<String> addressname = new ObservableField<>("0");
    public ObservableField<String> addressphone = new ObservableField<>("0");
    public ObservableBoolean isVisa = new ObservableBoolean(true);
    public ObservableBoolean isCash = new ObservableBoolean(false);
    public ObservableBoolean isPay = new ObservableBoolean(false);
    public ObservableBoolean isVisa2 = new ObservableBoolean(false);
    AlertDialog alertDialog,alertDialog4,alertDialog1;
    String Token;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    public ObservableField<String> notificationsNum = new ObservableField<>("");
    public int addressId;
    CouponResponse couponResponse2;
    BasketResponse basketItemsResponse2;
    private String cardType = "STC_PAY";


    public CartViewModel(CartActivity activity) {
        this.activity = activity;

        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity,R.style.SheetDialog);
        alertDialog = builder.create();

        Token = preferences.GetToken(activity);
        addressTitle.set(preferences.GetAddressTitle(activity));
        addressname.set(preferences.GetAddressName(activity));
        addressphone.set(preferences.GetAddressPhone(activity));
        addressid.set(String.valueOf(preferences.GetAddressId(activity)));


        if (addressid.get().equals("-1")){
            activity.binding.linearAddress.setVisibility(View.GONE);
            activity.binding.chooseAddressTxt.setVisibility(View.VISIBLE);
            activity.binding.aa11.setVisibility(View.VISIBLE);
            activity.binding.aa2.setVisibility(View.GONE);
            activity.binding.aa22.setVisibility(View.GONE);
        }else {
            activity.binding.linearAddress.setVisibility(View.VISIBLE);
            activity.binding.chooseAddressTxt.setVisibility(View.GONE);
            activity.binding.aa11.setVisibility(View.GONE);
            activity.binding.aa2.setVisibility(View.VISIBLE);
            activity.binding.aa22.setVisibility(View.VISIBLE);
            addressId=preferences.GetAddressId(activity);
            activity.binding.notiTitle.setText(preferences.GetAddressTitle(activity));
            activity.binding.addressName.setText(preferences.GetAddressName(activity));
            activity.binding.addressMobile.setText(preferences.GetAddressPhone(activity));
            //GetFees();
        }
        GetBasketItems();

        double totalCost = 0.0;
        totalCost = Double.parseDouble(productsCost.get()) + Integer.parseInt(fees.get());
        totalCost = Utilities.round2(totalCost,2);
        totalCostFinal.set(String.valueOf(totalCost));

       /* double totalCost = 0;
        totalCost = Double.parseDouble(productsCost.get()) + Double.parseDouble(fees.get());
        totalCostFinal.set(String.valueOf(totalCost));*/
    }


    public void GetBasketItems() {
        if (alertDialog.isShowing()){}else {
            showLoadingDialog(activity);
        }
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetUserBasket2()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseBasketItems, this::handleErrorBasketItems));
    }

    private void handleResponseBasketItems(BasketResponse basketItemsResponse) {
        basketItemsResponse2=basketItemsResponse;

        if (!addressid.get().equals("-1")){
            GetFees();
        }else {
            alertDialog.dismiss();

            basketCounter.set(String.valueOf(basketItemsResponse2.getData().getBasket_items().size()));
            activity.initRec(basketItemsResponse2);
            double tCost = 0;
            for (int i = 0; i < basketItemsResponse2.getData().getBasket_items().size(); i++) {
                tCost += basketItemsResponse2.getData().getBasket_items().get(i).getTotal_cost();
            }
            productsCost.set(String.valueOf(Utilities.round2(tCost,2)));
            double totalCost1 = 0;
            totalCost1 = Double.parseDouble(productsCost.get()) + Double.parseDouble(fees.get());
            totalCostFinal.set(String.valueOf(Utilities.round2(tCost,2)));
        }

    }

    private void handleErrorBasketItems(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void GetFees() {
        if (alertDialog.isShowing()){}else {
            showLoadingDialog(activity);
        }
        FeesRequest feesRequest = new FeesRequest(addressId);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetFees(feesRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseFees, this::handleErrorsFees));
    }

    private void handleResponseFees(FeesResponse feesResponse) {
        //alertDialog.dismiss();

        basketCounter.set(String.valueOf(basketItemsResponse2.getData().getBasket_items().size()));
        activity.initRec(basketItemsResponse2);
        double tCost = 0;
        for (int i=0;i<basketItemsResponse2.getData().getBasket_items().size();i++){
            tCost += basketItemsResponse2.getData().getBasket_items().get(i).getTotal_cost();
        }
        productsCost.set(String.valueOf(tCost));
        double totalCost1 = 0;
        totalCost1 = Double.parseDouble(productsCost.get()) + Double.parseDouble(fees.get());
        totalCostFinal.set(String.valueOf(tCost));


        fees.set(String.valueOf(feesResponse.getData().getOrder_cost()));
        double totalCost = 0.0;
        totalCost = Double.parseDouble(productsCost.get()) + Integer.parseInt(fees.get());
        totalCostFinal.set(String.valueOf(totalCost));

        if (!activity.binding.editCoupon.getText().toString().equals(""))
            CheckCoupon(activity.binding.editCoupon.getText().toString());

        else
            alertDialog.dismiss();

    }

    private void handleErrorsFees(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void CheckCoupon(String coupon) {
        Utilities.hideKeyboard(activity);
        if (alertDialog.isShowing()){}else {
            showLoadingDialog(activity);
        }
        CouponRequest couponRequest = new CouponRequest(coupon);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .CheckCoupon(couponRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseCheckCoupon, this::handleErrorsCheckCoupon));
    }

    private void handleResponseCheckCoupon(CouponResponse couponResponse) {
        alertDialog.dismiss();
        couponResponse2 = couponResponse;

        double totalCost = 0.0;
        double costAfterDiscount = 0.0;
        double costtt = 0.0;
        double dsc = 0.0;
        costtt = Double.parseDouble(productsCost.get());
        totalCost = Double.parseDouble(totalCostFinal.get());
        double disc = (double) couponResponse.getMsg().getDiscount_percentage() / 100;
        dsc = (double) costtt * disc;
        double sss = Utilities.round2(dsc,2);
        int aaa = (int) Math.floor(dsc);
        //Math.ceil(dsc);
        discount.set(String.valueOf(sss));
        costAfterDiscount = totalCost - (aaa);
        totalCostFinal.set(String.valueOf(costAfterDiscount));
    }

    private void handleErrorsCheckCoupon(Throwable throwable) {
        alertDialog.dismiss();
        Constant.showErrorDialog(activity.getString(R.string.invalid_coupon),activity);
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

    public void AddToBasket(int id,int counter) {
        showLoadingDialog(activity);
        AddToBasketRequest addToBasketRequest = new AddToBasketRequest(id,counter);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .AddToBasket(addToBasketRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseAddToBasket, this::handleErrorAddToBasket));
    }

    private void handleResponseAddToBasket(EditProfileResponse editProfileResponse) {
        //alertDialog.dismiss();
        GetBasketItems();
    }

    private void handleErrorAddToBasket(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void DeleteBasketItem(int id,AlertDialog alertDialog5) {
        alertDialog4=alertDialog5;
        alertDialog4.dismiss();
        showLoadingDialog(activity);
        DeleteBasketItemRequest deleteBasketItemRequest = new DeleteBasketItemRequest(id);
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .DeleteBasketItem(deleteBasketItemRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse1, this::handleError1));
    }

    private void handleResponse1(EditProfileResponse editProfileResponse) {
        GetBasketItems();
        //GetFees();

        try {
            if (productsCost.get().equals("0")){
                discount.set("0");
                totalCostFinal.set("0");
            }else {
                double totalCost = 0.0;
                double costAfterDiscount = 0.0;
                totalCost = Double.parseDouble(totalCostFinal.get());
                double disc = (double) couponResponse2.getMsg().getDiscount_percentage() / 100;
                discount.set(String.valueOf(Utilities.round2(totalCost * disc,2)));
                costAfterDiscount = totalCost - (Utilities.round2(totalCost * (disc),2));
                totalCostFinal.set(String.valueOf(costAfterDiscount));
            }

        }catch (Exception e){
            discount.set(String.valueOf("0"));

        }
    }

    private void handleError1(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void DeleteBasketItems() {
        if (alertDialog.isShowing()){}else {
            showLoadingDialog(activity);
        }
        mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                .GetUserBasket2()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseDItems, this::handleErrorDItems));
    }

    private void handleResponseDItems(BasketResponse basketItemsResponse) {
        basketItemsResponse2=basketItemsResponse;

        if (!addressid.get().equals("-1")){
            GetFees();
        }else
            alertDialog.dismiss();

    }

    private void handleErrorDItems(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void MakeOrder() {
        if (basketCounter.get().equals("0")){
            Constant.showErrorDialog(activity.getString(R.string.basket_empty),activity);
        }else {
            if (alertDialog.isShowing()){}else {
                showLoadingDialog(activity);
            }
            SaveOrderRequest saveOrderRequest = new SaveOrderRequest(addressId,activity.binding.editCoupon.getText().toString());
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                    .SaveOrder(saveOrderRequest)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseMakeOrder, this::handleErrorMakeOrder));
        }
    }

    private void handleResponseMakeOrder(EditProfileResponse editProfileResponse) {
        alertDialog.dismiss();
        showCodeDialog(activity.getString(R.string.order_saved),activity);
    }

    private void handleErrorMakeOrder(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void showCodeDialog(String code, Context context) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_alert_code, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog1 = builder.create();
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

    public void DeleteAll() {
            if (alertDialog.isShowing()){}else {
                showLoadingDialog(activity);
            }
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(Token)
                    .DeleteBasketItems()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseDeleteAll, this::handleErrorDeleteAll));
    }

    private void handleResponseDeleteAll(EditProfileResponse editProfileResponse) {
       // alertDialog.dismiss();
        GetBasketItems();
    }

    private void handleErrorDeleteAll(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors2(throwable,activity);
    }

    public void chooseCardPaymentMethod(int type) {
        if (type == 1) {
            cardType = "CASH";
            activity.binding.imgCredit.setImageResource(R.drawable.cash);
        } else if (type == 2) {
            cardType = "PAY";
            activity.binding.imgCredit.setImageResource(R.drawable.pay);
        }
    }

}
