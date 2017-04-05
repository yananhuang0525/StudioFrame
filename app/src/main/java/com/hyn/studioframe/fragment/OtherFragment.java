package com.hyn.studioframe.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hyn.studioframe.R;
import com.hyn.studioframe.adapter.PullToRefreshAdapter;
import com.hyn.studioframe.database.DataInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyn on 2016/1/12.
 */
public class OtherFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private PullToRefreshAdapter pullToRefreshAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initAdapter();
        return view;
    }

    @Override
    public void onRefresh() {
        pullToRefreshAdapter.openLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pullToRefreshAdapter.addData(newDate(1));
                mSwipeRefreshLayout.setRefreshing(false);
                pullToRefreshAdapter.openLoadMore(true);
            }
        }, 1000);
    }

    @Override
    public void onLoadMoreRequested() {
//        mSwipeRefreshLayout.setEnabled(false);
        pullToRefreshAdapter.addData(newDate(2));
    }

    private void initAdapter() {
        pullToRefreshAdapter = new PullToRefreshAdapter(R.layout.item_listview, newDate(10));
        pullToRefreshAdapter.setOnLoadMoreListener(this);
        pullToRefreshAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mRecyclerView.setAdapter(pullToRefreshAdapter);
    }

    private List<DataInfo> newDate(int len) {
        List<DataInfo> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            DataInfo info = new DataInfo();
            info.setName("测试" + i);
            info.setSex("男");
            info.setAge("25");
            info.setJob("开发工程师");
            list.add(info);
        }

        return list;
    }
}
