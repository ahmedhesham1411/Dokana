package com.example.aldokana.viewModels;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

public class SliderAdapterViewModel extends ViewModel {
    Activity activity;
    public SliderAdapterViewModel(Activity activity) {
        this.activity = activity;
    }
}
