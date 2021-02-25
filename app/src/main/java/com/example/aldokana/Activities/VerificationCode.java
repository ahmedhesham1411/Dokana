package com.example.aldokana.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.aldokana.MyButtonBold;
import com.example.aldokana.MyTextViewCairo;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;
import com.example.aldokana.databinding.ActivityVerificationCodeBinding;
import com.example.aldokana.viewModels.CodeViewModel;

import ng.max.slideview.SlideView;

public class VerificationCode extends BaseActivity {

    public ActivityVerificationCodeBinding binding;
    CodeViewModel codeViewModel;
    Preferences preferences;
    public String email,entered_code,code;
    AlertDialog alertDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verification_code);
        codeViewModel=new CodeViewModel(this);
        binding.setCodeVM(codeViewModel);

        binding.slideView.setOnSlideCompleteListener(new SlideView.OnSlideCompleteListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onSlideComplete(SlideView slideView) {
                // vibrate the device
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(500);
                entered_code = codeViewModel.num1.get()+codeViewModel.num2.get()+codeViewModel.num3.get()+codeViewModel.num4.get()+codeViewModel.num5.get();
                if (entered_code.equals(codeViewModel.code)){
                    preferences.saveUserState(VerificationCode.this,"true");
                    showCodeDialog(getString(R.string.codeconf),VerificationCode.this);
                }else {
                    Constant.showErrorDialog(getString(R.string.codeconfnot),VerificationCode.this);
                }
            }

        });


        binding.signInTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerificationCode.this,Login.class);
                startActivity(intent);
            }
        });

        binding.signUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerificationCode.this,Register.class);
                startActivity(intent);
            }
        });

        binding.num1Edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.num1Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_shadow_colored));
                }
                if (!hasFocus) {
                    if (!codeViewModel.num1.get().equals("")){
                        binding.num1Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_shadow_full));
                    }else
                    binding.num1Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_shadow));
                }
            }
        });

        binding.num2Edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.num2Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_shadow_colored));
                }
                if (!hasFocus) {
                    if (!codeViewModel.num2.get().equals("")){
                        binding.num2Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_shadow_full));
                    }else
                    binding.num2Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_shadow));
                }
            }
        });
        binding.num3Edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.num3Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_shadow_colored));
                }
                if (!hasFocus) {
                    if (!codeViewModel.num3.get().equals("")){
                        binding.num3Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_shadow_full));
                    }else
                    binding.num3Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_shadow));
                }
            }
        });
        binding.num4Edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.num4Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_shadow_colored));
                }
                if (!hasFocus) {
                    if (!codeViewModel.num4.get().equals("")){
                        binding.num4Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_shadow_full));
                    }else
                    binding.num4Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_shadow));
                }
            }
        });

        binding.num5Edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.num5Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_shadow_colored));
                }
                if (!hasFocus) {
                    if (!codeViewModel.num5.get().equals("")){
                        binding.num5Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_shadow_full));
                    }else
                        binding.num5Edt.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_shadow));
                }
            }
        });


        binding.num1Edt.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                codeViewModel.num1.set(s.toString());
                requestFocus(1);
            }
        });
        binding.num2Edt.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                codeViewModel.num2.set(s.toString());
                requestFocus(2);

            }
        });

        binding.num3Edt.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                codeViewModel.num3.set(s.toString());
                requestFocus(3);

            }
        });

        binding.num4Edt.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                codeViewModel.num4.set(s.toString());
                requestFocus(4);
            }
        });

        binding.num5Edt.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                codeViewModel.num5.set(s.toString());
                binding.num5Edt.clearFocus();
                //Constant.hideKeyboard(VerificationCode.this);
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
               /* if (codeViewModel.entered_code == codeViewModel.code){
                    Constant.showCodeDialog(getString(R.string.codeconf),VerificationCode.this);
                }else {
                    Constant.showErrorDialog(getString(R.string.codeconfnot),VerificationCode.this);
                }*/
            }
        });

        email = preferences.GetEmail(this);
        codeViewModel.GetCode(email);

    }

    public void requestFocus(int input) {
        String str = "";
        switch (input) {
            case 1:
                    binding.num2Edt.requestFocus();
                break;

            case 2:
                    binding.num3Edt.requestFocus();
                break;

            case 3:
                    binding.num4Edt.requestFocus();
                break;
            case 4:
                binding.num5Edt.requestFocus();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        preferences.Clear(VerificationCode.this);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showCodeDialog(String code, Context context) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_alert_code, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog2 = builder.create();
        alertDialog2.show();
        alertDialog2.setCancelable(false);
        MyTextViewCairo error = view.findViewById(R.id.error_message);
        error.setText(code);
        MyButtonBold ok;
        ok = view.findViewById(R.id.btn_error_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog2.dismiss();
                Intent intent = new Intent(VerificationCode.this,Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                //intent.putExtra("key","1");
                startActivity(intent);
            }
        });
    }

}