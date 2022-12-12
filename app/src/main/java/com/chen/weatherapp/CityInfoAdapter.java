package com.chen.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chen.weatherapp.Bean.City;

import java.util.List;

public class CityInfoAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<City.dataBean.forecast> mList;                                                     //数据源与适配器进行关联

    public CityInfoAdapter(Context context, List<City.dataBean.forecast> list) {
        mList = list;
        layoutInflater = LayoutInflater.from(context);                                //context要使用当前adapter的界面对象layoutinflater（布局装载器对象）
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //返回每一项的显示内容
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //比较好的适配方法
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.cityinfo_item, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.item_date);
            viewHolder.name1 = (TextView) convertView.findViewById(R.id.item_high);
            viewHolder.name2 = (TextView) convertView.findViewById(R.id.item_low);
            viewHolder.name3 = (TextView) convertView.findViewById(R.id.item_ymd);
            viewHolder.name4 = (TextView) convertView.findViewById(R.id.item_week);
            viewHolder.name5 = (TextView) convertView.findViewById(R.id.item_sunrise);
            viewHolder.name6 = (TextView) convertView.findViewById(R.id.item_sunset);
            viewHolder.name7 = (TextView) convertView.findViewById(R.id.item_aqi);
            viewHolder.name8 = (TextView) convertView.findViewById(R.id.item_fx);
            viewHolder.name9 = (TextView) convertView.findViewById(R.id.item_fl);
            viewHolder.name10= (TextView) convertView.findViewById(R.id.item_type);
            viewHolder.name11 = (TextView) convertView.findViewById(R.id.item_notice);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText((CharSequence) mList.get(position).getYmd());
        viewHolder.name1.setText((CharSequence) mList.get(position).getHigh());
        viewHolder.name2.setText((CharSequence) mList.get(position).getLow());
        viewHolder.name3.setText((CharSequence) mList.get(position).getDate());
        viewHolder.name7.setText((CharSequence) mList.get(position).getWeek());
        viewHolder.name5.setText("日出 "+(CharSequence) mList.get(position).getSunrise());
        viewHolder.name6.setText("日落 "+(CharSequence) mList.get(position).getSunset());
        viewHolder.name4.setText("Aqi "+String.valueOf(mList.get(position).getAqi()));
        viewHolder.name8.setText((CharSequence) mList.get(position).getFx());
        viewHolder.name9.setText((CharSequence) mList.get(position).getfL());
        viewHolder.name10.setText((CharSequence) mList.get(position).getType());
        viewHolder.name11.setText((CharSequence) mList.get(position).getNotice());
        return convertView;
    }
    class ViewHolder {
        public TextView name,name1,name2,name3,name4,name5,name6,name7,name8,name9,name10,name11;
    }


}
