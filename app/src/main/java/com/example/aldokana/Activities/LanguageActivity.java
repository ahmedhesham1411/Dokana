package com.example.aldokana.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aldokana.R;
import com.example.aldokana.Utils.LocaleManeger;
import com.example.aldokana.databinding.ActivityLanguageBinding;

import static com.example.aldokana.Utils.LocaleManeger.ARABIC;
import static com.example.aldokana.Utils.LocaleManeger.setNewLocale;

public class LanguageActivity extends BaseActivity {

    public ActivityLanguageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_language);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNewLocale(LanguageActivity.this,ARABIC);
                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        binding.endlish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNewLocale(LanguageActivity.this, LocaleManeger.ENGLISH);
                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }
}