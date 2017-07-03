package com.jun.chu.demo.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by chujun on 2017/3/10.
 */
public class JsonUtils {
    private static final Gson gson               = new Gson();

    /**
     * 序列化null字段
     */
    private static final Gson serializeNullsGson = new GsonBuilder().serializeNulls().create();

    /**
     * 对象转化为json对象
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String toJson(T t) {
        try {
            return toJson(t, false);
        }catch (Exception e){
            //NOP
            System.out.println("json 转化错误:"+e);
            return "json 转化错误";
        }
    }

    /**
     * 对象转化为json对象
     *
     * @param t
     * @param serializeNulls 是否序列化null
     * @param <T>
     * @return
     */
    public static <T> String toJson(T t, boolean serializeNulls) {
        return serializeNulls ? serializeNullsGson.toJson(t) : gson.toJson(t);
    }

    /**
     * json字符串转化为指定类型对象
     *
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String str, Class<T> clazz) {
        return gson.fromJson(str, clazz);
    }

    /**
     * json字符串创转化为指定类型对象列表
     *
     * @param jsonStr json字符串
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> fromJsonList(String jsonStr, Class<T> tClass) {
        return gson.fromJson(jsonStr, new TypeToken<List<T>>() {
        }.getType());
    }
}
