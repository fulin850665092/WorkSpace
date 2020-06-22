package com.atguigu.join;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.net.URI;

public class mapjoinDriver {

    public static void main(String[] args) throws  Exception{
        args=new String[]{"/Users/fulin/test/mapjoin/order.txt","/Users/fulin/test/mapjointest2"};

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(mapjoinDriver.class);
        job.setMapperClass(mapjoin.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.addCacheArchive(new URI("/Users/fulin/test/mapjoin/pd.txt"));
        job.setNumReduceTasks(0);

        job.waitForCompletion(true);
    }
}
