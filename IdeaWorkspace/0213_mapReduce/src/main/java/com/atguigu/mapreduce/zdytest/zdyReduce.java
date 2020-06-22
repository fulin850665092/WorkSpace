package com.atguigu.mapreduce.zdytest;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class zdyReduce extends Reducer<Text, BytesWritable,Text,BytesWritable> {

    @Override
    protected void reduce(Text key, Iterable<BytesWritable> values, Context context) throws IOException, InterruptedException {

        for (BytesWritable bytesWritable:values
             ) {
                context.write(key,bytesWritable);
        }    }
}
