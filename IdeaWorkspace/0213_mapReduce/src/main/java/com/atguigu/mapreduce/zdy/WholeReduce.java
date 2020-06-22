package com.atguigu.mapreduce.zdy;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WholeReduce extends Reducer<Text, BytesWritable,Text,BytesWritable> {

    @Override
    protected void reduce(Text key, Iterable<BytesWritable> values, Context context) throws IOException, InterruptedException {

        for (BytesWritable bytesWritable:values
             ) {
            context.write(key,bytesWritable);
        }
    }
}
