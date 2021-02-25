package com.example.aldokana.viewModels;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

public class PromoCodeViewModel extends ViewModel {
    Activity activity;
    public PromoCodeViewModel(Activity activity) {
        this.activity = activity;
    }
}
