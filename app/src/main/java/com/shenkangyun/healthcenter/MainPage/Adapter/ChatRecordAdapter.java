package com.shenkangyun.healthcenter.MainPage.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.healthcenter.BeanFolder.ChatRecord;
import com.shenkangyun.healthcenter.R;

/**
 * Created by Administrator on 2018/6/11.
 */

public class ChatRecordAdapter extends BaseQuickAdapter<ChatRecord, BaseViewHolder> {
    public ChatRecordAdapter() {
        super(R.layout.item_chatlist, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChatRecord item) {
        helper.setText(R.id.tv_name, item.getNickName());
        helper.setText(R.id.tv_count, item.getUnRemsgCount());
        helper.setVisible(R.id.tv_count, true);
        helper.addOnClickListener(R.id.content);
        helper.addOnClickListener(R.id.right);
    }
}