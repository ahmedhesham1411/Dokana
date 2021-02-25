package com.example.aldokana.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aldokana.Activities.AboutAppActivity;
import com.example.aldokana.Activities.AddressesActivity;
import com.example.aldokana.Activities.CallUsActivity;
import com.example.aldokana.Activities.EditProfileActivity;
import com.example.aldokana.Activities.LanguageActivity;
import com.example.aldokana.Activities.Login;
import com.example.aldokana.Activities.MainPageActivity;
import com.example.aldokana.Activities.NotificationsActivity;
import com.example.aldokana.Activities.PrivacyPolicyActivity;
import com.example.aldokana.Activities.PromoCodeActivity;
import com.example.aldokana.Activities.QuestionAndAnswerActivity;
import com.example.aldokana.Activities.TermsAndConditionsActivity;
import com.example.aldokana.MyTextViewCairo;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Preferences;
import com.example.aldokana.databinding.FragmentProfileBinding;
import com.example.aldokana.viewModels.ProfileFragmentViewModel;

public class ProfileFragment extends Fragment {

    public FragmentProfileBinding binding;
    AlertDialog alertDialog;
    Preferences preferences;
    ProfileFragmentViewModel viewModel;
    String Token;
    Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);

        viewModel = new ProfileFragmentViewModel(ProfileFragment.this);
        binding.setLoginVM(viewModel);
        activity=getActivity();
        Token = preferences.GetToken(getContext());

        if (Token.equals("visitor")){
            binding.logout.setVisibility(View.GONE);
        }else{
            binding.logout.setVisibility(View.VISIBLE);
        }


        binding.chooseLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LanguageActivity.class);
                startActivity(intent);
            }
        });

        binding.editProfileTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Token.equals("visitor")){
                    Constant.logInDialog("a",activity);
                }else {
                    Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                    startActivity(intent);
                }

            }
        });

        binding.promoCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PromoCodeActivity.class);
                startActivity(intent);
            }
        });

        binding.notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NotificationsActivity.class);
                startActivity(intent);
            }
        });

        binding.myAddresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Token.equals("visitor")){
                    Constant.logInDialog("a",activity);
                }else {
                    Intent intent = new Intent(getContext(), AddressesActivity.class);
                    startActivity(intent);
                }
            }
        });

        binding.questionAndAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuestionAndAnswerActivity.class);
                startActivity(intent);
            }
        });

        binding.aboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AboutAppActivity.class);
                startActivity(intent);
            }
        });

        binding.policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PrivacyPolicyActivity.class);
                startActivity(intent);
            }
        });

        binding.termsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TermsAndConditionsActivity.class);
                startActivity(intent);
            }
        });

        binding.callUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Token.equals("visitor")){
                    Constant.logInDialog("a",activity);
                }else {
                    Intent intent = new Intent(getContext(), CallUsActivity.class);
                    startActivity(intent);
                }
            }
        });
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogoutDialog();
            }
        });
        return binding.getRoot();
    }

    public void showLogoutDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.custom_alert_logout, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.ErrorDialog);
        builder.setView(view);

        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        MyTextViewCairo yes = alertDialog.findViewById(R.id.yes_txt);
        MyTextViewCairo no = alertDialog.findViewById(R.id.no_txt);
        AppCompatImageView close = alertDialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        yes.setOnClickListener(v -> {
            //preferences.saveToken(getActivity(),"default");
            preferences.Clear(getActivity());
            Intent intent = new Intent(getContext(), Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            alertDialog.dismiss();
        });

        no.setOnClickListener(v -> { ;
            alertDialog.dismiss();
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel = new ProfileFragmentViewModel(ProfileFragment.this);
        binding.setLoginVM(viewModel);
    }
}