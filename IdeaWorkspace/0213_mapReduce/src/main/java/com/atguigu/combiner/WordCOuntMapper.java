package com.atguigu.combiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCOuntMapper  extends Mapper<LongWritable, Text,Text, IntWritable> {

    Text text= new Text();
    IntWritable intWritable=new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //1。获取一行
        String line= value.toString();
        //2。按空格切割
        String[] splits = line.split(" ");

        for (String string:splits) {
            //  转为 text
            text.set(string);
            //每有个索引，调用一次map
            context.write(text,intWritable);
        }

    }
}
