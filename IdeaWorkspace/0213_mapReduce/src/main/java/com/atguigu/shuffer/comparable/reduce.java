package com.atguigu.shuffer.comparable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class reduce extends Reducer<ComparableBean, Text,Text,ComparableBean> {

    @Override
    protected void reduce(ComparableBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        for (Text value : values) {
            context.write(value,key);
        }
    }
}
