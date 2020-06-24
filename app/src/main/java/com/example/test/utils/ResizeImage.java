package com.example.test.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.example.test.R;


public class ResizeImage {//这个类是获得一张图片，图片大小可以自己在screenW和screenH中通过更改倍数实现，emmm不知道现在在不同的分辨率的手机上会不会出BUG

    public static BitmapFactory.Options bfoOptions = new BitmapFactory.Options();

    public static Bitmap resizeImage(int imageid, Resources resource,int screenW, int screenH){
        Bitmap bp;
        bfoOptions.inScaled = false;
        bp = BitmapFactory.decodeResource(resource,imageid, bfoOptions);
        int W = bp.getWidth();
        int H = bp.getHeight();

        bp = BitmapFactory.decodeResource(resource, imageid);
        return  Bitmap.createScaledBitmap(bp, W*screenW/1920,
                H*screenH/1080, true);
    }

}