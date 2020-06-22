package com.atguigu.shuffer.comparator;

import com.atguigu.bean.FlowBean;
import com.atguigu.mapreduce.flow.FlowDriver;
import com.atguigu.mapreduce.flow.FlowMap;
import com.atguigu.mapreduce.flow.FlowReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class driver {
    public static void main(String[] args) throws  Exception{

        args= new String[]{"/Users/fulin/test/test6.txt","/Users/fulin/test/shuffer_comparator"};

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(driver.class);

        job.setMapperClass(map.class);
        job.setReducerClass(reduece.class);

        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        job.setGroupingComparatorClass(comparatorOrder.class);
        job.waitForCompletion(true);

    }
}
