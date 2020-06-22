package com.atguigu.hbase.mr2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HRegionPartitioner;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.awt.geom.PathIterator;

/**
 * 将HDFS中的数据写入到Hbase表中
 */
public class Driver {
    public static void main(String[] args)throws Exception {

        Configuration configuration = HBaseConfiguration.create();
        //zookeeper集群地址,不用谢端口号
        configuration.set("hbase.zookeeper.quorum","hadoop102,hadoop103,hadoop104");
        //设置zookeeper 的客户端端口号
        Job job = Job.getInstance(configuration);

        job.setJarByClass(Driver.class);
        job.setMapperClass(ReadMapper.class);
        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(Put.class);
        FileInputFormat.setInputPaths(job,new Path("/input_fruit"));

        //设置reduce个数
        job.setNumReduceTasks(100);
        TableMapReduceUtil.initTableReducerJob("fruit_mr2", WriterReduce.class,job, HRegionPartitioner.class);

        job.waitForCompletion(true);
    }
}
