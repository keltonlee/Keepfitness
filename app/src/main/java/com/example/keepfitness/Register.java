package com.example.keepfitness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.widget.Toast;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Register extends Activity{
    EditText enterName,enteremail,enterage,enterheight,enterweight,enterbodyfat;
    String user_name,user_email,user_sex,user_age,user_height,user_weight,user_body_fat;
    Button returnbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaldata);
        enterName = findViewById(R.id.Name);
        enteremail=findViewById(R.id.email);
        enterage=findViewById(R.id.age);
        enterheight=findViewById(R.id.height);
        enterweight=findViewById(R.id.weight);
        enterbodyfat=findViewById(R.id.body_fat);
        returnbtn=findViewById(R.id.returnbtn);
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳轉到個人資料畫面
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void userReg(View view){
        RadioGroup rg = findViewById(R.id.sex);
        switch(rg.getCheckedRadioButtonId()){
            case R.id.male:
                user_sex="男";
                break;
            case R.id.female:
                user_sex="女";
                break;
        }
        user_name=enterName.getText().toString();
        user_email=enteremail.getText().toString();
        user_age=enterage.getText().toString();
        user_height=enterheight.getText().toString();
        user_weight=enterweight.getText().toString();
        user_body_fat=enterbodyfat.getText().toString();
        //String method ="register";
        //BackgroundTask backgroundTask =new BackgroundTask(this);
        //backgroundTask.execute(method,user_name,user_email,user_sex,user_age,user_height,user_weight,user_body_fat);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String response = "";
            @Override
            public void run() {
                String reg_url = "http://54.196.201.140/keepfitness/inserting.php";
                String Name = user_name;
                String email = user_email;
                String sex = user_sex;
                String age = user_age;
                String height = user_height;
                String weight = user_weight;
                String body_fat = user_body_fat;
                Calendar calendar=Calendar.getInstance();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-yyyy");
                String datetime = simpleDateFormat.format(calendar.getTime());
                try {
                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("Name", "UTF-8")+"="+URLEncoder.encode(Name, "UTF-8") + "&" +
                            URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8") + "&" +
                            URLEncoder.encode("sex", "UTF-8")+"="+URLEncoder.encode(sex, "UTF-8") + "&" +
                            URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8") + "&" +
                            URLEncoder.encode("height", "UTF-8") + "=" + URLEncoder.encode(height, "UTF-8") + "&" +
                            URLEncoder.encode("weight", "UTF-8") + "=" + URLEncoder.encode(weight, "UTF-8") + "&" +
                            URLEncoder.encode("body_fat", "UTF-8") + "=" + URLEncoder.encode(body_fat, "UTF-8") + "&" +
                            URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(datetime, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
                    String responses = "";
                    String line ;
                    while ((line = bufferedReader.readLine())!=null)
                    {
                        responses+= line;
                    }
                    System.out.println(responses);
                    bufferedReader.close();
                    IS.close();
                    httpURLConnection.disconnect();
                    response =  responses;
                    httpURLConnection.connect();
                    httpURLConnection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(response .equals("Register Success")){
                            Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Register.this,MainActivity.class));
                        }
                        else{
                            Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Register.this,Register.class));
                        }
                    }
                });
            }
        });
        finish();
    }
}
