package com.example.aldokana.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aldokana.R;
import com.example.aldokana.databinding.ActivityAddAddressBinding;
import com.example.aldokana.viewModels.AddAddressViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddAddressActivity extends BaseActivity {

    Typeface face;
    public ActivityAddAddressBinding binding;

    public ArrayAdapter<String> areaAdapter;
    public ArrayAdapter<String> cityAdapter;
    AddAddressViewModel viewModel;
    public TextView selectedText2,selectedText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_address);

        viewModel = new AddAddressViewModel(this);
        binding.setAddAddressVM(viewModel);

        face= Typeface.createFromAsset(getAssets(), "cairo.ttf");
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        areaAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, viewModel.areaList);
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.areaSpinner.setAdapter(areaAdapter);
        binding.areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView selectedText = (TextView) parent.getChildAt(0);
                if (selectedText != null) {
                    selectedText.setTypeface(face);
                    selectedText.setTextColor(getColor(R.color.colorTextHint));
                }
                viewModel.areaId = viewModel.areaIdList.get(position);
                if (viewModel.areaId!=0){
                    viewModel.getCities(viewModel.areaId);
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                TextView selectedText = (TextView) parent.getChildAt(0);
                selectedText.setTypeface(face);
                selectedText.setTextColor(getColor(R.color.colorTextHint));
            }
        });

        initCities();

        binding.firstNameTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearFirstName.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                }
                if (!hasFocus) {
                    binding.linearFirstName.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                }
            }
        });

        binding.secondNameTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearSecondName.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                }
                if (!hasFocus) {
                    binding.linearSecondName.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                }
            }
        });

        binding.streetTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearStreet.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                }
                if (!hasFocus) {
                    binding.linearStreet.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                }
            }
        });

        binding.towerTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearTower.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                }
                if (!hasFocus) {
                    binding.linearTower.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                }
            }
        });

        binding.phoneTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearPhone.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                }
                if (!hasFocus) {
                    binding.linearPhone.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                }
            }
        });

        binding.addAddressClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.Validate();
            }
        });
    }

    public void initCities(){
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, viewModel.cityList);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.citySpinner.setAdapter(cityAdapter);
        binding.citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.cityId = viewModel.cityIdList.get(position);
                selectedText2 = (TextView) parent.getChildAt(0);
                if (selectedText2 != null) {
                    selectedText2.setTypeface(face);
                    selectedText2.setTextColor(getColor(R.color.colorTextHint));
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedText3 = (TextView) parent.getChildAt(0);
                selectedText3.setTypeface(face);
                selectedText3.setTextColor(getColor(R.color.colorTextHint));
            }
        });
    }
}