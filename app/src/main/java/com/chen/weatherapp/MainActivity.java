   package com.chen.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.chen.weatherapp.Bean.City;
import com.chen.weatherapp.Bean.CityandKey;
import com.chen.weatherapp.Tool.Connect;
import com.chen.weatherapp.Tool.GetText;
import com.chen.weatherapp.Tool.SP;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SerializablePermission;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

   public class MainActivity extends AppCompatActivity {
       private Handler handler;
       private TextView textView,textView1,textView2,textView3,textView4;
       private static String weaurl = "http://t.weather.sojson.com/api/weather/city/101210401";
       private SP sp;
       private Spinner spinner;
       private List<City.dataBean.forecast> list;
       private City.cityInfoBean cityInfoBean;
       private City.dataBean dataBean;
       private ListView listView;
       private CityInfoAdapter cityInfoAdapter;
       private EditText editText;
       private List<String> his = new ArrayList<>();
       private ImageButton button;
       private ArrayAdapter arrayAdapter;
       private String respone;
       private SearchView searchView;
       @SuppressLint("MissingInflatedId")
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);
           initView();
           Defult();
           spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                   ShowCity(his.get(i).toString());
               }
               @Override
               public void onNothingSelected(AdapterView<?> adapterView) {
                   Defult();
               }
           });
       }
       public void Defult(){
           new Thread(){
               @Override
               public void run() {
                   Connection(weaurl);
               }
           }.start();
           handler = new Handler() {
               public void handleMessage(Message msg) {
                   switch (msg.what) {
                       case 0:
                           list = Json(msg.obj.toString()).getDataList().getForecasts();
                           cityInfoBean = Json(msg.obj.toString()).getCityinfos();
                           dataBean = Json(msg.obj.toString()).getDataList();
                           Show();
                           break;
                       default:
                           break;
                   }
               }
           };
       }
       public void initView(){
           textView = findViewById(R.id.tv_city);
           textView1 = findViewById(R.id.tv_wendu);
           textView2 = findViewById(R.id.tv_pm);
           textView3 = findViewById(R.id.tv_zhiling);
           editText = findViewById(R.id.ed_city);
           button = findViewById(R.id.searchcity);
           listView = findViewById(R.id.listview_cityinfo);
           spinner = findViewById(R.id.history);
           sp = new SP(getApplicationContext());
           getHis();
       }
       public void getHis(){
           his.add("宁波");
           his.add(sp.getCity());
           arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,his);
           arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           spinner.setAdapter(arrayAdapter);

       }
       public void Show(){
           cityInfoAdapter = new CityInfoAdapter(getApplicationContext(),list);
           listView.setAdapter(cityInfoAdapter);
           textView.setText(cityInfoBean.getParent()+"省"+cityInfoBean.getCity());
           textView1.setText(dataBean.getWendu()+"度");
           textView2.setText("pm25: "+dataBean.getPm25()+"/ pm10: "+dataBean.getPm10());
           textView3.setText("空气质量: "+dataBean.getQuality());
       }
       public void Connection(String urll) {
           new Thread() {
               public void run() {
                   try {
                       URL url = new URL(urll);
                       final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                       connection.setRequestMethod("GET");
                       InputStream inputstream = connection.getInputStream();
                       final BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
                       StringBuilder respone = new StringBuilder();
                       String line;
                       while ((line = reader.readLine()) != null) {
                           respone.append(line);
                       }
                       Message msg = Message.obtain();
                       msg.what = 0;
                       msg.obj = respone.toString();
                       handler.sendMessage(msg);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }.start();
       }
       public City Json(String data){
           Gson gson = new Gson();//Parse the data
           City city = gson.fromJson(data,City.class);
           return city;
       }
       public String Search(String city){
           HashMap<String,String> hashMap = new HashMap<>();
           Gson gson = new Gson();
           CityandKey cityandKey = gson.fromJson(new GetText().readAssetsTxt(getApplicationContext(),"citykey"), CityandKey.class);
           List<CityandKey.CityKeyBean> list = cityandKey.getList();
           List<CityandKey.CityKeyBean.cityBean> mlist = new ArrayList<>();
           for(int j = 0;j<list.size();j++){
               mlist = list.get(j).getList();
               for(int x = 0;x<mlist.size();x++){
                   hashMap.put(mlist.get(x).getCityName(),mlist.get(x).getKey());
               }
           }
             return hashMap.get(city);
       }
       public void SearchOnclick(View view){
          SearchCity(editText.getText().toString());
       }
       public void SearchCity(String city){
           ShowCity(city);
           sp.SaveCity(editText.getText().toString());
           his.clear();
           getHis();
       }
       public void ShowCity(String city){
           String url = "http://t.weather.sojson.com/api/weather/city/";
           new Thread(){
               @Override
               public void run() {
                   Connection(url+Search(city));
               }
           }.start();
           handler = new Handler() {
               public void handleMessage(Message msg) {
                   switch (msg.what) {
                       case 0:
                           list = Json(msg.obj.toString()).getDataList().getForecasts();
                           cityInfoBean = Json(msg.obj.toString()).getCityinfos();
                           dataBean = Json(msg.obj.toString()).getDataList();
                           Show();
                           break;
                       default:
                           break;
                   }
               }
           };
       }
   }