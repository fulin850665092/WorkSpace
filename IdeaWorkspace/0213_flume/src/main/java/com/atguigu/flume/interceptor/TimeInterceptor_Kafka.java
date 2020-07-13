package com.atguigu.flume.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;

/**
 * 实现精准的时间消费
 */
public class TimeInterceptor_Kafka implements Interceptor {

    @Override
    public void initialize() {

    }
    /*
        {
        "common":{"ar":"110000","ba":"Xiaomi","ch":"xiaomi","md":"Xiaomi 10 Pro ","mid":"mid_62","os":"Android 11.0","uid":"167","vc":"v2.1.134"},"displays":[{"displayType":"activity","item":"2","item_type":"activity_id","order":1},{"displayType":"activity","item":"2","item_type":"activity_id","order":2},{"displayType":"query","item":"3","item_type":"sku_id","order":3},{"displayType":"query","item":"4","item_type":"sku_id","order":4},{"displayType":"query","item":"7","item_type":"sku_id","order":5},{"displayType":"query","item":"5","item_type":"sku_id","order":6},{"displayType":"promotion","item":"7","item_type":"sku_id","order":7}],
        "page":{"during_time":1477,"page_id":"home"},
        "ts":1592784222323}
     */
    @Override
    public Event intercept(Event event) {
        String line= new String( event.getBody());
        JSONObject jsonObject = JSONObject.parseObject(line);
        String ts = jsonObject.getString("ts");
        if (ts !=null){
            //设置key区分
            event.getHeaders().put("timestamp",ts);
        }
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    @Override
    public void close() {

    }
    public static class  Builder implements Interceptor.Builder{
        @Override
        public Interceptor build() {
          return   new TimeInterceptor_Kafka();
        }

        @Override
        public void configure(Context context) {

        }
    }

}
