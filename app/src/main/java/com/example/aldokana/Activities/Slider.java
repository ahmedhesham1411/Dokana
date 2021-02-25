package com.example.aldokana.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.aldokana.Adapter.Home_slider_adapter;
import com.example.aldokana.R;
import com.example.aldokana.databinding.ActivitySlicerBinding;
import com.example.aldokana.viewModels.ModelTest;
import com.example.aldokana.viewModels.SliderAdapterViewModel;
import com.smarteist.autoimageslider.CircularSliderHandle;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class Slider extends BaseActivity {

    public ActivitySlicerBinding binding;

    LinearLayoutCompat skip,start;
    List<Integer> imgs;
    public SliderView sliderView;
    ModelTest viewModel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_slicer);
        viewModel = new ModelTest(this);

        skip=findViewById(R.id.skip);
        start=findViewById(R.id.startaa);
        imgs=new ArrayList<>();
        if (LocaleManeger.getLanguagePref(this).equals("ar")) {
            imgs.add(R.drawable.img1);
            imgs.add(R.drawable.img2);
            imgs.add(R.drawable.img3);
        }
        else if (LocaleManeger.getLanguagePref(this).equals("en")){
            imgs.add(R.drawable.img111);
            imgs.add(R.drawable.img222);
            imgs.add(R.drawable.img333);
        }

        //sliderView = findViewById(R.id.imageSliderHome);
        Home_slider_adapter adapterr = new Home_slider_adapter(this,imgs,skip,start);
        binding.imageSliderHome.setSliderAdapter(adapterr);
        binding.imageSliderHome.setCircularHandlerEnabled(true);

        //Toast.makeText(Slider.this, String.valueOf(binding.imageSliderHome.getCurrentPagePosition()), Toast.LENGTH_SHORT).show();
    }
}
