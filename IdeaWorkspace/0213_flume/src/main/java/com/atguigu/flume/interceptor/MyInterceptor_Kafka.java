package com.atguigu.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;

public class MyInterceptor_Kafka implements Interceptor {
    //初始化方法
    public void initialize() {

    }

    public Event intercept(Event event) {
        //Interceptor 由header 和body组成，header k-v键值对，body是数据
        byte[] body = event.getBody();
        if (body[0]>='1'&&body[0]<='9'){
            //设置key区分
            event.getHeaders().put("topic","number");
        }else if (body[0]>='a'&&body[0]<='z'){
            event.getHeaders().put("topic","letter");
        }
        return event;
    }

    public List<Event> intercept(List<Event> list) {
        for (Event event : list) {
            intercept(event);
        }
        return list;
    }
    //关闭方法
    public void close() {

    }
    //使用内部类实现Intercetor.Builderz拦截器
    public static  class Builder implements Interceptor.Builder{
        //创建拦截器对象
        public Interceptor build() {
            return new MyInterceptor_Kafka();
        }
        //获取配置里面的参数
        public void configure(Context context) {

        }
    }
}
