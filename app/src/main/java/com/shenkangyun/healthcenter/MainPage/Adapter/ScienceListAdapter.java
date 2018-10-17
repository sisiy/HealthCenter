package com.shenkangyun.healthcenter.MainPage.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.healthcenter.BeanFolder.ScienceBean;
import com.shenkangyun.healthcenter.R;

/**
 * Created by Administrator on 2018/8/3.
 */

public class ScienceListAdapter extends BaseQuickAdapter<ScienceBean.DataBean.ListBean, BaseViewHolder> {
    public ScienceListAdapter() {
        super(R.layout.item_science_list, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScienceBean.DataBean.ListBean item) {
        helper.setText(R.id.science_name, item.getArticleName());
        helper.addOnClickListener(R.id.content);
    }
}
