package com.atguigu.mapreduce.flowtest;

import com.atguigu.bean.NumberBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class NumberReduce  extends Reducer<Text, NumberBean,Text,NumberBean> {

    @Override
    protected void reduce(Text key, Iterable<NumberBean> values, Context context) throws IOException, InterruptedException {

        long sumUpNum=0;
        long sumDownNum=0;

        for (NumberBean numberBean:values
             ) {
               sumDownNum+=numberBean.getSumNum();
               sumUpNum+=numberBean.getUpNum();
        }
        NumberBean numberBean1=new NumberBean();
        numberBean1.setUpNum(sumUpNum);
        numberBean1.setDownNum(sumDownNum);
        numberBean1.setSumNum(sumDownNum+sumUpNum);
        context.write(key,numberBean1);
    }
}
