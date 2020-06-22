package com.atguigu.mapreduce.comparableTest;

import com.atguigu.bean.ComparableBean2;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class reduce extends Reducer<ComparableBean2, Text,Text,ComparableBean2> {

    @Override
    protected void reduce(ComparableBean2 key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        for (Text value : values) {

            context.write(value,key);
        }
    }
}
