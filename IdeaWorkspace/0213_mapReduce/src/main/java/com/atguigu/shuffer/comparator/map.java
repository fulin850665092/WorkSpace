package com.atguigu.shuffer.comparator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class map extends Mapper<LongWritable, Text,OrderBean, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            //yihang
        String line = value.toString();
        String[] split = line.split("\t");
        OrderBean orderBean=new OrderBean();
        orderBean.setOrder_id(split[0]);
        orderBean.setPrice(Double.parseDouble(split[2]));
        context.write(orderBean,NullWritable.get());
    }
}
