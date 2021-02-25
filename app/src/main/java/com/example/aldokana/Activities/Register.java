package com.example.aldokana.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import com.example.aldokana.R;
import com.example.aldokana.databinding.ActivityRegisterBinding;
import com.example.aldokana.viewModels.RegisterViewModel;

import ng.max.slideview.SlideView;

public class Register extends BaseActivity {

    public ActivityRegisterBinding binding;
    RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        viewModel = new RegisterViewModel(this);
        binding.setRegisterVM(viewModel);

        binding.slideView.setOnSlideCompleteListener(new SlideView.OnSlideCompleteListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onSlideComplete(SlideView slideView) {
                // vibrate the device
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(500);

                viewModel.Validate();
            }
        });

        binding.signInTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });

        binding.nameTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearName.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                    binding.profileImg.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
                }
                if (!hasFocus) {
                    binding.linearName.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                    binding.profileImg.setColorFilter(getResources().getColor(R.color.colorTextHint), PorterDuff.Mode.SRC_ATOP);
                }
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

        binding.emailTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearEmail.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                    binding.emailImg.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
                }
                if (!hasFocus) {
                    binding.linearEmail.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                    binding.emailImg.setColorFilter(getResources().getColor(R.color.colorTextHint), PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        binding.passwordTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearPassword.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                    binding.passwordImg.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
                }
                if (!hasFocus) {
                    binding.linearPassword.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                    binding.passwordImg.setColorFilter(getResources().getColor(R.color.colorTextHint), PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

    }
}