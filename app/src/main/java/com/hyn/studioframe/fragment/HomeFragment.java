package com.hyn.studioframe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.hyn.baselibrary.avc.utils.DateUtil;
import com.hyn.baselibrary.avc.wight.xlistview.XListView;
import com.hyn.studioframe.R;
import com.hyn.studioframe.activity.DetailActivity;
import com.hyn.studioframe.adapter.ListViewAdapter;
import com.hyn.studioframe.database.DataInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hyn on 2016/1/12.
 */
public class HomeFragment extends Fragment implements XListView.IXListViewListener {
    private XListView listView;
    private List<DataInfo> list = new ArrayList<>();
    private ListViewAdapter adapter;
    Date date = new Date(System.currentTimeMillis());
    String s = DateUtil.getQyFormateDate(date);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        for (int i = 0; i < 5; i++) {
            DataInfo dataInfo = new DataInfo();
            dataInfo.setName("测试" + i);
            dataInfo.setSex("男");
            dataInfo.setAge("25" + i);
            dataInfo.setJob("开发工程师" + i);
            dataInfo.setPhone("1234568779" + i);
            dataInfo.setAddress("河南" + i);
            dataInfo.setEmail("123456@sina.com" + i);
            list.add(dataInfo);
        }
        adapter = new ListViewAdapter(getActivity(), list);
        listView = (XListView) view.findViewById(R.id.lv);
        listView.setXListViewListener(this);
//        listView.setPullLoadEnable(true);
        listView.setAdapter(adapter);
        listView.setRefreshTime(s);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), DetailActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DataInfo dataInfo = new DataInfo();
                dataInfo.setName("添加测试");
                dataInfo.setSex("男");
                dataInfo.setAge("20");
                dataInfo.setJob("工程师");
                dataInfo.setPhone("1234568779");
                dataInfo.setAddress("河南郑州");
                dataInfo.setEmail("123456@sina.com");
                list.add(dataInfo);
                list.add(0, dataInfo);
                adapter.notifyDataSetChanged();
                listView.stopRefresh();
                listView.setRefreshTime(s);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DataInfo dataInfo = new DataInfo();
                dataInfo.setName("添加测试");
                dataInfo.setSex("男");
                dataInfo.setAge("20");
                dataInfo.setJob("工程师");
                dataInfo.setPhone("1234568779");
                dataInfo.setAddress("河南郑州");
                dataInfo.setEmail("123456@sina.com");
                list.add(dataInfo);
                list.add(0, dataInfo);
                adapter.notifyDataSetChanged();
                listView.stopRefresh();
                listView.setRefreshTime(s);
            }
        }, 2000);
    }
}
