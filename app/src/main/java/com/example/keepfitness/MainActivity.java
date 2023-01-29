package com.example.keepfitness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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

public class MainActivity extends Activity{
    EditText enterName,enterEmail;
    Button loginbutton;
    TextView register;
    String Name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((Globalclass)this.getApplication()).setUrl("54.196.201.140");
        enterName = findViewById(R.id.Name);
        enterEmail = findViewById(R.id.email);
        loginbutton = findViewById(R.id.loginbtn);
        register = findViewById(R.id.registerbtn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳轉到個人資料畫面
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enterName.getText().toString().isEmpty()){
                    enterName.setError("不能為空");
                }
                if(enterEmail.getText().toString().isEmpty()){
                    enterEmail.setError("不能為空");
                }
                else if(!enterName.getText().toString().isEmpty()&&!enterEmail.getText().toString().isEmpty()){
                    //跳轉到個人資料畫面
                    userLogin(v);
                    //Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
                    //startActivity(intent);
                }
            }
        });

    }
    public void userReg(View view)
    {
        startActivity(new Intent(this,Register.class));
    }
    public void userLogin(View view)
    {
        Name = enterName.getText().toString();
        email = enterEmail.getText().toString();
        ((Globalclass)this.getApplication()).setName(Name);
        ((Globalclass)this.getApplication()).setEmail(email);
        String addurl = ((Globalclass) this.getApplication()).getUrl();
        //String method = "login";
        //BackgroundTask backgroundTask = new BackgroundTask(this);
        //backgroundTask.execute(method,login_name,login_email);
        //startActivity(new Intent(this,bodypage.class));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String response = "";
            @Override
            public void run() {
                try {
                    URL url = new URL("http://"+addurl+"/keepfitness/login.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String data = URLEncoder.encode("Name","UTF-8")+"="+URLEncoder.encode(Name,"UTF-8")+"&"+
                                  URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String responses = "";
                    String line ;
                    while ((line = bufferedReader.readLine())!=null)
                    {
                        responses+= line;
                    }
                    System.out.println(responses);
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    response =  responses;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(response .equals("Login Success")){
                            Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this,ChooseActivity.class));
                        }
                        else{
                            Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this,MainActivity.class));
                        }
                    }
                });
            }
        });
        finish();
    }
}