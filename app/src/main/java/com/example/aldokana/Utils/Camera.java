package com.example.aldokana.Utils;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

public class Camera {

    public static Bitmap resizeBitmap(Activity activity, Bitmap bitmapImage) {
        float aspectRatio = bitmapImage.getWidth() /
                (float) bitmapImage.getHeight();
        int width = 150;
        int height = Math.round(width / aspectRatio);
        Bitmap bitmap = Bitmap.createScaledBitmap(bitmapImage, width, height, false);
        Log.e("SIZE_BITMAP", bitmap.getWidth() + " " + bitmap.getHeight());


        int imageWidth = bitmapImage.getWidth();
        int imageHeight = bitmapImage.getHeight();
        int resultWidth;
        int resultHeight;
        int reqWidth = 200;
        int reqHeight = 250;

        int diffWidth = imageWidth - reqWidth;
        int diffHeight = imageHeight - reqHeight;

        if (diffHeight < 0 || diffWidth < 0) {
            resultWidth = imageWidth;
            resultHeight = imageHeight;
        } else {
            if (diffWidth < diffHeight) {
                resultWidth = reqWidth;
                resultHeight = Math.round(((resultWidth * imageHeight) / imageWidth));
            } else {
                resultHeight = reqHeight;
                resultWidth = Math.round(((resultHeight * imageWidth) / imageHeight));
            }
        }
        return Bitmap.createScaledBitmap(bitmapImage, resultWidth, resultHeight, false);
    }
    public static String getRealPathFromURI(Activity activity, Uri contentURI) {
        String result = null;

        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);

        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            if (cursor.moveToFirst()) {
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                result = cursor.getString(idx);
            }
            cursor.close();
        }
        return result;
    }

}
