package com.example.test;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;

import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.example.test.View.GameView;
import com.example.test.model.Card;
import com.example.test.model.MainGame;
import com.example.test.model.Player;
import com.example.test.model.Rules;
import com.example.test.model.ShowCard;

import com.example.test.utils.createNewButton;


public class GameActivity extends AppCompatActivity {
    public ImageButton Take,Pass,Allset;
    private Button Back,Restart;
    public ImageButton[] Card=new ImageButton[13];

    public Bitmap takeBitmap,passBitmap,allsetBitmap;
    public Bitmap[] cardBitmap=new Bitmap[13];

    public ImageView[] shareCard=new ImageView[5];
    public ImageView[] shareCardleft=new ImageView[5];
    public ImageView[] shareCardmid=new ImageView[5];
    public ImageView[] shareCardright=new ImageView[5];
    private AbsoluteLayout gameover;
    public Drawable background;
    public boolean bgSet;
    public boolean[] isSelect=new boolean[13];

    public TextView[] pass=new TextView[3];
    public static int screenW,screenH;
    public int roleSelectID;
    public GameView gameView=null;
    private Player me=null;
    private Player[] robot =new Player[3];
    private MainGame mainGame=null;

    public  int[] testcard=new int[13];

    private int[] newcard=new int[13];
    private int[] newcardUi=new int[13];
    private createNewButton create=null;

    private void newGame(){
        for(int i=0;i<5;i++) {
            if(i==0) {
                shareCard[i] = (ImageView) findViewById(R.id.pshow1);
                shareCardleft[i]=(ImageView)findViewById(R.id.r1show1);
                shareCardmid[i]=(ImageView)findViewById(R.id.r2show1);
                shareCardright[i]=(ImageView)findViewById(R.id.r3show1);
            }
            else {
                shareCard[i]=(ImageView)findViewById(R.id.pshow1+i);
                shareCardleft[i]=(ImageView)findViewById(R.id.r1show1+i);
                shareCardmid[i]=(ImageView)findViewById(R.id.r2show1+i);
                shareCardright[i]=(ImageView)findViewById(R.id.r3show1+i);
            }
        }
        for(int i=0;i<13;i++){
            newcard[i]=i;
            newcardUi[i]=R.drawable.c1;
        }
        me=new Player(newcard,shareCard,newcardUi);
        robot[0]=new Player(newcard,shareCardright,newcardUi);
        robot[1]=new Player(newcard,shareCardmid,newcardUi);
        robot[2]=new Player(newcard,shareCardleft,newcardUi);
        mainGame=new MainGame(me,robot);
        for(int i=0;i<13;i++){
            Card[i]=new ImageButton(this);
            cardBitmap[i]=create.getBitmapImage(me.getHandcardUi()[i],3,2);
            Card[i]=create.setImageButton(Card[i],cardBitmap[i],400+i*70,750);
            isSelect[i]=false;
        }
        for(int i=0;i<=4;i++){
            shareCard[i].setVisibility(View.INVISIBLE);
            shareCardleft[i].setVisibility(View.INVISIBLE);
            shareCardmid[i].setVisibility(View.INVISIBLE);
            shareCardright[i].setVisibility(View.INVISIBLE);
        }
        gameover=(AbsoluteLayout)(findViewById(R.id.over));
        gameover.setVisibility(View.INVISIBLE);
        Back=(Button)findViewById(R.id.button2);
        Restart=(Button)findViewById(R.id.button);
        me.setImageButton(Card);
        me.setIsSelect(isSelect);
        for(int i=0;i<13;i++){
            testcard[i]=me.getHandcard()[i];
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);//调用xml
        Rules.deskcard[0]=0;
        Rules.deskcard[1]=0;
        Rules.passnum=3;
        int[] tmp=new int []{0,0};
        //得到屏幕大小  px为单位
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenW = dm.widthPixels;
        screenH = dm.heightPixels;
        Typeface typeface=Typeface.createFromAsset(getAssets(),"mytext.ttf");//记录自己的字体类型
        AbsoluteLayout startView=(AbsoluteLayout)findViewById(R.id.absolute_id);//记录绝对布局的id，方便后面添加控件
        final TextView leftAiNum=(TextView)findViewById(R.id.leftnum);
        final TextView rightAiNum=(TextView)findViewById(R.id.rightnum);
        final TextView midAiNum=(TextView)findViewById(R.id.midnum);
        pass[0]=(TextView)findViewById(R.id.pass1);
        pass[1]=(TextView)findViewById(R.id.pass2);
        pass[2]=(TextView)findViewById(R.id.pass3);

        leftAiNum.setTypeface(typeface);
        rightAiNum.setTypeface(typeface);
        midAiNum.setTypeface(typeface);
        pass[0].setTypeface(typeface);
        pass[1].setTypeface(typeface);
        pass[2].setTypeface(typeface);
        setContentView(startView);
        Resources resources=getResources();//这玩意必须放在onCreate里面，不然比报错，我R

        //改背景图片的操作
        Intent getIntent=getIntent();
        bgSet=getIntent.getBooleanExtra("change",false);
        changeBackGround(bgSet,startView);

        //创建一个对象来执行创建控件的操作
        create=new createNewButton(screenW,screenH,startView,this,resources);
        //防止图片拉伸（写了个工具类，resizeimage类里面有专门的，由于图片质量问题，emmm这个没办法缩放，我只能够改分辨率了
        Pass=new ImageButton(this);
        Take=new ImageButton(this);
        Allset=new ImageButton(this);
        allsetBitmap=create.getBitmapImage(R.drawable.timg,1,1);
        passBitmap= create.getBitmapImage(R.drawable.btnpass,2,2);
        takeBitmap= create.getBitmapImage(R.drawable.btntake,2,2);
        Pass=create.setImageButton(Pass,passBitmap,650,650);
        Take=create.setImageButton(Take,takeBitmap,1000,650);
        Allset=create.setImageButton(Allset,allsetBitmap,1600,0);
        //创建一副可以点击的牌，目前能够实现点击后上下移动（通过监听将他们的位置进行移动
        newGame();

        leftAiNum.setText(String.valueOf(robot[2].getHandcardnum()));
        rightAiNum.setText(String.valueOf(robot[0].getHandcardnum()));
        midAiNum.setText(String.valueOf(robot[1].getHandcardnum()));
        pass[0].setVisibility(View.INVISIBLE);
        pass[1].setVisibility(View.INVISIBLE);
        pass[2].setVisibility(View.INVISIBLE);
        robot[0].setPass(pass[0]);
        robot[1].setPass(pass[1]);
        robot[2].setPass(pass[2]);
            switch (MainGame.whoplay % 4) {//以方片3（whoplay初始值）为开始，轮流打牌
                case 0:
                    break;
                case 1:
                    robot[0].AiShowCard();
                    MainGame.whoplay++;
                    rightAiNum.setText(String.valueOf(robot[0].getHandcardnum()));
                    // 1号robot的回合
                case 2:
                    robot[1].AiShowCard();
                    MainGame.whoplay++;
                    midAiNum.setText(String.valueOf(robot[1].getHandcardnum()));
                    // 2号robot的回合
                case 3:
                    robot[2].AiShowCard();
                    MainGame.whoplay = 0;
                    leftAiNum.setText(String.valueOf(robot[2].getHandcardnum()));
                    // 3号robot的回合
            }


        for(int i=0;i<13;i++){
            Card[i].setTag(i);
            Card[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i=(Integer) view.getTag();
                    if(isSelect[i]==true){
                        Card[i].setTranslationY(0);
                        isSelect[i]=false;
                    }
                    else if(isSelect[i]==false){
                        Card[i].setTranslationY(-35);
                        isSelect[i]=true;
                    }
                }
            });
        }

        //这里是测试用的


//设置出牌键的监听 在出牌区本来就有5张牌就位，然后通过判断牌是否被选中来实现出牌，并且影藏已经出了的牌
        Take.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if (me.playcards(isSelect, Card) == true) {
                        MainGame.whoplay=1;
                        if (me.getHandcardnum() == 0) {
                            MainGame.whoplay = -2;
                            gameover.setVisibility(View.VISIBLE);
                            Take.setVisibility(View.INVISIBLE);
                            Pass.setVisibility(View.INVISIBLE);
                        }
                    switch (MainGame.whoplay % 4) {//以方片3（whoplay初始值）为开始，轮流打牌
                        case 0:
                            break;
                        case 1:
                            robot[0].AiShowCard();
                            rightAiNum.setText(String.valueOf(robot[0].getHandcardnum()));
                            if(robot[0].getHandcardnum()==0){
                                MainGame.whoplay=-1;

                                break;
                            }
                            MainGame.whoplay++;

                            // 1号robot的回合
                        case 2:
                            robot[1].AiShowCard();
                            midAiNum.setText(String.valueOf(robot[1].getHandcardnum()));
                            if(robot[1].getHandcardnum()==0){
                                MainGame.whoplay=-1;

                                break;
                            }
                            MainGame.whoplay++;

                            // 2号robot的回合
                        case 3:
                            robot[2].AiShowCard();
                            leftAiNum.setText(String.valueOf(robot[2].getHandcardnum()));
                            if(robot[2].getHandcardnum()==0){
                                MainGame.whoplay=-1;

                                break;
                            }
                            MainGame.whoplay = 0;

                            // 3号robot的回合
                        }
                    }

                if (MainGame.whoplay==-1) {
                    gameover.setVisibility(View.VISIBLE);
                    Take.setVisibility(View.INVISIBLE);
                    Pass.setVisibility(View.INVISIBLE);
                }

                for (int i = 0; i < 5; i++) {
                    shareCard[i].setVisibility(View.INVISIBLE);
                }

            }
        });
//pass的按钮，目前设定为把牌影藏（不确定后面的操作
        Pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<5;i++){
                    shareCard[i].setVisibility(View.INVISIBLE);//影藏操作
                }
                me.passClick=true;
                me.pass();
                MainGame.whoplay=1;
                    switch (MainGame.whoplay % 4) {//以方片3（whoplay初始值）为开始，轮流打牌
                        case 1:
                            robot[0].AiShowCard();
                            rightAiNum.setText(String.valueOf(robot[0].getHandcardnum()));
                            if(robot[0].getHandcardnum()==0){
                                MainGame.whoplay=-1;

                                break;
                            }

                            MainGame.whoplay++;// 1号robot的回合
                        case 2:
                            robot[1].AiShowCard();
                            midAiNum.setText(String.valueOf(robot[1].getHandcardnum()));
                            if(robot[1].getHandcardnum()==0){
                                MainGame.whoplay=-1;

                                break;
                            }

                            MainGame.whoplay++;// 2号robot的回合
                        case 3:
                            robot[2].AiShowCard();
                            leftAiNum.setText(String.valueOf(robot[2].getHandcardnum()));
                            if(robot[2].getHandcardnum()==0){
                                MainGame.whoplay=-1;

                                break;
                            }
                            MainGame.whoplay=0;// 3号robot的回合

                    }

                if(MainGame.whoplay==-1){
                    gameover.setVisibility(View.VISIBLE);
                    Take.setVisibility(View.INVISIBLE);
                    Pass.setVisibility(View.INVISIBLE);
                }

            }
        });

        Allset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMenuClick(v);
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GameActivity.this,MainActivity.class);
                intent.putExtra("change",bgSet);
                startActivity(intent);
                finish();
            }
        });
        Restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GameActivity.this,GameActivity.class);
                intent.putExtra("change",bgSet);
                intent.putExtra("backTo",2);
                intent.putExtra("role",roleSelectID);
                MainGame.whoplay=0;
                startActivity(intent);
                finish();
            }
        });
        roleSelectID=getIntent.getIntExtra("role",0);
        gameView=new GameView(roleSelectID,this,screenW,screenH);
        startView.addView(gameView,new AbsoluteLayout.LayoutParams(screenW,screenH,0,0));



    }

    //这个是为了更改背景，特意写的一个函数，写在onCreate里面没办法在外部调用
    public void changeBackGround(boolean bgSet,AbsoluteLayout view){

        if(bgSet==true){
            background=getResources().getDrawable(R.drawable.desk2);
            view.setBackground(background);
        }
        else {
            background=getResources().getDrawable(R.drawable.desk);
            view.setBackground(background);
        }
    }


    //如果要从上一个activity传参，得重写这个方法，然后返回相应的值
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK) {
                    bgSet=data.getBooleanExtra("change",false);
                    AbsoluteLayout view =(AbsoluteLayout) findViewById(R.id.absolute_id);
                    changeBackGround(bgSet,view);
                }
                break;
            default:
        }
    }

    public void onMenuClick(View view){
        PopupMenu popup = new PopupMenu(this, view);
        Menu menu=popup.getMenu();
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.set_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.back:
                        {
                        Intent intent=new Intent(GameActivity.this,MainActivity.class);
                        intent.putExtra("change",bgSet);
                        startActivity(intent);
                        finish();

                    }break;
                    case R.id.set:{
                        Intent intent=new Intent(GameActivity.this,SetActivity.class);
                        intent.putExtra("change",bgSet);
                        intent.putExtra("backTo",2);
                        startActivityForResult(intent,1);
                    }break;
                    case R.id.restart:{
                        Intent intent=new Intent(GameActivity.this,GameActivity.class);
                        intent.putExtra("change",bgSet);
                        intent.putExtra("backTo",2);
                        intent.putExtra("role",roleSelectID);
                        startActivity(intent);
                        finish();
                    }break;
                    default:return true;
                }
                return false;
            }
        });
        popup.show();
    }
}
