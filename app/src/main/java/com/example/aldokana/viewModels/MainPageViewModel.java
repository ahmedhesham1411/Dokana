package com.example.aldokana.viewModels;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

public class MainPageViewModel extends ViewModel {
    Activity activity;
    public MainPageViewModel(Activity activity) {
        this.activity = activity;
    }
}
