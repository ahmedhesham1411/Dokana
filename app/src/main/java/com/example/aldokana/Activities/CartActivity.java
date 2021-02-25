package com.example.aldokana.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.aldokana.Adapter.CartItemsAdapter;
import com.example.aldokana.Adapter.HomeAdapter;
import com.example.aldokana.Fragments.HomeFragment;
import com.example.aldokana.Interfaces.CartItemPlusMinus;
import com.example.aldokana.Model.BasketItemDataModel;
import com.example.aldokana.Model.BasketItemsResponse;
import com.example.aldokana.Model.BasketResponse;
import com.example.aldokana.Model.HomeItemModel;
import com.example.aldokana.MyButtonBold;
import com.example.aldokana.MyTextViewCairo;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Preferences;
import com.example.aldokana.databinding.ActivityCartBinding;
import com.example.aldokana.viewModels.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends BaseActivity implements CartItemPlusMinus {

    public ActivityCartBinding binding;
    CartViewModel cartViewModel;
    List<HomeItemModel> homeItemModels;
    CartItemsAdapter cartItemsAdapter;
    Preferences preferences;
    AlertDialog alertDialog,alertDialog1;
    List<BasketItemDataModel> basketItemDataModels;
    String addressId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);

        cartViewModel=new CartViewModel(this);
        binding.setCartVM(cartViewModel);

        //addressId = String.valueOf(preferences.GetAddressId(getApplicationContext()));
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        binding.chooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this,ChooseAddressActivity.class);
                startActivity(intent);
            }
        });
        binding.cardNumEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.layCardNum.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shadow_red));
                }
                if (!hasFocus) {
                    binding.layCardNum.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shadow));
                }
            }
        });

        binding.dateEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.dateEdt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shadow_red));
                }
                if (!hasFocus) {
                    binding.dateEdt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shadow));
                }
            }
        });

        binding.cvcEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.cvcEdt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shadow_red));
                }
                if (!hasFocus) {
                    binding.cvcEdt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shadow));
                }
            }
        });

        binding.cardNameEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.cardNameEdt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shadow_red));
                }
                if (!hasFocus) {
                    binding.cardNameEdt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shadow));
                }
            }
        });

        binding.removeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteAllDialog();
            }
        });

        binding.addCouponTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.editCoupon.getText().toString().equals("")){}else
                cartViewModel.CheckCoupon(binding.editCoupon.getText().toString());
            }
        });

        binding.makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartViewModel.addressid.get().equals("-1")){
                    showErrorDialog(getString(R.string.choose_address),view.getContext());
                }else {
                    cartViewModel.MakeOrder();
                }
            }
        });
    }

    @Override
    public void plus(int id) {
        cartViewModel.AddToBasket(id,1);
    }

    @Override
    public void minus(int id) {
        cartViewModel.AddToBasket(id,-1);
    }

    @Override
    public void delete(int id) {
        showDeleteItemDialog(id);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void showDeleteItemDialog(int id) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.custom_alert_delete_item_orders, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.ErrorDialog);
        builder.setView(view);

        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        MyTextViewCairo yes = alertDialog.findViewById(R.id.delete);
        MyTextViewCairo no = alertDialog.findViewById(R.id.cancel);
        AppCompatImageView close = alertDialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        yes.setOnClickListener(v -> {
            cartViewModel.DeleteBasketItem(id,alertDialog);
        });

        no.setOnClickListener(v -> { ;
            alertDialog.dismiss();
        });

    }

    private void showDeleteAllDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.custom_alert_delete_item_all, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.ErrorDialog);
        builder.setView(view);

        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        MyTextViewCairo yes = alertDialog.findViewById(R.id.delete);
        MyTextViewCairo no = alertDialog.findViewById(R.id.cancel);
        AppCompatImageView close = alertDialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        yes.setOnClickListener(v -> {
            alertDialog.dismiss();
            cartViewModel.DeleteAll();
        });

        no.setOnClickListener(v -> { ;
            alertDialog.dismiss();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        cartViewModel=new CartViewModel(this);
        binding.setCartVM(cartViewModel);
    }

    public void initRec(BasketResponse basketItemsResponse){
        basketItemDataModels = basketItemsResponse.getData().getBasket_items();
        cartItemsAdapter = new CartItemsAdapter(this,basketItemDataModels, CartActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false);
        binding.cartItemsRecycler.setLayoutManager(linearLayoutManager);
        binding.cartItemsRecycler.setHasFixedSize(true);
        binding.cartItemsRecycler.setAdapter(cartItemsAdapter);
    }

    public void showErrorDialog(String errorMsg, Context context) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_alert_error, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog1 = builder.create();
        alertDialog1.show();
        MyTextViewCairo error = view.findViewById(R.id.error_message);
        error.setText(errorMsg);
        MyButtonBold ok;
        ok = view.findViewById(R.id.btn_error_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
            }
        });
    }

}