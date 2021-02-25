package com.example.aldokana;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class MyEditTextBold extends EditText {
    public MyEditTextBold(Context context) {
        super(context);
    }

    public MyEditTextBold(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditTextBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTypeface(@Nullable Typeface tf, int style) {
            tf= Typeface.createFromAsset(getContext().getAssets(), "cairo.ttf");
            super.setTypeface(tf, Typeface.BOLD);
    }
}