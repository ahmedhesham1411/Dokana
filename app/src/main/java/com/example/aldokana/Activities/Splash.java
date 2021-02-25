package com.example.aldokana.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.aldokana.R;
import com.example.aldokana.Utils.Preferences;

import static com.example.aldokana.Activities.LocaleManeger.getLanguagePref;

public class Splash extends BaseActivity {
    private String Token,state;
    Preferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLanguagePref(this);

        Token = preferences.GetToken(this);
        state = preferences.GetUserState(this);
        Log.d("ahmed ",Token);
        Log.d("ahmed ",state);

        if (Token.equals("default") || Token.equals("visitor")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(Splash.this, Slider.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(mainIntent);
                    finish();
                }
            }, 3500);
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Splash.this,MainPageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("key","1");
                    startActivity(intent);
                }
            }, 3500);
        }


    }
}
