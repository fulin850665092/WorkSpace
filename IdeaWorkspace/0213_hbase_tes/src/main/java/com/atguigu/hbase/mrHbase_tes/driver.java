package com.atguigu.hbase.mrHbase_tes;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HRegionPartitioner;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Job;

import javax.swing.plaf.nimbus.AbstractRegionPainter;

public class driver {
    public static void main(String[] args)throws Exception {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","hadoop102,hadoop103,hadoop104");
        //设置zookeeper 的客户端端口号
        conf.set("hbase.zookeeper.property.clientPort","2181");
        Job job = Job.getInstance(conf);

        job.setJarByClass(driver.class);

        Scan scan = new Scan();
        TableMapReduceUtil.initTableMapperJob(TableName.valueOf("fruit"),scan,map.class, ImmutableBytesWritable.class, Put.class,job);
        TableMapReduceUtil.initTableReducerJob("friute2",reduce.class,job, HRegionPartitioner.class);
        job.waitForCompletion(true);
    }
}
