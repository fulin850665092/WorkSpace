package com.atguigu.shuffer.patitioner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class map extends Mapper<LongWritable, Text, ComparableBean,Text> {

    Text v= new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1	13736230513	192.196.100.1	www.atguigu.com	2481	24681	200
        String line = value.toString();
        String[] split = line.split("\t");
        v.set(split[1]);
        ComparableBean comparableBean=new ComparableBean();
        comparableBean.setUpFlow(Long.parseLong(split[split.length-3]));
        comparableBean.setDownFlow(Long.parseLong(split[split.length-2]));
//        comparableBean.setSumFlow(Long.parseLong(split[split.length-3])+Long.parseLong(split[split.length-2]));
       comparableBean.set();
        context.write(comparableBean,v);
    }
}
