package com.example.denemeler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import static android.os.SystemClock.sleep;

public class MyProgressBar extends AppCompatActivity {
    private String username;
    ProgressBar pb;
    int counter=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        Intent i=getIntent();
        username=i.getStringExtra("username");



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                prog();
            }
        }, 10000);
        Intent mainPage = new Intent(getApplicationContext(),MainPage.class);
        mainPage.putExtra("username",username);
        startActivity(mainPage);
    }
    public void prog(){
        pb=findViewById(R.id.pb);
        final Timer t=new Timer();
        TimerTask tt=new TimerTask(){
            @Override
            public void run() {
                counter++;
                pb.setProgress(counter);
                if(counter==100)
                    t.cancel();
            }
        };
        t.schedule(tt,0,100);
    }
}
