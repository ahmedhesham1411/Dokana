package com.example.aldokana.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;

import com.example.aldokana.Activities.Login;
import com.example.aldokana.Activities.ProductDetailsActivity;
import com.example.aldokana.Activities.Slider;
import com.example.aldokana.R;
import com.example.aldokana.databinding.ImageSliderHomeBinding;
import com.example.aldokana.databinding.ImageSliderProductDetailsBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductDetailsSliderAdapter extends SliderViewAdapter<ProductDetailsSliderAdapter.SliderAdapterVH> {

    ProductDetailsActivity context;
    List<String> imagesList;

    public ProductDetailsSliderAdapter(ProductDetailsActivity context, List<String> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
    }

    public ProductDetailsSliderAdapter(ImageSliderHomeBinding binding) {

    }


    @Override
    public ProductDetailsSliderAdapter.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        ImageSliderProductDetailsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.image_slider_product_details, parent, false);
        return new SliderAdapterVH(binding);
    }

    @Override
    public void onBindViewHolder(ProductDetailsSliderAdapter.SliderAdapterVH viewHolder, int position) {

        List<String> imageslist1 = imagesList;
        for (int j=0; j < imageslist1.size(); j++){

            String image = imageslist1.get(position);
            Picasso.get().load(image).into(viewHolder.binding.imageHome);
        }
    }

    @Override
    public int getCount() {
        return imagesList.size();
    }

    public class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        private ImageSliderProductDetailsBinding binding;

        private SliderAdapterVH(ImageSliderProductDetailsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

}
