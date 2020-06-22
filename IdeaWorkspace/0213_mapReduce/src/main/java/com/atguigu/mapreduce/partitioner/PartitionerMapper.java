package com.atguigu.mapreduce.partitioner;

import com.atguigu.bean.NumberBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PartitionerMapper extends Mapper<LongWritable,Text,Text, NumberBean> {

    Text k= new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {


        String line=value.toString();
        String[] split = line.split("\t");
        k.set(split[1]);

        NumberBean bean= new NumberBean();
        bean.setUpNum(Long.parseLong(split[split.length-3]));
        bean.setDownNum(Long.parseLong(split[split.length-2]));
        bean.setSumNum(Long.parseLong(split[split.length-2])+Long.parseLong(split[split.length-3]));

        context.write(k,bean);
    }
}
