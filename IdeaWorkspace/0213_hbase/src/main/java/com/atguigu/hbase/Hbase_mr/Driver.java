package com.atguigu.hbase.Hbase_mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HRegionPartitioner;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;

/**
 * 将fruit表族的一部分数据，用于mr迁入到fruit_mr表中
 */
public class Driver {
    public static void main(String[] args)throws Exception {

        Configuration configuration = HBaseConfiguration.create();
        //zookeeper集群地址,不用谢端口号
        configuration.set("hbase.zookeeper.quorum","hadoop102,hadoop103,hadoop104");
        //设置zookeeper 的客户端端口号
//        configuration.set("hbase.zookeeper.property.clientPort","2181");
        Job job = Job.getInstance(configuration);

        job.setJarByClass(Driver.class);
        Scan scan = new Scan();
        //把blockcache关掉(跑mr的时候最好关掉)
        scan.setCacheBlocks(false);
        TableMapReduceUtil.initTableMapperJob("fruit",scan,ReadMapper.class,ImmutableBytesWritable.class, Put.class,job);
        //设置reduce个数
        job.setNumReduceTasks(100);
        TableMapReduceUtil.initTableReducerJob("fruit_mr",WriterReduce.class,job, HRegionPartitioner.class);

        job.waitForCompletion(true);
    }
}
