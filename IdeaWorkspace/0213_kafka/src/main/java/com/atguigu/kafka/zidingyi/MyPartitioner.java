package com.atguigu.kafka.zidingyi;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

/**
 * 自定义分区器
 */
public class MyPartitioner implements Partitioner {

    //初始化值
    int partitionNum;

    /**
     * 主要重写这个方法，假设有topic country三个分区，producer将key为china、usa和korea的消息分开存储到不同的分区，否则都放到0号分区
     * @param topic 要使用自定义分区的topic
     * @param key 消息key
     * @param keyBytes 消息key序列化字节数组
     * @param value 消息value
     * @param valueBytes 消息value序列化字节数组
     * @param cluster 集群元信息
     * @return
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        String keyStr=(String) key;
        //获取分区信息
        List<PartitionInfo> partitionInfoList=cluster.availablePartitionsForTopic("country");
        int partitionInfoListSize=partitionInfoList.size();
        //判断是否有三个分区
        if(partitionInfoListSize==3){
            switch (keyStr){
                case "china":
                    partitionNum=0;
                    break;
                case "usa":
                    partitionNum=1;
                    break;
                case "korea":
                    partitionNum=2;
                    break;
                default:
                    partitionNum=0;
                    break;
            }
        }

        //返回分区序号
        return partitionNum;
    }

    @Override
    public void close() {
        //资源的清理工作在这里执行
        System.out.println("-----分区结束-----");
    }

    @Override
    public void configure(Map<String, ?> configs) {
        //资源的初始化工作在这里执行
        partitionNum=0;
    }
}