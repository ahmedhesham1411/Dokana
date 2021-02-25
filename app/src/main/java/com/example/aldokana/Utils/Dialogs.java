package com.example.aldokana.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.aldokana.R;

public abstract class Dialogs {


    public static Dialog noInternetDialog;


    public static void showEditDialog(final Activity currentActivity,Class targetClass,Class targetClass2) {


        final Dialog dialog = new Dialog(currentActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_alert_edit_profile);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView home = dialog.findViewById(R.id.home_txt);
        TextView profile = dialog.findViewById(R.id.profile_txt2);
        AppCompatImageView close = dialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        home.setOnClickListener(v -> {
            Intent intent = new Intent(currentActivity, targetClass);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("key","1");
            currentActivity.startActivity(intent);
            dialog.dismiss();
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(currentActivity, targetClass2);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("key","4");
            currentActivity.startActivity(intent);
            dialog.dismiss();
        });

        profile.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }


    public static void showLoading(Activity activity, LoadingDialog loadingDialog) {
        try {
            loadingDialog.show(((AppCompatActivity) activity).getSupportFragmentManager(), "showLoading");

        } catch (Exception e) {

        }
    }

    public static void dismissLoading(LoadingDialog loadingDialog) {
        try {
            if (loadingDialog != null)
                loadingDialog.dismiss();
            loadingDialog = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showToast(String message, Activity activity) {
        if (message != null && !message.equals(""))
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public interface DialogListener {
        void onChoose(int pos);
    }
}