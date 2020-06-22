package com.atguigu.flume.sink;

import org.apache.commons.logging.LogFactory;
import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;

public class CustomSink extends AbstractSink implements Configurable {

    private  Integer batchSize=null;
    private String prifix=null;
    private List<Event> list= new ArrayList<Event>();
    private static Logger LOG= LoggerFactory.getLogger(CustomSink.class);

    //写业务逻辑，会被循环调用
    public Status process() throws EventDeliveryException {
        list.clear();
        //获取配置文件中当前sink指定的channel
        Channel channel = getChannel();
        Transaction tx = channel.getTransaction();
        tx.begin();
        try {
            for (int i = 0; i <batchSize ; i++) {
                Event event = channel.take();
                if (event==null){
                    break;
                }
                list.add(event);
            }
            for (Event event : list) {
                LOG.info(prifix+"-"+new String(event.getBody()));
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            return Status.BACKOFF;
        }finally {
            tx.close();
        }
        return Status.READY;
    }
    //获取配置信息
    public void configure(Context context) {
         batchSize = context.getInteger("batchSize", 20);
         prifix = context.getString("prefix");
    }
}
