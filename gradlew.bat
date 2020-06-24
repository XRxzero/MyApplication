package com.example.test;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.style.AbsoluteSizeSpan;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

/*import com.example.test.View.GameView;*/
import com.example.test.utils.ResizeImage;

public class GameActivity extends Activity implements View.OnClickListener {
    public ImageButton Back;
    public int a;
    public ImageButton[] card=new ImageButton[13];
    public ImageView Card1,Card2;
    public static int screenW,screenH;
    /*public GameView gameView=null;*/
    public Bitmap[] test=new Bitmap[13];
    public Bitmap[] newTest=new Bitmap[13];
    public BitmapDrawable[] testDrawable=new BitmapDrawable[13];
    public  int y=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //得到屏幕大小  px为单位
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenW = dm.widthPixels;
        screenH = dm.heightPixels;

        final AbsoluteLayout startView =new AbsoluteLayout(this);//布局模式
        setContentView(startView);//启动绝对布局模式


        Resources resources=getResources();//资源
        startView.setBackgroundResource(R.drawable.desk);//设置背景
        //防止图片�