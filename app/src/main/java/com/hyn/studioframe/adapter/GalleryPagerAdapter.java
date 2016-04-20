package com.hyn.studioframe.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 图片轮播适配器
 * Created by hyn on 2016/1/13.
 */
public class GalleryPagerAdapter extends PagerAdapter {
    private int[] imageViewIds;
    private Context context;

    public GalleryPagerAdapter(Context context, int[] imageViewIds) {
        this.context = context;
        this.imageViewIds = imageViewIds;
    }

    @Override
    public int getCount() {
        return imageViewIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView item = new ImageView(context);
        item.setImageResource(imageViewIds[position]);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
        item.setLayoutParams(params);
        item.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(item);
        return item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
