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

public class day6 extends AppCompatActivity {
    private Button returnbutton;
    private VideoView videoview1;
    private VideoView videoview2;
    private VideoView videoview3;
    private VideoView videoview4;
    private VideoView videoview5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day6);

        videoview1=findViewById(R.id.day6video1);
        String videoPath1="android.resource://"+getPackageName()+"/"+R.raw.video5;
        Uri uri1= Uri.parse(videoPath1);
        videoview1.setVideoURI(uri1);
        videoview1.start(); //自動撥放

        //持續播放
        videoview1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });

        videoview2=findViewById(R.id.day6video2);
        String videoPath2="android.resource://"+getPackageName()+"/"+R.raw.video6;
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

        videoview3=findViewById(R.id.day6video3);
        String videoPath3="android.resource://"+getPackageName()+"/"+R.raw.video7;
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

        videoview4=findViewById(R.id.day6video4);
        String videoPath4="android.resource://"+getPackageName()+"/"+R.raw.video8;
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

        videoview5=findViewById(R.id.day6video5);
        String videoPath5="android.resource://"+getPackageName()+"/"+R.raw.video9;
        Uri uri5= Uri.parse(videoPath5);
        videoview5.setVideoURI(uri5);
        videoview5.start(); //自動撥放

        //持續播放
        videoview5.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
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
                Intent intent = new Intent(day6.this, workoutmenu.class);
                startActivity(intent);
            }
        });
    }
}