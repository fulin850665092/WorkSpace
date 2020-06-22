package com.atguigu.flume.gulivideo.rtl;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class EtlMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    Text text= new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line= value.toString();
        String str = EtlUtils.getStr(line);
        if (str==null){
            return;
        }
        text.set(str);
        context.write(text,NullWritable.get());
    }
}
