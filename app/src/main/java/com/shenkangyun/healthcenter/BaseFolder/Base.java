package com.shenkangyun.healthcenter.BaseFolder;

import com.shenkangyun.healthcenter.DBFolder.User;
import com.shenkangyun.healthcenter.DBFolder.UserEntry;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by Administrator on 2018/7/5.
 */

public class Base {


    //    public static final String URL = "http://192.168.0.210:8080/skyapp_cfyou/api/app_patient/";
    public static final String URL = "http://192.168.0.211:8080/skyapp_cfyou/api/app_patient/";
//    public static final String URL = "http://192.168.0.214:8080/skyapp_cfyou/api/app_patient/";


    public static final String AppKey = "8742c996f084b5c190cf43c5";
    public static final String TARGET_ID = "targetId";
    public static final String TARGET_APP_KEY = "targetAppKey";
    public static final String GROUP_ID = "groupId";
    public static final String CONV_TITLE = "conv_title";

    public static String getMD5Str() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMdd");
        String md5 = new Md5Hash("shenkangyun_canlian_patient", timeFormat.format(new Date()), 1).toString();
        return md5;
    }

    public static String getTimeSpan() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMdd");
        return timeFormat.format(new Date());
    }

    public static int getUserID() {
        int userID = 0;
        List<User> all = LitePal.findAll(User.class);
        for (int i = 0; i < all.size(); i++) {
            userID = all.get(i).getUserID();
        }
        return userID;
    }

    public static String getLoginNameP() {
        String loginName = "";
        List<User> all = LitePal.findAll(User.class);
        for (int i = 0; i < all.size(); i++) {
            loginName = all.get(i).getLoginName();
        }
        return loginName;
    }

    public static String getMobileP() {
        String mobile = "";
        List<User> all = LitePal.findAll(User.class);
        for (int i = 0; i < all.size(); i++) {
            mobile = all.get(i).getMobile();
        }
        return mobile;
    }

    public static String getIDCardP() {
        String idCard = "";
        List<User> all = LitePal.findAll(User.class);
        for (int i = 0; i < all.size(); i++) {
            idCard = all.get(i).getIdCard();
        }
        return idCard;
    }

    public static String getNationalP() {
        String national = "";
        List<User> all = LitePal.findAll(User.class);
        for (int i = 0; i < all.size(); i++) {
            national = all.get(i).getNational();
        }
        return national;
    }

    public static int getAgeP() {
        int age = 0;
        List<User> all = LitePal.findAll(User.class);
        for (int i = 0; i < all.size(); i++) {
            age = all.get(i).getAge();
        }
        return age;
    }

    public static UserEntry getUserEntry() {
        return UserEntry.getUser(JMessageClient.getMyInfo().getUserName(), JMessageClient.getMyInfo().getAppKey());
    }
}