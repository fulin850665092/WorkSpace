package com.atguigu.hbase.mrHbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HRegionPartitioner;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;

/**
 *  todo 从hbase A表中读取数据，过滤后，插入到B表
 */
import java.io.IOException;

public class driver {

    public static void main(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.create();
        //zookeeper集群地址,不用谢端口号
        conf.set("hbase.zookeeper.quorum","hadoop102,hadoop103,hadoop104");
        //设置zookeeper 的客户端端口号
        conf.set("hbase.zookeeper.property.clientPort","2181");
        Job job = Job.getInstance(conf);

        job.setJarByClass(driver.class);

        Scan scan = new Scan();
        TableMapReduceUtil.initTableMapperJob("fruit",scan,mapper.class, ImmutableBytesWritable.class, Put.class,job);

        job.setNumReduceTasks(100);
        TableMapReduceUtil.initTableReducerJob("fruit_1",reduce.class,job, HRegionPartitioner.class);

        job.waitForCompletion(true);

    }
}

