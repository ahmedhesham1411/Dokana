package com.example.aldokana.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aldokana.Model.EditProfileRequest;
import com.example.aldokana.Model.EditProfileResponse;
import com.example.aldokana.MyTextViewCairo;
import com.example.aldokana.Network.NetworkUtil;
import com.example.aldokana.R;
import com.example.aldokana.Utils.Camera;
import com.example.aldokana.Utils.Constant;
import com.example.aldokana.Utils.Dialogs;
import com.example.aldokana.Utils.Preferences;
import com.example.aldokana.Utils.RealPathUtil;
import com.example.aldokana.databinding.ActivityEditProfileBinding;
import com.example.aldokana.viewModels.EditProfileViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class EditProfileActivity extends BaseActivity {

    public ActivityEditProfileBinding binding;
    AlertDialog alertDialog;
    EditProfileViewModel viewModel;
    private static final int IMAGE_REQUEST = 1;
    String imageurl;
    public Uri imageUri ;
    public File file;
    public MultipartBody.Part body,body2;
    String pathhhh;
    String filePath;
    Preferences preferences;
    AlertDialog alertDialog2;
    String Token;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    byte[] imageBytese;
    MultipartBody.Builder requestBodyBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);

        viewModel = new EditProfileViewModel(EditProfileActivity.this);
        binding.setLoginVM(viewModel);

        Token = preferences.GetToken(this);

        binding.nameTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearName.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                    binding.profileImg.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
                }
                if (!hasFocus) {
                    binding.linearName.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                    binding.profileImg.setColorFilter(getResources().getColor(R.color.colorTextHint), PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        binding.phoneTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearPhone.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                    binding.phoneImg.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
                }
                if (!hasFocus) {
                    binding.linearPhone.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                    binding.phoneImg.setColorFilter(getResources().getColor(R.color.colorTextHint), PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        binding.emailTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.linearEmail.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey_rounded));
                    binding.emailImg.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
                }
                if (!hasFocus) {
                    binding.linearEmail.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_rounded_grey));
                    binding.emailImg.setColorFilter(getResources().getColor(R.color.colorTextHint), PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,phone,email;
                name = binding.nameTxt.getText().toString();
                phone = binding.phoneTxt.getText().toString();
                email = binding.emailTxt.getText().toString();

               viewModel.Validate(name,phone,email,body);
                //showEditDialog();
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });
    }

    private void showEditDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(EditProfileActivity.this);
        View view = layoutInflater.inflate(R.layout.custom_alert_edit_profile, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this,R.style.ErrorDialog);
        builder.setView(view);

        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        MyTextViewCairo home = alertDialog.findViewById(R.id.home_txt);
        MyTextViewCairo profile = alertDialog.findViewById(R.id.profile_txt2);
        AppCompatImageView close = alertDialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        home.setOnClickListener(v -> {
            Intent intent = new Intent(EditProfileActivity.this, MainPageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("key","1");
            startActivity(intent);
            alertDialog.dismiss();
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(EditProfileActivity.this, MainPageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("key","4");
            startActivity(intent);
            alertDialog.dismiss();
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            imageUri=null;
            body = null;
            filePath="";
            imageUri = data.getData();

            
            try {
                Bitmap bitmapImage;
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                try {
                    bitmap = Camera.resizeBitmap(EditProfileActivity.this, bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                binding.profileImageeee.setImageBitmap(bitmap);

                String filePath = "";
                filePath = getPath(imageUri);
                //Toast.makeText(this, filePath, Toast.LENGTH_SHORT).show();
                file= new File(filePath);
                Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 30, bos);
                body = MultipartBody.Part.createFormData("photo", filePath,
                        RequestBody.create(MediaType.parse("image/*"), bos.toByteArray()));

            } catch (Exception e) {
                Log.e("IMAGE", "GALLERY_REQUEST Exception: " + e.getMessage());
                e.printStackTrace();
            }

        }
    }

    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    public void showLoadingDialog(Context activity) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.loading, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.SheetDialog);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
    }

    public void showEditDialog2() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.custom_alert_edit_profile, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.ErrorDialog);
        builder.setView(view);

        alertDialog2 = builder.create();
        alertDialog2.show();
        alertDialog2.setCancelable(false);
        MyTextViewCairo home = alertDialog2.findViewById(R.id.home_txt);
        MyTextViewCairo profile = alertDialog2.findViewById(R.id.profile_txt2);
        AppCompatImageView close = alertDialog2.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog2.dismiss();
            }
        });

        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainPageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("key","1");
            startActivity(intent);
            alertDialog2.dismiss();
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainPageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("key","4");
            startActivity(intent);
            alertDialog2.dismiss();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}