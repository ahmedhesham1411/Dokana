package com.example.aldokana.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.aldokana.R;
import com.example.aldokana.Utils.Preferences;
import com.example.aldokana.databinding.ActivityLoginBinding;
import com.example.aldokana.viewModels.LoginViewModel;

import ng.max.slideview.SlideView;

public class Login extends BaseActivity {

    public ActivityLoginBinding binding;
    LoginViewModel viewModel;
    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        viewModel = new LoginViewModel(this);
        binding.setLoginVM(viewModel);

        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.INTERNET,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
        };



        binding.slideView.setOnSlideCompleteListener(new SlideView.OnSlideCompleteListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onSlideComplete(SlideView slideView) {
                // vibrate the device
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(500);


                if (!hasPermissions(Login.this, PERMISSIONS)) {
                    ActivityCompat.requestPermissions(Login.this, PERMISSIONS, PERMISSION_ALL);
                }else
                viewModel.Validate();
            }

        });

        binding.signUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

        binding.phoneTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearPhone.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                    binding.phoneImg.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
                }
                if (!hasFocus) {
                    binding.linearPhone.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                    binding.phoneImg.setColorFilter(getResources().getColor(R.color.colorTextHint), PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        binding.skipSignTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.saveToken(Login.this,"visitor");
                Intent intent = new Intent(Login.this,MainPageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("key","1");
                startActivity(intent);
            }
        });
    }

    public static boolean hasPermissions(Context context, String[] permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}