package com.atguigu.mapreduce.zdytest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

public class zdyDriver {

    public static void main(String[] args) throws  Exception {
        args=new String[]{"/Users/fulin/test/input","/Users/fulin/test/zdyput"};

        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration);

        job.setInputFormatClass(zdyFileInputformat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        job.setJarByClass(zdyDriver.class);
        job.setReducerClass(zdyReduce.class);
        job.setMapperClass(zdyTestMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BytesWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true);
    }
}
