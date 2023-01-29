package com.example.keepfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EditData extends AppCompatActivity {
    private Button returnbutton;
    private Button editbutton;
    private EditText editAge;
    private EditText editHeight;
    private EditText editWeight;
    private EditText editBodyFat;
    private RadioGroup choosegender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        returnbutton= (Button) findViewById(R.id.returnbtn);
        returnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //返回到選擇
                Intent intent= new Intent(EditData.this, ChooseActivity.class);
                startActivity(intent);
            }
        });

        choosegender=(RadioGroup)findViewById(R.id.sex);
        editAge=(EditText) findViewById(R.id.age);
        editHeight=(EditText) findViewById(R.id.height);
        editWeight=(EditText) findViewById(R.id.weight);
        editBodyFat=(EditText) findViewById(R.id.body_fat);
        //boolean txt=editName.getText().toString().matches("");

        editbutton= (Button) findViewById(R.id.editbtn);
        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(editAge.getText().toString().isEmpty()){
                    editAge.setError("不能為空");
                }
                if(editHeight.getText().toString().isEmpty()){
                    editHeight.setError("不能為空");
                }
                if(editWeight.getText().toString().isEmpty()){
                    editWeight.setError("不能為空");
                }
                if(editBodyFat.getText().toString().isEmpty()){
                    editBodyFat.setError("不能為空");
                }
                else if(!editAge.getText().toString().isEmpty()&&
                        !editHeight.getText().toString().isEmpty()&&
                        !editWeight.getText().toString().isEmpty()&&
                        !editBodyFat.getText().toString().isEmpty()){
                    if (choosegender.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(EditData.this, "請選擇性別", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //跳轉到登入畫面重新登入
                        Intent intent = new Intent(EditData.this, ChooseActivity.class);
                        startActivity(intent);
                        Toast.makeText(EditData.this, "修改成功！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}