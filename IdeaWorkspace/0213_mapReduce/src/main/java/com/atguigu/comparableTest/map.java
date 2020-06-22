package com.atguigu.comparableTest;

import com.atguigu.bean.ComparableBean2;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class map extends Mapper<LongWritable, Text, ComparableBean2,Text> {

    Text k= new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        ComparableBean2 comparableBean2= new ComparableBean2();
        String line= value.toString();
        String[] split = line.split("\t");
        comparableBean2.setDownFlow(Long.parseLong(split[split.length-3]));
        comparableBean2.setUpFlow(Long.parseLong(split[split.length-2]));
        comparableBean2.setSumFlow(Long.parseLong(split[split.length-3])+Long.parseLong(split[split.length-2]));
        k.set(split[1]);
        context.write(comparableBean2,k);
    }
}
