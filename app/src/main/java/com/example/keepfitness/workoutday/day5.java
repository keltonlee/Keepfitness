package com.example.keepfitness.workoutday;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.keepfitness.Globalclass;
import com.example.keepfitness.R;
import com.example.keepfitness.descriptionday.description5;
import com.example.keepfitness.workoutmenu;
import org.json.JSONArray;
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

public class day5 extends AppCompatActivity {
    private TextView description5button;
    private Button returnbutton;
    public static int[] item = {R.id.item5_1, R.id.item5_2, R.id.item5_3, R.id.item5_4, R.id.item5_5, R.id.item5_6,R.id.item5_7} ;
    public static int[] group = {R.id.gn1, R.id.gn2, R.id.gn3, R.id.gn4, R.id.gn5, R.id.gn6,R.id.gn7};
    public JSONArray JA;
    String[] itemparse,groupparse;
    TextView tvitem,tvgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day5);
        String Name = ((Globalclass) this.getApplication()).getName();
        String email = ((Globalclass) this.getApplication()).getEmail();
        String addurl = ((Globalclass) this.getApplication()).getUrl();
        description5button=findViewById(R.id.des5);
        description5button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳轉到解說畫面
                Intent intent = new Intent(day5.this, description5.class);
                startActivity(intent);
            }
        });
        returnbutton=findViewById(R.id.returnbtn);
        returnbutton.setOnClickListener(v -> {
            //返回到健身菜單畫面
            Intent intent = new Intent(day5.this, workoutmenu.class);
            startActivity(intent);
        });
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            //String response = "";

            @Override
            public void run() {
                String fri_url = "http://"+addurl+"/keepfitness/friday.php";
                try {
                    URL url = new URL(fri_url);
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
                    JA = new JSONArray(date);
                    itemparse = new String[JA.length()];
                    groupparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JSONObject JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("item");
                        groupparse[i] = JO.getString("group_num");
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    //response = "Eva..";
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
                        for (int i = 0; i < JA.length(); i++) {
                            tvitem=findViewById(item[i]);
                            tvgroup=findViewById(group[i]);
                            tvitem.setText(itemparse[i]);
                            tvgroup.setText(groupparse[i]);
                        }
                    }
                });
            }
        });
        //finish();
    }
}