package com.atguigu.mapreduce.keyvaluetest;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class KvReduce extends Reducer<Text, IntWritable,Text,IntWritable> {

    int sum=0;
    IntWritable v= new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        for (IntWritable vlaue:values
             ) {
                sum+=vlaue.get();
        }
        v.set(sum);
        context.write(key,v);
    }
}
