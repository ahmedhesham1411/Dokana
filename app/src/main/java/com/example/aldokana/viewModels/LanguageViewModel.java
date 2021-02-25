package com.example.aldokana.viewModels;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

public class LanguageViewModel extends ViewModel {
    Activity activity;
    public LanguageViewModel(Activity activity) {
        this.activity = activity;
    }
}
