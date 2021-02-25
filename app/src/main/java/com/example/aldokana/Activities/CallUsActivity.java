package com.example.aldokana.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import com.example.aldokana.Activities.BaseActivity;
import com.example.aldokana.R;
import com.example.aldokana.databinding.ActivityCallUsBinding;
import com.example.aldokana.viewModels.CallUsViewModel;

public class CallUsActivity extends BaseActivity {

    public ActivityCallUsBinding binding;
    CallUsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_call_us);

        viewModel = new CallUsViewModel(this);
        binding.setCallUsVM(viewModel);


        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.nameTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearName.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                }
                if (!hasFocus) {
                    binding.linearName.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                }
            }
        });

        binding.phoneTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearPhone.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                }
                if (!hasFocus) {
                    binding.linearPhone.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                }
            }
        });

        binding.emailTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearEmail.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                }
                if (!hasFocus) {
                    binding.linearEmail.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                }
            }
        });

        binding.reasonTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearReason.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                }
                if (!hasFocus) {
                    binding.linearReason.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                }
            }
        });

        binding.detailsTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearDetails.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                }
                if (!hasFocus) {
                    binding.linearDetails.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                }
            }
        });

        binding.callUsClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.Validate();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}