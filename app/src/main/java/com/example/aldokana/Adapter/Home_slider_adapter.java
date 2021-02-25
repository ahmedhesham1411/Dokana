package com.example.aldokana.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;

import com.example.aldokana.Activities.Login;
import com.example.aldokana.Activities.Slider;
import com.example.aldokana.R;
import com.example.aldokana.databinding.ImageSliderHomeBinding;
import com.smarteist.autoimageslider.CircularSliderHandle;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class Home_slider_adapter extends SliderViewAdapter<Home_slider_adapter.SliderAdapterVH> {

    Slider context;
    List<Integer> imagesList;
    LinearLayoutCompat skip,start;
    public int counter;

    public Home_slider_adapter(Slider context, List<Integer> imagesList, LinearLayoutCompat skip,LinearLayoutCompat start) {
        this.context = context;
        this.imagesList = imagesList;
        this.skip=skip;
        this.start=start;
    }

    public Home_slider_adapter(ImageSliderHomeBinding binding) {

    }


    @Override
    public Home_slider_adapter.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        ImageSliderHomeBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.image_slider_home, parent, false);
        return new SliderAdapterVH(binding);
    }

    @Override
    public void onBindViewHolder(Home_slider_adapter.SliderAdapterVH viewHolder, int position) {

        List<Integer> imageslist1 = imagesList;
        for (int j=0; j < imageslist1.size(); j++){

            int image = imageslist1.get(position);
            viewHolder.binding.imageHome.setImageResource(image);

        }

        if (counter==2){
            context.binding.startaa.setVisibility(View.VISIBLE);
            context.binding.skip.setVisibility(View.GONE);
        }
        if (counter!=2){
            context.binding.startaa.setVisibility(View.GONE);
            context.binding.skip.setVisibility(View.VISIBLE);
        }

        context.binding.imageSliderHome.setCurrentPageListener(new CircularSliderHandle.CurrentPageListener() {
            @Override
            public void onCurrentPageChanged(int currentPosition) {
                counter=currentPosition;
                if (counter==2){
                    context.binding.startaa.setVisibility(View.VISIBLE);
                    context.binding.skip.setVisibility(View.GONE);
                }
                if (counter!=2){
                    context.binding.startaa.setVisibility(View.GONE);
                    context.binding.skip.setVisibility(View.VISIBLE);
                }
            }
        });

        context.binding.skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter==0){
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            context.binding.imageSliderHome.setCurrentPagePosition(1);
                        }
                    });
                    context.binding.startaa.setVisibility(View.GONE);
                    context.binding.skip.setVisibility(View.VISIBLE);
                    notifyDataSetChanged();
                }
                else if (counter==1){
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            context.binding.imageSliderHome.setCurrentPagePosition(2);
                        }
                    });
                    context.binding.startaa.setVisibility(View.VISIBLE);
                    context.binding.skip.setVisibility(View.GONE);
                    notifyDataSetChanged();
                }

            }
        });

        context.binding.startaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getCount() {
        return imagesList.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        private ImageSliderHomeBinding binding;

        private SliderAdapterVH(ImageSliderHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
