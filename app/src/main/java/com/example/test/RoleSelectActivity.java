package com.example.test;


import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class RoleSelectActivity extends AppCompatActivity {

    private ImageView[] check=new ImageView[4];
    private ImageButton[] role =new ImageButton[4];
    private ImageButton playgame,backTo;
    private Boolean[] isSelect=new Boolean[4];
    private boolean bgSet;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.role_select);

        LinearLayout roleSelectView=(LinearLayout) findViewById(R.id.role_selectview);
        setContentView(roleSelectView);

        playgame=(ImageButton)findViewById(R.id.ok1);
        backTo=(ImageButton)findViewById(R.id.back);

        playgame.setVisibility(View.INVISIBLE);
        Intent getIntent=getIntent();
        bgSet=getIntent.getBooleanExtra("change",false);
        setBackground(bgSet,roleSelectView);

        for(int i=0;i<4;i++){
            if(i==0) {
                check[i] = (ImageView) findViewById(R.id.check1);
                role[i] = (ImageButton) findViewById(R.id.role1);
            }
            else{
                check[i] = (ImageView) findViewById(R.id.check1+i);
                role[i] = (ImageButton) findViewById(R.id.role1+i);
            }
            isSelect[i]=false;
            check[i].setVisibility(View.INVISIBLE);

            role[i].setTag(i);
            role[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i=(Integer)view.getTag();
                    isSelect[i]=true;
                    check[i].setVisibility(View.VISIBLE);
                    playgame.setVisibility(View.VISIBLE);
                    for(int j=0;j<4;j++){
                        if(j!=i){
                            check[j].setVisibility(View.INVISIBLE);
                            isSelect[j]=false;
                        }
                    }
                }
            });
        }
        backTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back=new Intent(RoleSelectActivity.this,MainActivity.class);
                back.putExtra("change",bgSet);
                startActivity(back);
                finish();
            }
        });

        playgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start=new Intent(RoleSelectActivity.this,GameActivity.class);
                start.putExtra("change",bgSet);
                start.putExtra("role",getRole());
                startActivity(start);
                finish();
            }
        });
    }

        private int getRole(){
            int roleid=0;
            for(int i=0;i<4;i++){
                if(isSelect[i]==true){
                    roleid=i;
                    break;
                }
            }
            return roleid;
        }
        private  void setBackground(Boolean bg,LinearLayout view){
        if(bg==false) {
            Drawable background=getResources().getDrawable(R.drawable.desk);
            view.setBackground(background);
        }
        else {
            Drawable background=getResources().getDrawable(R.drawable.desk2);
            view.setBackground(background);
        }
    }
}
