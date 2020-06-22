package com.atguigu.shuffer.patitioner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class driver {

    public static void main(String[] args) throws  Exception{

        args=new String[]{"/Users/fulin/test/test2.txt","/Users/fulin/test/shuffer_comparable_patition"};

        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration);

        job.setJarByClass(driver.class);
        job.setReducerClass(reduce.class);
        job.setMapperClass(map.class);

        job.setMapOutputKeyClass(ComparableBean.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(ComparableBean.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.setPartitionerClass(patition.class);
        job.setNumReduceTasks(4);
        job.waitForCompletion(true);
    }
}
