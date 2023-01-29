package com.example.keepfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChooseActivity extends AppCompatActivity {
    private Button workoutbutton;
    private Button dietbutton;
    private Button bodyconditionbutton;
    private TextView editdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        workoutbutton= (Button) findViewById(R.id.workout);
        workoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //跳轉到健身菜單
                Intent intent= new Intent(ChooseActivity.this,workoutmenu.class);
                startActivity(intent);
            }
        });

        dietbutton= (Button) findViewById(R.id.diet);
        dietbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //跳轉到飲食菜單
                Intent intent= new Intent(ChooseActivity.this,DietActivity.class);
                startActivity(intent);
            }
        });

        bodyconditionbutton= (Button) findViewById(R.id.bodycondition);
        bodyconditionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //跳轉到身體狀況評估表
                Intent intent= new Intent(ChooseActivity.this,bodycondition.class);
                startActivity(intent);
            }
        });

        editdata= (TextView) findViewById(R.id.edit);
        editdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //跳到修改個人資料
                Intent intent= new Intent(ChooseActivity.this,EditData.class);
                startActivity(intent);
            }
        });
    }
}