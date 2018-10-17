package com.shenkangyun.healthcenter.IMFolder.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.healthcenter.BeanFolder.FriendBean;
import com.shenkangyun.healthcenter.R;

/**
 * Created by Administrator on 2018/9/18.
 */

public class FriendsListAdapter extends BaseQuickAdapter<FriendBean, BaseViewHolder> {
    public FriendsListAdapter() {
        super(R.layout.item_friends, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendBean item) {
        helper.setText(R.id.friend_name, item.getNickName());
    }
}
