package com.atguigu.flume.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

public class JSONUtils {
    public static boolean CheckJson(String line){
        try {
            JSON.parse(line);
            return  true;
        } catch (JSONException e) {
            return false;
        }
    }
}
