package com.atguigu.mapreduce.wordcounttest;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class wordtestReduce extends Reducer<Text , IntWritable,Text,IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {


        for (IntWritable value:values
             ) {
            context.write(key,value);

        }

    }
}
