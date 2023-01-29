package com.example.keepfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.example.keepfitness.models.Cartitem;

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

public class OwnDietActivity extends AppCompatActivity {

    AnyChartView chart;

    private Button btn_return;
    private Button btn_search;

    private List<Cartitem> arrayList;
    public JSONObject JO;
    public static TextView cal,pro,car,fat,fiber,cholesterol;
    int int_pro,int_car,int_fat;

    String see="";
    String calories_parse, protein_parse, fat_parse, carbohydrates_parse, dietary_fiber_parse, cholesterol_parse;
    double icaloriesparse,iproteinparse,ifatparse,icarbohydratesparse,idietary_fiberparse,icholesterolparse;
    GlobalVariable sharedData = GlobalVariable.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_diet);
        chart=findViewById(R.id.chart);
        cal=findViewById(R.id.cal);
        pro=findViewById(R.id.pro);
        car=findViewById(R.id.car);
        fat=findViewById(R.id.fat);
        fiber=findViewById(R.id.fiber);
        cholesterol=findViewById(R.id.cholesterol);

        int [] num1 =new int[10];
        String [] food = {"木瓜","木瓜","木瓜","木瓜","木瓜","木瓜","木瓜","木瓜","木瓜","木瓜"};
        String [] num2 = {"0","0","0","0","0","0","0","0","0","0"};

        int i=0;
        arrayList = sharedData.getGlobalArrayList();
        for (Cartitem Item : arrayList) {
            food[i]=Item.getProduct().getName();
            num1[i]=Item.getQuantity();
            num2[i]=Integer.toString(num1[i]);
            i++;
        }
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String fi_url = "http://54.196.201.140/keepfitness/food_information.php";
                try {
                    URL url = new URL(fi_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("see", "UTF-8") + "=" + URLEncoder.encode(see, "UTF-8") + "&" +
                            URLEncoder.encode("food1", "UTF-8")+"="+URLEncoder.encode(food[0], "UTF-8") + "&" +
                            URLEncoder.encode("times1", "UTF-8")+"="+URLEncoder.encode(num2[0], "UTF-8") + "&" +
                            URLEncoder.encode("food2", "UTF-8")+"="+URLEncoder.encode(food[1], "UTF-8") + "&" +
                            URLEncoder.encode("times2", "UTF-8")+"="+URLEncoder.encode(num2[1], "UTF-8") + "&" +
                            URLEncoder.encode("food3", "UTF-8")+"="+URLEncoder.encode(food[2], "UTF-8") + "&" +
                            URLEncoder.encode("times3", "UTF-8")+"="+URLEncoder.encode(num2[2], "UTF-8") + "&" +
                            URLEncoder.encode("food4", "UTF-8")+"="+URLEncoder.encode(food[3], "UTF-8") + "&" +
                            URLEncoder.encode("times4", "UTF-8")+"="+URLEncoder.encode(num2[3], "UTF-8") + "&" +
                            URLEncoder.encode("food5", "UTF-8")+"="+URLEncoder.encode(food[4], "UTF-8") + "&" +
                            URLEncoder.encode("times5", "UTF-8")+"="+URLEncoder.encode(num2[4], "UTF-8") + "&" +
                            URLEncoder.encode("food6", "UTF-8")+"="+URLEncoder.encode(food[5], "UTF-8") + "&" +
                            URLEncoder.encode("times6", "UTF-8")+"="+URLEncoder.encode(num2[5], "UTF-8") + "&" +
                            URLEncoder.encode("food7", "UTF-8")+"="+URLEncoder.encode(food[6], "UTF-8") + "&" +
                            URLEncoder.encode("times7", "UTF-8")+"="+URLEncoder.encode(num2[6], "UTF-8") + "&" +
                            URLEncoder.encode("food8", "UTF-8")+"="+URLEncoder.encode(food[7], "UTF-8") + "&" +
                            URLEncoder.encode("times8", "UTF-8")+"="+URLEncoder.encode(num2[7], "UTF-8") + "&" +
                            URLEncoder.encode("food9", "UTF-8")+"="+URLEncoder.encode(food[8], "UTF-8") + "&" +
                            URLEncoder.encode("times9", "UTF-8")+"="+URLEncoder.encode(num2[8], "UTF-8") + "&" +
                            URLEncoder.encode("food10", "UTF-8")+"="+URLEncoder.encode(food[9], "UTF-8") + "&" +
                            URLEncoder.encode("times10", "UTF-8")+"="+URLEncoder.encode(num2[9], "UTF-8");
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
                    JO =new JSONObject(date);
                    calories_parse = (String) JO.get("calories").toString();
                    protein_parse = (String) JO.get("protein").toString();
                    carbohydrates_parse = (String) JO.get("carbohydrates").toString();
                    fat_parse = (String) JO.get("fat").toString();
                    dietary_fiber_parse = (String) JO.get("carbohydrates").toString();
                    cholesterol_parse = (String) JO.get("cholesterol").toString();
                    icaloriesparse =Double.valueOf(calories_parse);
                    iproteinparse = Double.valueOf(protein_parse);
                    ifatparse = Double.valueOf(fat_parse);
                    icarbohydratesparse = Double.valueOf(carbohydrates_parse);
                    idietary_fiberparse = Double.valueOf(dietary_fiber_parse);
                    icholesterolparse = Double.valueOf(cholesterol_parse);
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
                calories_parse = String.valueOf("熱量: "+icaloriesparse+"kcal/100g");
                protein_parse = String.valueOf("蛋白質: "+iproteinparse+"g/100g");
                fat_parse = String.valueOf("碳水化合物: "+ifatparse+"g/100g");
                carbohydrates_parse = String.valueOf("脂肪: "+icarbohydratesparse+"g/100g");
                dietary_fiber_parse = String.valueOf("膳食纖維: "+idietary_fiberparse+"g/100g");
                cholesterol_parse = String.valueOf("膽固醇: "+icholesterolparse+"g/100g");
                //int_pro=Integer.parseInt(protein_parse);
                //int_car=Integer.parseInt(carbohydrates_parse);
                //int_fat=Integer.parseInt(fat_parse);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        OwnDietActivity.cal.setText(calories_parse);
                        OwnDietActivity.pro.setText(protein_parse);
                        OwnDietActivity.car.setText(carbohydrates_parse);
                        OwnDietActivity.fat.setText(fat_parse);
                        OwnDietActivity.fiber.setText(dietary_fiber_parse);
                        OwnDietActivity.cholesterol.setText(cholesterol_parse);
                        Pie pie= AnyChart.pie();
                        List<DataEntry> dataEntries = new ArrayList<>();
                        dataEntries.add(new ValueDataEntry("蛋白質",iproteinparse));
                        dataEntries.add(new ValueDataEntry("碳水化合物",icarbohydratesparse));
                        dataEntries.add(new ValueDataEntry("脂肪",ifatparse));
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
        btn_return=findViewById(R.id.btn_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnDietActivity.this,TypeActivity.class);
                startActivity(intent);
            }
        });
    }
}