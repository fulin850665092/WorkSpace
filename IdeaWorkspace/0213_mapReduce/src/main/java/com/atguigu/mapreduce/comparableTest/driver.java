package com.atguigu.mapreduce.comparableTest;

import com.atguigu.bean.ComparableBean;
import com.atguigu.bean.ComparableBean2;
import com.atguigu.mapreduce.compareableAndpartition.NumberDriver;
import com.atguigu.mapreduce.compareableAndpartition.NumberMapper;
import com.atguigu.mapreduce.compareableAndpartition.NumberReduce;
import com.atguigu.mapreduce.compareableAndpartition.partitionNum;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class driver {
    public static void main(String[] args) throws  Exception{
        args=new String[]{"/Users/fulin/test/test2.txt","/Users/fulin/test/comparaable4"};

        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration);

        job.setPartitionerClass(PhoneNumPartitioner.class);
        job.setNumReduceTasks(5);

        job.setJarByClass(driver.class);
        job.setReducerClass(reduce.class);
        job.setMapperClass(map.class);

        job.setMapOutputKeyClass(ComparableBean2.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(ComparableBean2.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true);
    }

}
