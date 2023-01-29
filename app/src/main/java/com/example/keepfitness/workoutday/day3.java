package com.example.keepfitness.workoutday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.example.keepfitness.R;
import com.example.keepfitness.workoutmenu;

public class day3 extends AppCompatActivity {
    private Button returnbutton;
    private VideoView videoview1;
    private VideoView videoview2;
    private VideoView videoview3;
    private VideoView videoview4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day3);

        videoview1=findViewById(R.id.day3video1);
        String videoPath1="android.resource://"+getPackageName()+"/"+R.raw.video1;
        Uri uri1= Uri.parse(videoPath1);
        videoview1.setVideoURI(uri1);
        videoview1.start(); //自動撥放

        //控制條 應該是用不到 嗎
        //MediaController mediaController=new MediaController(this);
        //videoview1.setMediaController(mediaController);
        //mediaController.setAnchorView(videoview1);

        //持續播放
        videoview1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });

        videoview2=findViewById(R.id.day3video2);
        String videoPath2="android.resource://"+getPackageName()+"/"+R.raw.video2;
        Uri uri2= Uri.parse(videoPath2);
        videoview2.setVideoURI(uri2);
        videoview2.start(); //自動撥放

        //持續播放
        videoview2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });

        videoview3=findViewById(R.id.day3video3);
        String videoPath3="android.resource://"+getPackageName()+"/"+R.raw.video3;
        Uri uri3= Uri.parse(videoPath3);
        videoview3.setVideoURI(uri3);
        videoview3.start(); //自動撥放

        //持續播放
        videoview3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });

        videoview4=findViewById(R.id.day3video4);
        String videoPath4="android.resource://"+getPackageName()+"/"+R.raw.video4;
        Uri uri4= Uri.parse(videoPath4);
        videoview4.setVideoURI(uri4);
        videoview4.start(); //自動撥放

        //持續播放
        videoview4.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });


        returnbutton = (Button) findViewById(R.id.returnbtn);
        returnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回到健身菜單畫面
                Intent intent = new Intent(day3.this, workoutmenu.class);
                startActivity(intent);
            }
        });
    }
}