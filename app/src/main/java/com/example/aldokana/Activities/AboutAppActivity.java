package com.example.aldokana.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.aldokana.Activities.BaseActivity;
import com.example.aldokana.Model.AboutAppResponse;
import com.example.aldokana.R;
import com.example.aldokana.databinding.ActivityAboutAppBinding;
import com.example.aldokana.viewModels.AboutAppViewModel;

public class AboutAppActivity extends BaseActivity {

    public ActivityAboutAppBinding binding;
    AboutAppViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about_app);

        viewModel = new AboutAppViewModel(this);
        binding.setAboutAppVM(viewModel);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initAbout(AboutAppResponse aboutAppResponse){
        binding.aboutTxt.setText(aboutAppResponse.getData().getAbout().get(0).getAbout());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}