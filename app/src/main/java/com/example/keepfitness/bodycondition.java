package com.example.keepfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class bodycondition extends AppCompatActivity {
    private Button return_button;
    public static TextView BMI,height,weight,body_fat,level;
    String str_BMI,str_height,str_weight,str_body_fat,str_level;
    String  BMI_parse="",weight_parse="",height_parse="",body_fat_parse="",fit_intensity_parse="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodycondition);
        BMI=findViewById(R.id.BMI);
        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        body_fat=findViewById(R.id.body_fat);
        level=findViewById(R.id.level);
        return_button=findViewById(R.id.returnbtn);
        return_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //返回到選擇畫面
                Intent intent= new Intent(bodycondition.this,ChooseActivity.class);
                startActivity(intent);
            }
        });
        str_BMI=BMI.getText().toString();
        str_height=height.getText().toString();
        str_weight=weight.getText().toString();
        str_body_fat=body_fat.getText().toString();
        str_level=level.getText().toString();
        String Name =((Globalclass)this.getApplication()).getName();
        String email=((Globalclass)this.getApplication()).getEmail();
        String addurl = ((Globalclass) this.getApplication()).getUrl();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String response = "";

            @Override
            public void run() {
                String eva_url = "http://"+addurl+"/keepfitness/evaluate.php";
                try {
                    URL url = new URL(eva_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(Name, "UTF-8") + "&" +
                            URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    String line;
                    String date = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        date += line;
                    }
                    JSONObject JO = new JSONObject(date);
                    BMI_parse = (String) JO.get("BMI");
                    weight_parse = (String) JO.get("weight");
                    height_parse = (String) JO.get("height");
                    body_fat_parse = (String) JO.get("body_fat");
                    fit_intensity_parse = (String) JO.get("fit_intensity");
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    response = "Eva..";
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.equals("Eva..")) {
                            bodycondition.BMI.setText(BMI_parse);
                            bodycondition.height.setText(height_parse);
                            bodycondition.weight.setText(weight_parse);
                            bodycondition.body_fat.setText(body_fat_parse);
                            bodycondition.level.setText(fit_intensity_parse);
                        }

                    }
                });
            }
        });
    }
}