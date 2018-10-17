package com.shenkangyun.healthcenter.BeanFolder;

/**
 * Created by Administrator on 2018/6/11.
 */

public class ChatRecord {

    private String userName;
    private String nickName;
    private String content;
    private String unRemsgCount;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUnRemsgCount() {
        return unRemsgCount;
    }

    public void setUnRemsgCount(String unRemsgCount) {
        this.unRemsgCount = unRemsgCount;
    }
}
