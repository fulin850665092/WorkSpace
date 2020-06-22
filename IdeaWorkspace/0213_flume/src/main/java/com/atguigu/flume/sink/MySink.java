package com.atguigu.flume.sink;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MySink extends AbstractSink implements Configurable {
    private  Integer batchSize=null;
    private String prifix=null;
    private List<Event> list= new ArrayList<Event>();
    private static Logger LOG= LoggerFactory.getLogger(CustomSink.class);

    //循环调用读取
    public Status process() throws EventDeliveryException {
        list.clear();
        //获取
        Channel channel = getChannel();
        //获取事务
        Transaction transaction = channel.getTransaction();
        //开始事务
        transaction.begin();
        try {
            for (int i = 0; i < batchSize; i++) {
                Event event = channel.take();
                if (event==null){
                    break;
                }
                list.add(event);
            }
            for (Event event : list) {
                LOG.info(prifix+"-"+new String(event.getBody()));
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            return Status.BACKOFF;
        } finally {
            transaction.close();
        }
        return Status.READY;
    }

    //读取配置文件
    public void configure(Context context) {
        batchSize = context.getInteger("batchSize", 20);
        prifix = context.getString("prefix");
    }
}
