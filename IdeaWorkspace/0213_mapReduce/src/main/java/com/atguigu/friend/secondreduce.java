package com.atguigu.friend;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class secondreduce extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuffer sb = new StringBuffer();
        //得到的key（ren-ren），value（共同好友）
        for (Text value : values) {
            //想要的结果（人-人 友，友）
            sb.append(value).append(" ");
        }
        context.write(key,new Text(sb.toString()));
    }
}
