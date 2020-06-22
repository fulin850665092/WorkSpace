package com.atguigu.mapreduce.flow;

import com.atguigu.bean.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


public class FlowReduce extends Reducer<Text,FlowBean,Text,FlowBean> {

    FlowBean flowBean= new FlowBean();
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {

        long totalUpFlow=0;
        long totalDownFlow=0;
        long totalSumFlow=0;
        for (FlowBean flowBean:values){
            totalDownFlow+=flowBean.getDownFlow();
            totalUpFlow+=flowBean.getUpFlow();
            totalSumFlow= flowBean.getSumFlow();
        }

        flowBean.setDownFlow(totalDownFlow);
        flowBean.setUpFlow(totalUpFlow);
        flowBean.setSumFlow(totalSumFlow);

        context.write(key,flowBean);


    }
}
