package com.example.aldokana.viewModels;

import android.app.Activity;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.aldokana.Activities.Slider;
import com.example.aldokana.R;

public class ModelTest extends ViewModel {
    Slider activity;
    public ObservableField<String> page_position = new ObservableField<>("");


    public ModelTest(Slider activity) {
        this.activity = activity;

        try {
            page_position.set(String.valueOf(activity.binding.imageSliderHome.getCurrentPagePosition()));
            Toast.makeText(activity, String.valueOf(page_position), Toast.LENGTH_SHORT).show();
        }catch (Exception e){}

    }
}
