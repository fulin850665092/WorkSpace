package com.atguigu.combinetextinputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ConbineDriver {

    public static void main(String[] args) throws Exception {

        args=new String[]{"/Users/fulin/test/","/Users/fulin/test/output1"};

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        //设置虚拟切片大小为4M
        CombineTextInputFormat.setMaxInputSplitSize(job,1024*1024*4);
        //告诉job使用Combine
        job.setInputFormatClass(CombineTextInputFormat.class);

        job.setJarByClass(ConbineDriver.class);
        job.setMapperClass(CombineMapper.class);
        job.setReducerClass(CombineReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true);
    }
}
