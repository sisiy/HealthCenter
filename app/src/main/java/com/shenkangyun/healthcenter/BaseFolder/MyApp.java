package com.shenkangyun.healthcenter.BaseFolder;

import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

import java.util.concurrent.TimeUnit;

import cn.jpush.im.android.api.JMessageClient;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2018/6/19.
 */

public class MyApp extends LitePalApplication {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取全局Context
        context = getApplicationContext();
        //初始化极光SDK
        JMessageClient.setDebugMode(true);
        JMessageClient.init(this);
        //设置Notification的模式
        JMessageClient.setNotificationFlag(JMessageClient.FLAG_NOTIFY_WITH_SOUND | JMessageClient.FLAG_NOTIFY_WITH_LED | JMessageClient.FLAG_NOTIFY_WITH_VIBRATE);
        //初始化LitePal
        LitePal.initialize(this);
        //初始化Http
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //.addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public static Context getContext() {
        return context;
    }
}
