package com.hyn.studioframe.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hyn.baselibrary.avc.core.BaseActivity;
import com.hyn.baselibrary.avc.wight.loopviewpager.AutoLoopViewPager;
import com.hyn.baselibrary.avc.wight.viewpagerindicator.CirclePageIndicator;
import com.hyn.studioframe.R;
import com.hyn.studioframe.adapter.GalleryPagerAdapter;

/**
 * Created by hyn on 2016/1/13.
 */
public class DetailActivity extends BaseActivity {
    private Button btnBack;
    private TextView tv_title;
    private AutoLoopViewPager pager;
    private CirclePageIndicator indicator;
    private int[] imageViewIds;
    private GalleryPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_detail);
        initView();
    }

    private void initView() {
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("详情");
        pager = (AutoLoopViewPager) findViewById(R.id.pager);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        imageViewIds = new int[]{R.drawable.house_background, R.drawable.house_background_1, R.drawable.house_background_2};
        adapter = new GalleryPagerAdapter(this, imageViewIds);
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);
        indicator.setPadding(5, 5, 10, 5);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pager.startAutoScroll();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pager.stopAutoScroll();
    }
}
