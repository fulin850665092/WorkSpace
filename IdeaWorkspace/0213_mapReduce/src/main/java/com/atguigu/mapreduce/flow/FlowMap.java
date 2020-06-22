package com.atguigu.mapreduce.flow;

import com.atguigu.bean.FlowBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMap extends Mapper<LongWritable, Text,Text, FlowBean> {


     Text textK=new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line=value.toString();

        String[] split = line.split("\t");

        String k=split[1];
        textK.set(k);

        String upFlow=split[split.length-3];
        String downFlow=split[split.length-2];
        FlowBean flowBean= new FlowBean();
        flowBean.setUpFlow(Long.parseLong(upFlow));
        flowBean.setDownFlow(Long.parseLong(downFlow));
        flowBean.setSumFlow(Long.parseLong(upFlow)+Long.parseLong(downFlow));

        context.write(textK,flowBean);

    }
}
