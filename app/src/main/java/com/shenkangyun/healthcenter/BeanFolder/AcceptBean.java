package com.shenkangyun.healthcenter.BeanFolder;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018/5/10.
 */

public class AcceptBean implements MultiItemEntity {

    private String id;
    private String name;
    private String tidingsTime;
    private String tidingsCount;

    public static final int accept = 1;
    public static final int tidings = 2;
    private int itemType;

    public AcceptBean(int itemType) {
        this.itemType = itemType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTidingsTime() {
        return tidingsTime;
    }

    public void setTidingsTime(String tidingsTime) {
        this.tidingsTime = tidingsTime;
    }

    public String getTidingsCount() {
        return tidingsCount;
    }

    public void setTidingsCount(String tidingsCount) {
        this.tidingsCount = tidingsCount;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
