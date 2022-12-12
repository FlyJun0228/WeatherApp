package com.chen.weatherapp.Tool;

import android.content.Context;
import android.content.SharedPreferences;

public class SP {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public SP(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("City",Context.MODE_PRIVATE);
    }
    public void SaveCity(String name){
        editor = sharedPreferences.edit();
        editor.putString("city",name);
        editor.commit();
    }
    public String getCity(){
        return sharedPreferences.getString("city","宁波");
    }
}
