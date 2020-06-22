package com.atguigu.mapreduce.nlinetest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class LineDriver {

    public static void main(String[] args) throws  Exception{

        args=new String[]{"/Users/fulin/test/test4.txt","/Users/fulin/test/nlinput1"};

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        NLineInputFormat.setNumLinesPerSplit(job,5);
        job.setInputFormatClass(NLineInputFormat.class);


        job.setJarByClass(LineDriver.class);
        job.setReducerClass(NlineReduce.class);
        job.setMapperClass(NlineMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true);
    }
}
