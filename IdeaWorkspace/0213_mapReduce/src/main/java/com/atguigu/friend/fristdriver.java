package com.atguigu.friend;

import com.atguigu.mapreduce.wordcounttest.WordtestMap;
import com.atguigu.mapreduce.wordcounttest.wordtestDrive;
import com.atguigu.mapreduce.wordcounttest.wordtestReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class fristdriver {

    public static void main(String[] args) throws Exception {
        args=new String[]{"/Users/fulin/test/friend.txt","/Users/fulin/test/fristDriver"};

        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration);

        job.setJarByClass(fristdriver.class);
        job.setReducerClass(fristreduce.class);
        job.setMapperClass(fristmapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true);
    }
}
