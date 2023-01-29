package com.example.keepfitness.repositories;

import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.example.keepfitness.GlobalVariable;
import com.example.keepfitness.MainActivity;
import com.example.keepfitness.models.Product;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ShopRepo  {

    private MutableLiveData<List<Product>> mutableProductList;
    GlobalVariable sharedData = GlobalVariable.getInstance();
    int pos = sharedData.getValue();

    public LiveData<List<Product>> getProducts(){
        if (mutableProductList == null){
            mutableProductList = new MutableLiveData<>();
            if (pos==0)   {loadMeat();}
            if (pos==1)   {loadStarch();}
            if (pos==2)   {loadMushroom();}
            if (pos==3)   {loadVegetable();}
            if (pos==4)   {loadCrops();}
            if (pos==5)   {loadSeaweed();}
            if (pos==6)   {loadEgg();}
            if (pos==7)   {loadBean();}
            if (pos==8)   {loadFish();}
            if (pos==9)   {loadMilk();}
            if (pos==10)   {loadFruit();}
            if (pos==11)   {loadDrink();}
            if (pos==12)   {loadSnack();}
            if (pos==13)   {loadSauce();}
            if (pos==14)   {loadNuts();}
            if (pos==15)   {loadIndustry();}
        }
        return mutableProductList;
    }

    public JSONArray JA;
    public JSONObject JO;
    String[] itemparse,calparse;
    public void loadMilk(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String type = "乳品類";
            @Override
            public void run() {
                String ft_url = "http://54.196.201.140/keepfitness/food_type.php";
                try {
                    URL url = new URL(ft_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    calparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("food");
                        calparse[i] = JO.getString("calories");
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = new ArrayList<>();
                        for(int i=0;i< JA.length();i++){
                            double d_calories = Double.parseDouble(calparse[i]);
                            productList.add(new Product(String.valueOf(i),itemparse[i],"乳品類",d_calories));
                        }//13
                        mutableProductList.setValue(productList);
                    }
                });
            }
         });
    }
    private void loadIndustry(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String type = "加工調理食品及其他類";
            @Override
            public void run() {
                String ft_url = "http://54.196.201.140/keepfitness/food_type.php";
                try {
                    URL url = new URL(ft_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    calparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("food");
                        calparse[i] = JO.getString("calories");
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = new ArrayList<>();
                        for(int i=0;i< JA.length();i++){
                            double d_calories = Double.parseDouble(calparse[i]);
                            productList.add(new Product(String.valueOf(i),itemparse[i],"加工調理食品及其他類",d_calories));
                        }//13
                        mutableProductList.setValue(productList);
                    }
                });
            }
        });
    }
    private void loadNuts(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String type = "堅果及種子類";
            @Override
            public void run() {
                String ft_url = "http://54.196.201.140/keepfitness/food_type.php";
                try {
                    URL url = new URL(ft_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    calparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("food");
                        calparse[i] = JO.getString("calories");
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = new ArrayList<>();
                        for(int i=0;i< JA.length();i++){
                            double d_calories = Double.parseDouble(calparse[i]);
                            productList.add(new Product(String.valueOf(i),itemparse[i],"堅果及種子類",d_calories));
                        }//13
                        mutableProductList.setValue(productList);
                    }
                });
            }
        });
    }
    private void loadFruit(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String type = "水果類";
            @Override
            public void run() {
                String ft_url = "http://54.196.201.140/keepfitness/food_type.php";
                try {
                    URL url = new URL(ft_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    calparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("food");
                        calparse[i] = JO.getString("calories");
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = new ArrayList<>();
                        for(int i=0;i< JA.length();i++){
                            double d_calories = Double.parseDouble(calparse[i]);
                            productList.add(new Product(String.valueOf(i),itemparse[i],"水果類",d_calories));
                        }//13
                        mutableProductList.setValue(productList);
                    }
                });
            }
        });
    }
    private void loadStarch(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String type = "澱粉類";
            @Override
            public void run() {
                String ft_url = "http://54.196.201.140/keepfitness/food_type.php";
                try {
                    URL url = new URL(ft_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    calparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("food");
                        calparse[i] = JO.getString("calories");
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = new ArrayList<>();
                        for(int i=0;i< JA.length();i++){
                            double d_calories = Double.parseDouble(calparse[i]);
                            productList.add(new Product(String.valueOf(i),itemparse[i],"澱粉類",d_calories));
                        }//13
                        mutableProductList.setValue(productList);
                    }
                });
            }
        });
    }
    private void loadCrops(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String type = "穀物類";
            @Override
            public void run() {
                String ft_url = "http://54.196.201.140/keepfitness/food_type.php";
                try {
                    URL url = new URL(ft_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    calparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("food");
                        calparse[i] = JO.getString("calories");
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = new ArrayList<>();
                        for(int i=0;i< JA.length();i++){
                            double d_calories = Double.parseDouble(calparse[i]);
                            productList.add(new Product(String.valueOf(i),itemparse[i],"穀物類",d_calories));
                        }//13
                        mutableProductList.setValue(productList);
                    }
                });
            }
        });
    }
    private void loadSnack(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String type = "糕餅點心類";
            @Override
            public void run() {
                String ft_url = "http://54.196.201.140/keepfitness/food_type.php";
                try {
                    URL url = new URL(ft_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    calparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("food");
                        calparse[i] = JO.getString("calories");
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = new ArrayList<>();
                        for(int i=0;i< JA.length();i++){
                            double d_calories = Double.parseDouble(calparse[i]);
                            productList.add(new Product(String.valueOf(i),itemparse[i],"糕餅點心類",d_calories));
                        }//13
                        mutableProductList.setValue(productList);
                    }
                });
            }
        });
    }
    private void loadMeat(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String type = "肉類";
            @Override
            public void run() {
                String ft_url = "http://54.196.201.140/keepfitness/food_type.php";
                try {
                    URL url = new URL(ft_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    calparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("food");
                        calparse[i] = JO.getString("calories");
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = new ArrayList<>();
                        for(int i=0;i< JA.length();i++){
                            double d_calories = Double.parseDouble(calparse[i]);
                            productList.add(new Product(String.valueOf(i),itemparse[i],"肉類",d_calories));
                        }//13
                        mutableProductList.setValue(productList);
                    }
                });
            }
        });
    }
    private void loadMushroom(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String type = "菇類";
            @Override
            public void run() {
                String ft_url = "http://54.196.201.140/keepfitness/food_type.php";
                try {
                    URL url = new URL(ft_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    calparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("food");
                        calparse[i] = JO.getString("calories");
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = new ArrayList<>();
                        for(int i=0;i< JA.length();i++){
                            double d_calories = Double.parseDouble(calparse[i]);
                            productList.add(new Product(String.valueOf(i),itemparse[i],"菇類",d_calories));
                        }//13
                        mutableProductList.setValue(productList);
                    }
                });
            }
        });
    }
    private void loadVegetable(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String type = "蔬菜類";
            @Override
            public void run() {
                String ft_url = "http://54.196.201.140/keepfitness/food_type.php";
                try {
                    URL url = new URL(ft_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    calparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("food");
                        calparse[i] = JO.getString("calories");
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = new ArrayList<>();
                        for(int i=0;i< JA.length();i++){
                            double d_calories = Double.parseDouble(calparse[i]);
                            productList.add(new Product(String.valueOf(i),itemparse[i],"蔬菜類",d_calories));
                        }//13
                        mutableProductList.setValue(productList);
                    }
                });
            }
        });
    }
    private void loadSeaweed(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String type = "藻類";
            @Override
            public void run() {
                String ft_url = "http://54.196.201.140/keepfitness/food_type.php";
                try {
                    URL url = new URL(ft_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    calparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("food");
                        calparse[i] = JO.getString("calories");
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = new ArrayList<>();
                        for(int i=0;i< JA.length();i++){
                            double d_calories = Double.parseDouble(calparse[i]);
                            productList.add(new Product(String.valueOf(i),itemparse[i],"藻類",d_calories));
                        }//13
                        mutableProductList.setValue(productList);
                    }
                });
            }
        });
    }
    private void loadEgg(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String type = "蛋類";
            @Override
            public void run() {
                String ft_url = "http://54.196.201.140/keepfitness/food_type.php";
                try {
                    URL url = new URL(ft_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    calparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("food");
                        calparse[i] = JO.getString("calories");
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = new ArrayList<>();
                        for(int i=0;i< JA.length();i++){
                            double d_calories = Double.parseDouble(calparse[i]);
                            productList.add(new Product(String.valueOf(i),itemparse[i],"蛋類",d_calories));
                        }//13
                        mutableProductList.setValue(productList);
                    }
                });
            }
        });
    }
    private void loadSauce(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String type = "調味料及香辛料類";
            @Override
            public void run() {
                String ft_url = "http://54.196.201.140/keepfitness/food_type.php";
                try {
                    URL url = new URL(ft_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    calparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("food");
                        calparse[i] = JO.getString("calories");
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = new ArrayList<>();
                        for(int i=0;i< JA.length();i++){
                            double d_calories = Double.parseDouble(calparse[i]);
                            productList.add(new Product(String.valueOf(i),itemparse[i],"調味料及香辛料類",d_calories));
                        }//13
                        mutableProductList.setValue(productList);
                    }
                });
            }
        });
    }
    private void loadBean(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String type = "豆類";
            @Override
            public void run() {
                String ft_url = "http://54.196.201.140/keepfitness/food_type.php";
                try {
                    URL url = new URL(ft_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    calparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("food");
                        calparse[i] = JO.getString("calories");
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = new ArrayList<>();
                        for(int i=0;i< JA.length();i++){
                            double d_calories = Double.parseDouble(calparse[i]);
                            productList.add(new Product(String.valueOf(i),itemparse[i],"豆類",d_calories));
                        }//13
                        mutableProductList.setValue(productList);
                    }
                });
            }
        });
    }
    private void loadDrink(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String type = "飲料類";
            @Override
            public void run() {
                String ft_url = "http://54.196.201.140/keepfitness/food_type.php";
                try {
                    URL url = new URL(ft_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    calparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("food");
                        calparse[i] = JO.getString("calories");
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = new ArrayList<>();
                        for(int i=0;i< JA.length();i++){
                            double d_calories = Double.parseDouble(calparse[i]);
                            productList.add(new Product(String.valueOf(i),itemparse[i],"飲料類",d_calories));
                        }//13
                        mutableProductList.setValue(productList);
                    }
                });
            }
        });
    }
    private void loadFish(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            String type = "魚貝類";
            @Override
            public void run() {
                String ft_url = "http://54.196.201.140/keepfitness/food_type.php";
                try {
                    URL url = new URL(ft_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
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
                    calparse = new String[JA.length()];
                    for (int i = 0; i < JA.length(); i++) {
                        JO = (JSONObject) JA.get(i);
                        itemparse[i] = JO.getString("food");
                        calparse[i] = JO.getString("calories");
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
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = new ArrayList<>();
                        for(int i=0;i< JA.length();i++){
                            double d_calories = Double.parseDouble(calparse[i]);
                            productList.add(new Product(String.valueOf(i),itemparse[i],"魚貝類",d_calories));
                        }//13
                        mutableProductList.setValue(productList);
                    }
                });
            }
        });
    }

}
