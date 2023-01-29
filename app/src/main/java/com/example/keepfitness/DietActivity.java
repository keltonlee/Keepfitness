package com.example.keepfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DietActivity extends AppCompatActivity {

    AnyChartView chart;
    String pro="",car="",fat="";
    int int_pro,int_car,int_fat;
    private Button btn_return;
    private Button btn_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        chart=findViewById(R.id.chart);
        btn_return=findViewById(R.id.btn_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DietActivity.this,ChooseActivity.class);
                startActivity(intent);
            }
        });
        btn_search=findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DietActivity.this,TypeActivity.class);
                startActivity(intent);
            }
        });
        String Name =((Globalclass)this.getApplication()).getName();

        String email=((Globalclass)this.getApplication()).getEmail();
        String addurl = ((Globalclass) this.getApplication()).getUrl();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String response = "";
            @Override
            public void run() {
                String eat_url = "http://"+addurl+"/keepfitness/eat_menu.php";
                try {
                    URL url = new URL(eat_url);
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
                    car = (String) JO.get("carbohydrates");
                    pro = (String) JO.get("protein");
                    fat = (String) JO.get("fat");
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    response = "Success";
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int_pro=Integer.parseInt(pro);
                int_car=Integer.parseInt(car);
                int_fat=Integer.parseInt(fat);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Pie pie= AnyChart.pie();
                        List<DataEntry> dataEntries = new ArrayList<>();
                        dataEntries.add(new ValueDataEntry("蛋白質",int_pro));
                        dataEntries.add(new ValueDataEntry("碳水化合物",int_car));
                        dataEntries.add(new ValueDataEntry("脂肪",int_fat));
                        pie.data(dataEntries);
                        pie.legend()
                                .position("center-bottom")
                                .itemsLayout(LegendLayout.HORIZONTAL)
                                .align(Align.CENTER);
                        chart.setChart(pie);
                    }
                });
            }
        });
    }
    /*public void setupPieChart(){
        Pie pie= AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();
        for (int i=0;i< items.length;i++){
            dataEntries.add(new ValueDataEntry(items[i],value[i]));
        }
        pie.data(dataEntries);
        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);
        chart.setChart(pie);
    }*/


}