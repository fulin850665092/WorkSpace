package com.atguigu.mapreduce.compareableAndpartition;

import com.atguigu.bean.ComparableBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class NumberMapper extends Mapper<LongWritable, Text,ComparableBean,Text > {

    Text k= new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line= value.toString();
        String[] split = line.split("\t");

        k.set(split[1]);
        ComparableBean bean= new ComparableBean();
        bean.setUpFlow(Long.parseLong(split[split.length-3]));
        bean.setDownFlow(Long.parseLong(split[split.length-2]));
        bean.setSumFlow(Long.parseLong(split[split.length-3])+Long.parseLong(split[split.length-2]));
        context.write(bean,k);
    }
}
