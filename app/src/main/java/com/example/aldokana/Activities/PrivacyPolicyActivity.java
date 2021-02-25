package com.example.aldokana.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.aldokana.Model.PrivacyPolicyResponse;
import com.example.aldokana.R;
import com.example.aldokana.databinding.ActivityPrivacyPolicyBinding;
import com.example.aldokana.viewModels.PrivacyPolicyViewModel;

public class PrivacyPolicyActivity extends BaseActivity {

    public ActivityPrivacyPolicyBinding binding;
    PrivacyPolicyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_privacy_policy);

        viewModel = new PrivacyPolicyViewModel(this);
        binding.setPrivacyVM(viewModel);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void initPrivacy(PrivacyPolicyResponse privacyPolicyResponse){
        binding.privacyTxt.setText(privacyPolicyResponse.getData().getPrivacy().get(0).getPrivacy());
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}