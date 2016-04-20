package com.hyn.studioframe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.hyn.baselibrary.avc.utils.SharedPreferencesUtil;
import com.hyn.baselibrary.avc.wight.viewpagerindicator.CirclePageIndicator;
import com.hyn.studioframe.R;

/**
 * 闪屏界面
 * Created by hyn on 2016/1/11.
 */
public class SplashActivity extends Activity {
    private Button btnHome;
    private CirclePageIndicator indicator;
    private GalleryPagerAdapter adapter;
    private ViewPager pager;
    private int[] images = {
            R.mipmap.newer01,
            R.mipmap.newer02,
            R.mipmap.newer03,
            R.mipmap.newer04
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_splash);
        boolean isFirstUse = SharedPreferencesUtil.getPreferenceData(SplashActivity.this, "first-time-use", true);
        if (isFirstUse) {
            initGuideGallery();
        } else {
            initLaunchLogo();
        }
    }

    private void initLaunchLogo() {
        ImageView guideImage = (ImageView) findViewById(R.id.guideImage);
        guideImage.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        }, 2000);
    }

    /**
     * 引导页
     */
    private void initGuideGallery() {
        final Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SharedPreferences.getInstance().putBoolean("first-time-use", false);
                try {
                    SharedPreferencesUtil.setPreferenceData(SplashActivity.this, "first-time-use", false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(SplashActivity.this, "进入首页", Toast.LENGTH_SHORT).show();
//                UIHelper.showHome(SplashActivity.this);
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setVisibility(View.VISIBLE);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setVisibility(View.VISIBLE);

        adapter = new GalleryPagerAdapter();
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == images.length - 1) {
                    btnHome.setVisibility(View.VISIBLE);
                    btnHome.startAnimation(fadeIn);
                } else {
                    btnHome.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public class GalleryPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView item = new ImageView(SplashActivity.this);
            item.setScaleType(ImageView.ScaleType.CENTER_CROP);
            item.setImageResource(images[position]);
            container.addView(item);
            return item;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }
    }

}
