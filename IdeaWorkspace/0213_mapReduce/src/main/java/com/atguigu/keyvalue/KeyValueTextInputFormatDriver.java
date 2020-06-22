package com.atguigu.keyvalue;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class KeyValueTextInputFormatDriver {

    public static void main(String[] args) throws  Exception {

        args=new String[]{"/Users/fulin/test/test3.txt","/Users/fulin/test/output3"};

        Configuration configuration = new Configuration();
        //设置切割符
        configuration.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR," ");

        Job job = Job.getInstance(configuration);
        //设置输入的格式
        job.setInputFormatClass(KeyValueTextInputFormat.class);

        job.setJarByClass(KeyValueTextInputFormatDriver.class);
        job.setMapperClass(KeyValueTextInputFormatMapper.class);
        job.setReducerClass(KeyValueTextInputFormatReduce.class);

        job.setMapOutputValueClass(IntWritable.class);
        job.setMapOutputKeyClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true);
    }
}
