package com.example.keepfitness.descriptionday;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.keepfitness.R;
import com.example.keepfitness.workoutday.day1;
import android.widget.TextView;
import com.example.keepfitness.Globalclass;
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

public class description1 extends AppCompatActivity {
    private Button returnbutton;
    public static int[] item={R.id.item11,R.id.item22,R.id.item33,R.id.item44,R.id.item55,R.id.item66};
    public static int[] body={R.id.bodypart1,R.id.bodypart2,R.id.bodypart3,R.id.bodypart4,R.id.bodypart5,R.id.bodypart6};
    public static int[] comment={R.id.comment1,R.id.comment2,R.id.comment3,R.id.comment4,R.id.comment5,R.id.comment6};
    public JSONArray JA;
    String [] itemparse,commemetparse,bodyparse;
    TextView tvitem,tvbody,tvcom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description1);
        String Name =((Globalclass)this.getApplication()).getName();
        String email=((Globalclass)this.getApplication()).getEmail();
        String addurl = ((Globalclass) this.getApplication()).getUrl();
        returnbutton = findViewById(R.id.returnbtn);
        returnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回到健身菜單畫面
                Intent intent = new Intent(description1.this, day1.class);
                startActivity(intent);
           }
        });
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            //String response = "";
            @Override
            public void run() {
                String mon_dt_url = "http://"+addurl+"/keepfitness/mondaydetail.php";
                try {
                    URL url = new URL(mon_dt_url);
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
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                    String line;
                    String date="";
                    while ((line = bufferedReader.readLine())!=null)
                    {
                        date += line;
                    }
                    JA =new JSONArray(date);
                    itemparse=new String[JA.length()];
                    commemetparse=new String[JA.length()];
                    bodyparse=new String[JA.length()];
                    for(int i=0;i< JA.length();i++){
                        JSONObject JO = (JSONObject)JA.get(i);
                        itemparse[i]=JO.getString("item");
                        bodyparse[i]= JO.getString("bodypart");
                        commemetparse[i]= JO.getString("comment");
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
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
                        for(int i=0;i< JA.length();i++){
                            tvitem = findViewById(item[i]);
                            tvbody = findViewById(body[i]);
                            tvcom = findViewById(comment[i]);
                            tvitem.setText(itemparse[i]);
                            tvbody.setText(bodyparse[i]);
                            tvcom.setText(commemetparse[i]);
                        }
                    }
                });
            }
        });
        //finish();
    }
}
