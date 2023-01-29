package com.example.keepfitness.workoutday;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.keepfitness.Globalclass;
import com.example.keepfitness.R;
import com.example.keepfitness.descriptionday.description4;
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

public class day4 extends AppCompatActivity {
    private TextView description4button;
    private Button returnbutton;
    public static int[] item = {R.id.item4_1, R.id.item4_2, R.id.item4_3, R.id.item4_4, R.id.item4_5, R.id.item4_6};
    public static int[] group = {R.id.gn1, R.id.gn2, R.id.gn3, R.id.gn4, R.id.gn5, R.id.gn6};
    public JSONArray JA;
    String[] itemparse,groupparse;
    TextView tvitem,tvgroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day4);
        String Name = ((Globalclass) this.getApplication()).getName();
        String email = ((Globalclass) this.getApplication()).getEmail();
        String addurl = ((Globalclass) this.getApplication()).getUrl();
        description4button=findViewById(R.id.des4);
        description4button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳轉到解說畫面
                Intent intent = new Intent(day4.this, description4.class);
                startActivity(intent);
            }
        });
        returnbutton=findViewById(R.id.returnbtn);
        returnbutton.setOnClickListener(v -> {
            //返回到健身菜單畫面
            Intent intent = new Intent(day4.this, workoutmenu.class);
            startActivity(intent);
        });
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            //String response = "";

            @Override
            public void run() {
                String thu_url = "http://"+addurl+"/keepfitness/thursday.php";
                try {
                    URL url = new URL(thu_url);
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