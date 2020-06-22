package com.atguigu.shuffer.outputformat;

import com.atguigu.shuffer.combiner.combiner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class driver {

    public static void main(String[] args) throws  Exception {

        args=new String[]{"/Users/fulin/test/output.txt","/Users/fulin/test/shuffer_outputformat"};
        //1.获取连接以及封装任务
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        //2设置jar的加载路径
        job.setJarByClass(driver.class);
        //3.设置map和reduce类
        job.setMapperClass(map.class);
        job.setReducerClass(reduce.class);
        //4设置map的输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        //5设置最终输出的kv的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        //6.设置输入和输出的路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        job.setOutputFormatClass(outputfomat.class);
        //7提交
        boolean result = job.waitForCompletion(true);
        System.out.println(result ? 0 :1);
    }
    }

