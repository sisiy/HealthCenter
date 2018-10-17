package com.shenkangyun.healthcenter.IMFolder.Adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.healthcenter.BeanFolder.ChatContent;
import com.shenkangyun.healthcenter.R;

/**
 * Created by Administrator on 2018/5/14.
 */

public class ChatContentAdapter extends BaseMultiItemQuickAdapter<ChatContent, BaseViewHolder> {
    public ChatContentAdapter() {
        super(null);
        addItemType(ChatContent.send, R.layout.item_send);
        addItemType(ChatContent.receive, R.layout.item_receive);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChatContent item) {
        switch (helper.getItemViewType()) {
            case ChatContent.send:
                helper.setText(R.id.tv_message, item.getContent());
                break;
            case ChatContent.receive:
                helper.setText(R.id.tv_message, item.getContent());
                break;
        }

    }
}
