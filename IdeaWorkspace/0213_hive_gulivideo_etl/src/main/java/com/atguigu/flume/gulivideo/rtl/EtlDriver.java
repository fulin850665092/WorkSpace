package com.atguigu.flume.gulivideo.rtl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class EtlDriver {

    public static void main(String[] args) throws  Exception{
//        args=new String[]{"/Users/fulin/test/test.txt","/Users/fulin/test/combinerput"};
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(EtlDriver.class);
        job.setMapperClass(EtlMapper.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.setNumReduceTasks(0);
        job.waitForCompletion(true);
    }

}
