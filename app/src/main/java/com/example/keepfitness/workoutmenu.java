package com.example.keepfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

//import com.example.keepfitness.adapter.DataBean;
//import com.example.keepfitness.adapter.RecyclerAdapter;
import com.example.keepfitness.workoutday.day1;
import com.example.keepfitness.workoutday.day2;
import com.example.keepfitness.workoutday.day3;
import com.example.keepfitness.workoutday.day4;
import com.example.keepfitness.workoutday.day5;
import com.example.keepfitness.workoutday.day6;
import com.example.keepfitness.workoutday.day7;

import java.util.ArrayList;
import java.util.List;

public class workoutmenu extends AppCompatActivity {
    //private RecyclerView mRecyclerView;
    //private List<DataBean> dataBeanList;
    //private DataBean dataBean;
    //private RecyclerAdapter mAdapter;

    private Button day1button;
    private Button day2button;
    private Button day3button;
    private Button day4button;
    private Button day5button;
    private Button day6button;
    private Button day7button;
    private Button returnbutton;

    //private CheckBox checkbox1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workoutmenu);
        //舊的 mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        //舊的 initData();
        day1button = (Button) findViewById(R.id.day1btn);
        day1button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳轉到第一天項目
                Intent intent = new Intent(workoutmenu.this,day1.class);
                startActivity(intent);
            }
        });

        day2button = (Button) findViewById(R.id.day2btn);
        day2button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳轉到第二天項目
                Intent intent = new Intent(workoutmenu.this, day2.class);
                startActivity(intent);
            }
        });

        day3button = (Button) findViewById(R.id.day3btn);
        day3button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳轉到第三天有氧
                Intent intent = new Intent(workoutmenu.this, day3.class);
                startActivity(intent);
            }
        });

        day4button = (Button) findViewById(R.id.day4btn);
        day4button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳轉到第四天項目
                Intent intent = new Intent(workoutmenu.this, day4.class);
                startActivity(intent);
            }
        });

        day5button = (Button) findViewById(R.id.day5btn);
        day5button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳轉到第五天項目
                Intent intent = new Intent(workoutmenu.this, day5.class);
                startActivity(intent);
            }
        });

        day6button = (Button) findViewById(R.id.day6btn);
        day6button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳轉到第六天項目
                Intent intent = new Intent(workoutmenu.this, day6.class);
                startActivity(intent);
            }
        });

        day7button = (Button) findViewById(R.id.day7btn);
        day7button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳轉到第七天項目
                Intent intent = new Intent(workoutmenu.this, day7.class);
                startActivity(intent);
            }
        });

        returnbutton = (Button) findViewById(R.id.returnbtn);
        returnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回到選擇畫面
                Intent intent = new Intent(workoutmenu.this,ChooseActivity.class);
                startActivity(intent);
            }
        });

    }





/**從這邊開始 舊的
    private void initData(){
        dataBeanList = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            dataBean = new DataBean();
            dataBean.setID(i+"");
            dataBean.setType(0);
            dataBean.setParentLeftTxt("第"+i+"天");
            //if(i==1){
            //    descriptionbutton=(TextView)findViewById(R.id.descriptionbtn);
            //    descriptionbutton.setOnClickListener(new View.OnClickListener() {
            //        @Override
            //        public void onClick(View v) {
                        //跳轉到解說1畫面
            //            Intent intent = new Intent(workoutmenu.this, description.class);
            //            startActivity(intent);
            //        }
            //    });
            //}

            dataBean.setChildLeftTxt("內容"+i);
            dataBean.setChildBean(dataBean);
            dataBeanList.add(dataBean);
        }
        setData();
    }

    private void setData(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerAdapter(this,dataBeanList);
        mRecyclerView.setAdapter(mAdapter);
        //滾動監聽
        mAdapter.setOnScrollListener(new RecyclerAdapter.OnScrollListener() {
            @Override
            public void scrollTo(int pos) {
                mRecyclerView.scrollToPosition(pos);
            }
        });
    }
 */
}
