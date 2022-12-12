package com.chen.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import com.chen.weatherapp.Bean.City;

import java.util.ArrayList;
import java.util.List;

public class Jinqi extends AppCompatActivity {
    private List<City.dataBean.forecast> list;
    private ListView listView;
    private CityInfoAdapter cityInfoAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jinqi);
        listView = findViewById(R.id.listview_cityinfo);
        list = new ArrayList<>();
        list = (List<City.dataBean.forecast>) getIntent().getSerializableExtra("list");
        cityInfoAdapter = new CityInfoAdapter(getApplicationContext(),list);
        listView.setAdapter(cityInfoAdapter);

    }
}