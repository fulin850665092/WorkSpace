package com.atguigu.mapreduce.flowtest;

import com.atguigu.bean.NumberBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class NumberDriver {

    public static void main(String[] args) throws  Exception{

        args=new String[]{"/Users/fulin/test/test2.txt","/Users/fulin/test/Beanput"};

        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration);

        job.setJarByClass(NumberDriver.class);
        job.setReducerClass(NumberReduce.class);
        job.setMapperClass(NumberMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NumberBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NumberBean.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true);
    }
}
