package com.atguigu.hbase.mrHdfs_tes;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HRegionPartitioner;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

public class driver {
    public static void main(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.create();
        //zookeeper集群地址,不用谢端口号
        conf.set("hbase.zookeeper.quorum","hadoop102,hadoop103,hadoop104");
        //设置zookeeper 的客户端端口号
        conf.set("hbase.zookeeper.property.clientPort","2181");
        Job job = Job.getInstance(conf);
        job.setJarByClass(driver.class);

        job.setMapperClass(mapper.class);
        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(Put.class);
        FileInputFormat.setInputPaths(job,new Path("/fruit"));

        TableMapReduceUtil.initTableReducerJob("friut3",reduce.class,job, HRegionPartitioner.class);

        job.waitForCompletion(true);
    }
}
