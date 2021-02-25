package com.example.aldokana.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.aldokana.Fragments.HomeFragment;
import com.example.aldokana.Fragments.OrdersFragment;
import com.example.aldokana.Fragments.OffersFragment;
import com.example.aldokana.Fragments.ProfileFragment;
import com.example.aldokana.Model.LoginRequest;
import com.example.aldokana.Model.RegisterationResponse;
import com.example.aldokana.Model.User;
import com.example.aldokana.MyButtonBold;
import com.example.aldokana.MyTextViewCairo;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;
import com.example.aldokana.Utils.Utilities;
import com.example.aldokana.databinding.ActivityMainPageBinding;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.example.aldokana.Utils.GlobalVariables.MAIN_FRAGMENT_ID;
import static com.example.aldokana.Utils.GlobalVariables.OFFERS_FRAGMENT_ID;
import static com.example.aldokana.Utils.GlobalVariables.ORDERS_FRAGMENT_ID;
import static com.example.aldokana.Utils.GlobalVariables.PROFILE_FRAGMENT_ID;

public class MainPageActivity extends BaseActivity {

    public ActivityMainPageBinding binding;
    private Animation rotateAnimation;
    private FragmentManager fragmentManager;
    private String selectedFragmentTag;
    boolean doubleBackToExitPressedOnce = false;
    boolean isHome = true;
    Preferences preferences;
    String Token;
    String MAIN_FRAGMENT_TAG="1",ORDERS_FRAGMENT_TAG="2",OFFERS_FRAGMENT_TAG="3",PROFILE_FRAGMENT_TAG="4";
    AlertDialog alertDialog,alertDialog1;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_page);
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.item_animation_rise_up);
        Token = preferences.GetToken(this);
        if (Token.equals("visitor")){}else{
            ProfileData(Token);
        }
        Log.d("ahmed ",Token);
        fragmentManager = getSupportFragmentManager();



        //editLayout(MAIN_FRAGMENT_TAG);
        binding.homeLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editLayout(MAIN_FRAGMENT_TAG);
                isHome=true;
            }
        });
        binding.orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Token.equals("visitor")){
                    Constant.logInDialog("a",MainPageActivity.this);
                }else {
                    editLayout(ORDERS_FRAGMENT_TAG);
                    isHome = false;
                }
            }
        });
        binding.offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editLayout(OFFERS_FRAGMENT_TAG);
                isHome=false;
            }
        });
        binding.myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                if (Token.equals("visitor")){
                    Constant.logInDialog("a",MainPageActivity.this);
                }else {
                    editLayout(PROFILE_FRAGMENT_TAG);
                    isHome = false;
                }*/
                editLayout(PROFILE_FRAGMENT_TAG);
                isHome = false;
            }
        });
        //editLayout(MAIN_FRAGMENT_TAG);

        if (getIntent().getExtras().getString("key").equals("1")) {
            editLayout(MAIN_FRAGMENT_TAG);
        } else if (getIntent().getExtras().getString("key").equals("2")) {
            editLayout(ORDERS_FRAGMENT_TAG);
        }
        else if (getIntent().getExtras().getString("key").equals("3")) {
            editLayout(OFFERS_FRAGMENT_TAG);
        } else if (getIntent().getExtras().getString("key").equals("4")) {
            editLayout(PROFILE_FRAGMENT_TAG);
        }
    }

    public void editLayout(String fragmentTag) {

        binding.main.clearAnimation();
        binding.ordersImg.clearAnimation();
        binding.offersImg.clearAnimation();
        binding.profile.clearAnimation();

        binding.main.setImageResource(R.drawable.homeimg);
        binding.ordersImg.setImageResource(R.drawable.ordersimg);
        binding.offersImg.setImageResource(R.drawable.offersimg);
        binding.profile.setImageResource(R.drawable.profileimggg);

        binding.main.setColorFilter(getResources().getColor(R.color.colortext2), PorterDuff.Mode.SRC_ATOP);
        binding.ordersImg.setColorFilter(getResources().getColor(R.color.colortext2), PorterDuff.Mode.SRC_ATOP);
        binding.offersImg.setColorFilter(getResources().getColor(R.color.colortext2), PorterDuff.Mode.SRC_ATOP);
        binding.profile.setColorFilter(getResources().getColor(R.color.colortext2), PorterDuff.Mode.SRC_ATOP);

        binding.mainTxt.setTextColor(getResources().getColor(R.color.colortext2));
        binding.ordersTxt.setTextColor(getResources().getColor(R.color.colortext2));
        binding.offersTxt.setTextColor(getResources().getColor(R.color.colortext2));
        binding.profileTxt.setTextColor(getResources().getColor(R.color.colortext2));

        if (fragmentTag.equals(MAIN_FRAGMENT_TAG)) {
            binding.main.setImageResource(R.drawable.homeimgcolored);

            binding.main.startAnimation(rotateAnimation);
            binding.mainTxt.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            binding.main.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
            pushFragment(MAIN_FRAGMENT_ID,null);
        } else if (fragmentTag.equals(ORDERS_FRAGMENT_TAG)) {
            binding.ordersImg.setImageResource(R.drawable.ordersimgcolored);

            binding.ordersImg.startAnimation(rotateAnimation);
            binding.ordersTxt.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            binding.ordersImg.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
            pushFragment(ORDERS_FRAGMENT_ID,null);
        } else if (fragmentTag.equals(OFFERS_FRAGMENT_TAG)) {
            binding.offersImg.setImageResource(R.drawable.offersimgcolored);

            binding.offersImg.startAnimation(rotateAnimation);
            binding.offersTxt.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            binding.offersImg.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
            pushFragment(OFFERS_FRAGMENT_ID,null);
        } else if (fragmentTag.equals(PROFILE_FRAGMENT_TAG)) {
            binding.profile.setImageResource(R.drawable.profileimgggcolored);

            binding.profile.startAnimation(rotateAnimation);
            binding.profileTxt.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            binding.profile.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
            pushFragment(PROFILE_FRAGMENT_ID,null);
        }
    }

    public void pushFragment(int fragmentId, Bundle bundle) {
        String tag;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;

        switch (fragmentId) {
            case MAIN_FRAGMENT_ID:
                tag = MAIN_FRAGMENT_TAG;
                fragment = new HomeFragment();
                int count = fragmentManager.getBackStackEntryCount();
                for (int i = 0; i < count; ++i)
                    fragmentManager.popBackStackImmediate();

                break;
            case ORDERS_FRAGMENT_ID:
                tag = ORDERS_FRAGMENT_TAG;
                fragment = new OrdersFragment();
                fragment.setArguments(bundle);
                break;
            case OFFERS_FRAGMENT_ID:
                tag = OFFERS_FRAGMENT_TAG;
                fragment = new OffersFragment();
                fragment.setArguments(bundle);
                break;
            case PROFILE_FRAGMENT_ID:
                tag = PROFILE_FRAGMENT_TAG;
                fragment = new ProfileFragment();
                fragment.setArguments(bundle);
                break;

            default:
                try {
                    throw new Exception("Invalid fragment id :: " + fragmentId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
        }

        //editLayout(tag);

        selectedFragmentTag = tag;

        fragmentTransaction.replace(R.id.container, fragment, tag).commit();
    }

    @Override
    public void onBackPressed() {
        Utilities.hideKeyboard(this);
        if (isHome){
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }else {
            editLayout(MAIN_FRAGMENT_TAG);
            isHome=true;
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
            preferences.saveId(MainPageActivity.this,String.valueOf(user.getUser().getId()));
            preferences.saveName(MainPageActivity.this,user.getUser().getName());
            preferences.saveEmail(MainPageActivity.this,user.getUser().getEmail());
            preferences.savePhone(MainPageActivity.this,user.getUser().getPhone());
            preferences.savePhoto(MainPageActivity.this,user.getUser().getPhoto());
            preferences.saveStatus(MainPageActivity.this,String.valueOf(user.getUser().getStatus()));
        }catch (Exception e){}
    }

    private void handleError(Throwable throwable) {
        //Constant.handleErrorsProfile(throwable,MainPageActivity.this);
        showErrorDialog(getString(R.string.loginagain),this);
    }



    public boolean checkPermissionForReadExtertalStorage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        }
        return false;
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
                preferences.Clear(MainPageActivity.this);
                Intent intent = new Intent(MainPageActivity.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Token.equals("visitor")){}else{
            ProfileData(Token);
        }
    }
}