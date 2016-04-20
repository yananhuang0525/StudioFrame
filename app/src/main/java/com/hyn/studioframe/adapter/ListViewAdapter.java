package com.hyn.studioframe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyn.studioframe.R;
import com.hyn.studioframe.database.DataInfo;

import java.util.List;

/**
 * Created by hyn on 2015/12/2.
 */
public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<DataInfo> list;

    public ListViewAdapter(Context context, List<DataInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyTag tag = new MyTag();
        DataInfo data = list.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview, null);
            tag.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            tag.tv_job = (TextView) convertView.findViewById(R.id.tv_job);
            tag.tv_sex = (TextView) convertView.findViewById(R.id.tv_sex);
            tag.tv_age = (TextView) convertView.findViewById(R.id.tv_age);
            tag.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            tag.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            tag.tv_email = (TextView) convertView.findViewById(R.id.tv_email);
            convertView.setTag(tag);
        } else {
            tag = (MyTag) convertView.getTag();
        }

        tag.tv_name.setText("姓名： " + list.get(position).getName());
        tag.tv_sex.setText("性别: " + list.get(position).getSex());
        tag.tv_age.setText("年龄: " + list.get(position).getAge());
        tag.tv_address.setText("地址: " + list.get(position).getAddress());
        tag.tv_phone.setText("电话: " + list.get(position).getPhone());
        tag.tv_email.setText("邮箱: " + list.get(position).getEmail());
        tag.tv_job.setText("职位: " + list.get(position).getJob());
        return convertView;
    }

    private class MyTag {
        private TextView tv_sex;
        private TextView tv_name;
        private TextView tv_job;
        private TextView tv_age;
        private TextView tv_address;
        private TextView tv_phone;
        private TextView tv_email;
    }
}
