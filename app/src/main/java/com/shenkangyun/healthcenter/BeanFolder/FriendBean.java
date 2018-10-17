package com.shenkangyun.healthcenter.BeanFolder;

/**
 * Created by Administrator on 2018/9/18.
 */

public class FriendBean {
    private String nickName;
    private String userName;
    private long birthday;

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
