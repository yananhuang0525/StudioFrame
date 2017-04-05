package com.hyn.studioframe.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyn.studioframe.R;
import com.hyn.studioframe.database.DataInfo;

import java.util.List;

/**
 * author： hyn
 * e-mail: hyn_0525@sina.com
 * Date: 2017/4/5
 * Time: 10:19
 */
public class PullToRefreshAdapter extends BaseQuickAdapter<DataInfo> {

    public PullToRefreshAdapter(int layoutResId, List<DataInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DataInfo s) {
        baseViewHolder.setText(R.id.tv_name, s.getName())
                .setText(R.id.tv_sex, s.getSex())
                .setText(R.id.tv_age, s.getAge())
                .setText(R.id.tv_job, s.getJob());
    }
}
