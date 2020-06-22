package com.atguigu.mapreduce.keyvaluetest;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class KvMapper  extends Mapper<Text,Text,Text, IntWritable> {

    IntWritable v= new IntWritable(1);
    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {

        context.write(key,v);
    }
}
