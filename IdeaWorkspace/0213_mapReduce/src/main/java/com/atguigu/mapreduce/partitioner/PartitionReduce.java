package com.atguigu.mapreduce.partitioner;

import com.atguigu.bean.NumberBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PartitionReduce extends Reducer<Text, NumberBean,Text, NumberBean> {

    @Override
    protected void reduce(Text key, Iterable<NumberBean> values, Context context) throws IOException, InterruptedException {
        long sumUpNum=0;
        long sumDownNum=0;
        NumberBean numberBean= new NumberBean();
        for (NumberBean bean:values
             ) {
            sumUpNum+=bean.getUpNum();
            sumDownNum+=bean.getDownNum();
        }
        numberBean.setSumNum(sumDownNum+sumUpNum);
        context.write(key,numberBean);
    }
}
