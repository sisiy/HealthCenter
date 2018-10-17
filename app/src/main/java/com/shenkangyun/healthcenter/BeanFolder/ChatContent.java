package com.shenkangyun.healthcenter.BeanFolder;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018/5/14.
 */

public class ChatContent implements MultiItemEntity {

    private String content;

    public static final int send = 1;
    public static final int receive = 2;
    private int itemType;

    public ChatContent(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
