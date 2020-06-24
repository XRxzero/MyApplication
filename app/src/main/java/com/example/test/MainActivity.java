package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private boolean bgSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent getIntent=getIntent();
        bgSet=getIntent.getBooleanExtra("change",false);

        ImageButton button=(ImageButton)findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start=new Intent(MainActivity.this,RoleSelectActivity.class);
                start.putExtra("change",bgSet);
                startActivity(start);
                finish();
            }
        });
        ImageButton button2 =(ImageButton)findViewById(R.id.exit);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,musicService.class);
                stopService(intent);
                finish();
            }
        });

        ImageButton button3=(ImageButton)findViewById(R.id.set);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent set=new Intent(MainActivity.this,SetActivity.class);
                set.putExtra("change",bgSet);
                set.putExtra("backTo",1);
                startActivity(set);
                finish();
            }
        });
        Intent Musicintent=new Intent(this,musicService.class);
        startService(Musicintent);
    }
    /*@Override
    protected void onStop() {
        Intent intent = new Intent(this,musicService.class);
        stopService(intent);
        super.onStop();
    }*/

}

