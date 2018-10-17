package com.shenkangyun.healthcenter.UtilFolder;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * Created by Simple on 2016/10/22.
 */

public abstract class GsonCallBack<T> extends StringCallback {
    private final Gson mGson;
    Type mType;


    public GsonCallBack() {
        mType = getSuperclassTypeParameter(getClass());
        mGson=new Gson();
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        onError(e);
    }

    @Override
    public void onResponse(String response, int id) {
        String s = response.replaceAll("<script.*script>", "");
        try {
            if (mType == String.class) {
                try {
                    onSuccess((T)s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Object o = mGson.fromJson(s, mType);
                try {
                    onSuccess((T) o);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (com.google.gson.JsonParseException e)//Json解析的错误
        {
            onError(e);
        }
    }



    public abstract void onSuccess(T response) throws JSONException;
    public abstract void onError(Exception e);


}
