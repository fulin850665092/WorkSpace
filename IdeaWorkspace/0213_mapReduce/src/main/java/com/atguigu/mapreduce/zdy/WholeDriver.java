package com.atguigu.mapreduce.zdy;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

public class WholeDriver {


    public static void main(String[] args) throws  Exception {
        args=new String[]{"/Users/fulin/test/input","/Users/fulin/test/output5"};

        Job job = Job.getInstance(new Configuration());

        //设置自定义的inputformat
        job.setInputFormatClass(WholeFileInputFormat.class);
        //设置输出的outputformat
        job.setOutputValueClass(SequenceFileOutputFormat.class);

        job.setJarByClass(WholeDriver.class);
        job.setMapperClass(WholeMapper.class);
        job.setReducerClass(WholeReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BytesWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true);

    }
}
