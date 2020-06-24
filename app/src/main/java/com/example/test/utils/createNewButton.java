package com.example.test.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;

public class createNewButton {
    public static BitmapFactory.Options bfoOptions = new BitmapFactory.Options();

    public static int screenW,screenH;
    public static AbsoluteLayout layout;
    public static Resources resource;
    public static Context context;
    public createNewButton(int screenW,int screenH,AbsoluteLayout layout,Context context,Resources resource){
        this.screenW=screenW;
        this.screenH=screenH;
        this.layout=layout;
        this.resource=resource;
        this.context=context;
    }

    public Bitmap getBitmapImage(int imageid,int sizeW, int sizeH){
        Bitmap bp;
        bfoOptions.inScaled = false;
        bp = BitmapFactory.decodeResource(resource,imageid, bfoOptions);
        int W = bp.getWidth();
        int H = bp.getHeight();

        bp = BitmapFactory.decodeResource(resource, imageid);
        return  Bitmap.createScaledBitmap(bp, sizeW*W*screenW/1920,
                sizeH*H*screenH/1080, true);
    }

    public  ImageButton setImageButton(ImageButton imageButton, Bitmap bitmap,  int setW, int setH){
        BitmapDrawable imageDrawable;
        int W=bitmap.getWidth();
        int H=bitmap.getHeight();
        imageDrawable=new BitmapDrawable(resource,bitmap);
        imageButton.setImageDrawable(imageDrawable);
        imageButton.setLayoutParams(new AbsoluteLayout.LayoutParams(W*screenW/1920,H*screenH/1080,
                setW*screenW/1920,setH*screenH/1080));
        layout.addView(imageButton);
        return imageButton;
    }
}
