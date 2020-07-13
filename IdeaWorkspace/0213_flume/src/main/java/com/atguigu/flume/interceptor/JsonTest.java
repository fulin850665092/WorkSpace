package com.atguigu.flume.interceptor;

import com.alibaba.fastjson.JSONObject;

public class JsonTest {
    public static void main(String[] args) {
        String jsonString = "{\n" +
                "        \"common\":{\"ar\":\"110000\",\"ba\":\"Xiaomi\",\"ch\":\"xiaomi\",\"md\":\"Xiaomi 10 Pro \",\"mid\":\"mid_62\",\"os\":\"Android 11.0\",\"uid\":\"167\",\"vc\":\"v2.1.134\"},\"displays\":[{\"displayType\":\"activity\",\"item\":\"2\",\"item_type\":\"activity_id\",\"order\":1},{\"displayType\":\"activity\",\"item\":\"2\",\"item_type\":\"activity_id\",\"order\":2},{\"displayType\":\"query\",\"item\":\"3\",\"item_type\":\"sku_id\",\"order\":3},{\"displayType\":\"query\",\"item\":\"4\",\"item_type\":\"sku_id\",\"order\":4},{\"displayType\":\"query\",\"item\":\"7\",\"item_type\":\"sku_id\",\"order\":5},{\"displayType\":\"query\",\"item\":\"5\",\"item_type\":\"sku_id\",\"order\":6},{\"displayType\":\"promotion\",\"item\":\"7\",\"item_type\":\"sku_id\",\"order\":7}],\n" +
                "        \"page\":{\"during_time\":1477,\"page_id\":\"home\"},\n" +
                "        \"ts\":1592784222323}";
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        String ts = jsonObject.getString("ts");
        System.out.println("ts:" + ts);
    }

}
