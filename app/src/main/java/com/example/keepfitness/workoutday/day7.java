package com.example.keepfitness.workoutday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.keepfitness.R;
import com.example.keepfitness.descriptionday.description1;
import com.example.keepfitness.workoutmenu;

public class day7 extends AppCompatActivity {
    private TextView description7button;
    private Button returnbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day7);

        returnbutton = (Button) findViewById(R.id.returnbtn);
        returnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回到健身菜單畫面
                Intent intent = new Intent(day7.this, workoutmenu.class);
                startActivity(intent);
            }
        });
    }
}