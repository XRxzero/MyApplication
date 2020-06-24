package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class SetActivity extends AppCompatActivity {

    private int currentVolume,maxVolume;
    public boolean bgset=false;
    public int backTo=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_set);

        Intent getIntent=getIntent();
        bgset=getIntent.getBooleanExtra("change",false);
        backTo=getIntent.getIntExtra("backTo",1);

        //获取需要修改的字体所在的textView
        TextView setView=(TextView)findViewById(R.id.setText);
        TextView voiceView=(TextView)findViewById(R.id.voice);
        TextView backGView=(TextView)findViewById(R.id.background);

        //用导入的ttf文件来确定自己所要设计的字体
        Typeface typeface=Typeface.createFromAsset(getAssets(),"mytext.ttf");

        //调用函数导入自己需要的字体
        setView.setTypeface(typeface);
        voiceView.setTypeface(typeface);
        backGView.setTypeface(typeface);

        //通过seekbar来设置音量条并且显示数字
         ImageButton setVoice;//一键静音（没做

         MediaPlayer mediaPlayer01= new MediaPlayer();

         final AudioManager audiomanage;
         final TextView mVolume ;  //显示当前音量
         final SeekBar voiceBar;




        voiceBar=(SeekBar)findViewById(R.id.voiceSet) ;
        mVolume=(TextView)findViewById(R.id.voiceNum);
        audiomanage=(AudioManager)getSystemService(Context.AUDIO_SERVICE);

        maxVolume=audiomanage.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//获取最大音量
        voiceBar.setMax(maxVolume);//最大值
        currentVolume=audiomanage.getStreamVolume(AudioManager.STREAM_MUSIC);//获取当前音量
        voiceBar.setProgress(currentVolume);//当前值
        mVolume.setText(currentVolume*100/maxVolume+"%");//显示数值

        voiceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audiomanage.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
                currentVolume=audiomanage.getStreamVolume(AudioManager.STREAM_MUSIC);
                seekBar.setProgress(currentVolume);
                mVolume.setText(currentVolume*100/maxVolume+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        final ImageButton bg1=(ImageButton)findViewById(R.id.s_bg1);
        final ImageButton bg2=(ImageButton)findViewById(R.id.s_bg2);
        final ImageView check1=(ImageView)findViewById(R.id.imageView3);
        final ImageView check2=(ImageView)findViewById(R.id.imageView4);

        if(bgset==false)
        check2.setVisibility(View.INVISIBLE);//初始化为选择了第一张背景
        else check1.setVisibility(View.INVISIBLE);
        bg1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(bgset!=false) {
                    check1.setVisibility(View.VISIBLE);
                    check2.setVisibility(View.INVISIBLE);
                    bgset = false;
                }
            }
        });
        bg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(bgset!=true){
                    check2.setVisibility(View.VISIBLE);
                    check1.setVisibility(View.INVISIBLE);
                    bgset=true;
                }
            }
        });

        ImageButton OkBtn=(ImageButton)findViewById(R.id.ok);
        OkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(backTo==1) {
                    Intent back = new Intent(SetActivity.this, MainActivity.class);
                    back.putExtra("change", bgset);
                    startActivity(back);
                    finish();
                }
                else {
                    Intent back=new Intent();
                    back.putExtra("change",bgset);
                    setResult(RESULT_OK,back);
                    finish();
                }
            }

        });


    }

}
