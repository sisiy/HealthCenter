package com.shenkangyun.healthcenter.IMFolder.Adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.healthcenter.BeanFolder.AcceptBean;
import com.shenkangyun.healthcenter.R;

/**
 * Created by Administrator on 2018/5/10.
 */

public class RequestAdapter extends BaseMultiItemQuickAdapter<AcceptBean, BaseViewHolder> {
    public RequestAdapter() {
        super(null);
        addItemType(AcceptBean.accept, R.layout.item_accept);
        addItemType(AcceptBean.tidings, R.layout.item_message);
    }

    @Override
    protected void convert(BaseViewHolder helper, AcceptBean item) {
        switch (helper.getItemViewType()) {
            case AcceptBean.accept:
                helper.setText(R.id.tv_name, item.getName());
                helper.addOnClickListener(R.id.tv_accept);
                helper.addOnClickListener(R.id.tv_refuse);
                helper.addOnClickListener(R.id.right);
                break;
            case AcceptBean.tidings:

                break;
        }

    }
}
