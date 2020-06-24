package com.example.test.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;

import com.example.test.R;
import com.example.test.utils.ResizeImage;

public class GameView extends View implements Runnable{
    private Paint mpaint=null;
    public Context context;
    //用于牌的创建
    public ImageButton[] btn=new ImageButton[13];
    public Bitmap[] cardShow1=new Bitmap[13];
    public Bitmap[] cardShow2=new Bitmap[13];
    public Bitmap[] cardShow3=new Bitmap[13];


    public Bitmap PlayerRole =null;
    public Bitmap[] AiRole=new Bitmap[3];

    public static int screenH ,screenW;

    public GameView(int roleid,Context context,int W,int H){
        super(context);

        screenH=H;
        screenW=W;

        this.context=context;
        Resources resource=getResources();
        for(int i=0;i<13;i++)
            cardShow1[i] = ResizeImage.resizeImage(R.drawable.back, resource, 6*screenW, 4*screenH);
        for(int i=0;i<13;i++)
            cardShow2[i] = ResizeImage.resizeImage(R.drawable.back, resource, 6*screenW, 4*screenH);
        for(int i=0;i<13;i++)
            cardShow3[i] = ResizeImage.resizeImage(R.drawable.back, resource, 6*screenW, 4*screenH);

        switch(roleid){
            case 0:
                PlayerRole=ResizeImage.resizeImage(R.drawable.amia,resource,screenW,screenH);
                AiRole[0]=ResizeImage.resizeImage(R.drawable.blue,resource,screenW,screenH);
                AiRole[1]=ResizeImage.resizeImage(R.drawable.ifrit,resource,screenW,screenH);
                AiRole[2]=ResizeImage.resizeImage(R.drawable.sliver,resource,screenW,screenH);
                break;
            case 1:
                PlayerRole=ResizeImage.resizeImage(R.drawable.blue,resource,screenW,screenH);
                AiRole[0]=ResizeImage.resizeImage(R.drawable.amia,resource,screenW,screenH);
                AiRole[1]=ResizeImage.resizeImage(R.drawable.ifrit,resource,screenW,screenH);
                AiRole[2]=ResizeImage.resizeImage(R.drawable.sliver,resource,screenW,screenH);
                break;
            case 2:
                PlayerRole=ResizeImage.resizeImage(R.drawable.ifrit,resource,screenW,screenH);
                AiRole[0]=ResizeImage.resizeImage(R.drawable.blue,resource,screenW,screenH);
                AiRole[1]=ResizeImage.resizeImage(R.drawable.amia,resource,screenW,screenH);
                AiRole[2]=ResizeImage.resizeImage(R.drawable.sliver,resource,screenW,screenH);
                break;
            case 3:
                PlayerRole=ResizeImage.resizeImage(R.drawable.sliver,resource,screenW,screenH);
                AiRole[0]=ResizeImage.resizeImage(R.drawable.blue,resource,screenW,screenH);
                AiRole[1]=ResizeImage.resizeImage(R.drawable.ifrit,resource,screenW,screenH);
                AiRole[2]=ResizeImage.resizeImage(R.drawable.amia,resource,screenW,screenH);
                break;
        }

        mpaint=new Paint();
        mpaint.setTextSize(15*screenH/480f);
        new Thread(this).start();
    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        for(int i=0;i<13;i++){
            canvas.drawBitmap(cardShow1[i],250*screenW/1920,(300+7*20)*screenH/1080,null);
        }
        for(int i=0;i<13;i++){
            canvas.drawBitmap(cardShow2[i],(750+30*7)*screenW/1920,50*screenH/1080,null);
        }
        for(int i=0;i<13;i++){
            canvas.drawBitmap(cardShow3[i],1500*screenW/1920,(300+7*20)*screenH/1080,null);
        }

        canvas.drawBitmap(AiRole[0],450*screenW/1920,0,null);
        canvas.drawBitmap(AiRole[1],0,500*screenH/1920,null);
        canvas.drawBitmap(PlayerRole,200*screenW/1920,800*screenH/1080,null);
        canvas.drawBitmap(AiRole[2],1700*screenW/1920,500*screenH/1080,null);
    }

    public void run(){
        while (!Thread.currentThread().isInterrupted()) {
            try{
                Thread.sleep(100);

            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


}