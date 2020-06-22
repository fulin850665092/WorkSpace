package com.atguigu.mapreduce.nline;

import com.atguigu.mapreduce.keyvalue.KeyValueTextInputFormatDriver;
import com.atguigu.mapreduce.keyvalue.KeyValueTextInputFormatMapper;
import com.atguigu.mapreduce.keyvalue.KeyValueTextInputFormatReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class NlineDriver {


    public static void main(String[] args) throws  Exception {

        args=new String[]{"/Users/fulin/test/test4.txt","/Users/fulin/test/output4"};

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        //设置没三行为一个切片
        NLineInputFormat.setNumLinesPerSplit(job,3);
        //设置的NLineInputFormat
        job.setInputFormatClass(NLineInputFormat.class);

        job.setJarByClass(NlineDriver.class);
        job.setMapperClass(NlineMapper.class);
        job.setReducerClass(NlineReduce.class);

        job.setMapOutputValueClass(IntWritable.class);
        job.setMapOutputKeyClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true);
    }
}