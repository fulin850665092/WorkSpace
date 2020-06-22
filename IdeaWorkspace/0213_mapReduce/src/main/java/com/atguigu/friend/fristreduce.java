package com.atguigu.friend;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class fristreduce  extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        //拿到的是相同的key（友），对应的values（人）
        StringBuffer br = new StringBuffer();
        //想要的结果，友 人，人
        for (Text value : values) {
            br.append(value).append(",");
        }
        context.write(key,new Text(br.toString()));
    }
}
