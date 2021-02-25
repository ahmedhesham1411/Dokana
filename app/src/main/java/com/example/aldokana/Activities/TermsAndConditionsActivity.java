package com.example.aldokana.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.aldokana.Model.TermsResponse;
import com.example.aldokana.R;
import com.example.aldokana.databinding.ActivityTermsAndConditionsBinding;
import com.example.aldokana.viewModels.TermsAndConditionsViewModel;

public class TermsAndConditionsActivity extends BaseActivity {

    public ActivityTermsAndConditionsBinding binding;
    TermsAndConditionsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_terms_and_conditions);

        viewModel = new TermsAndConditionsViewModel(this);
        binding.setTermsVM(viewModel);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void initTerms(TermsResponse termsResponse){
        binding.termsTxt.setText(termsResponse.getData().getTerms().get(0).getTerms());
    }
}