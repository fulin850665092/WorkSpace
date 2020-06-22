package com.atguigu.mapreduce.compareableAndpartition;

import com.atguigu.bean.ComparableBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class NumberReduce  extends Reducer<ComparableBean,Text ,Text,ComparableBean> {
    @Override
    protected void reduce(ComparableBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        //循环输出 避免 总流量相同的情况
        for (Text text:values
             ) {
            context.write(text,key);
        }
    }

    //    @Override
//    protected void reduce(Text key, Iterable<ComparableBean> values, Context context) throws IOException, InterruptedException {

//        long sumUpNum=0;
//        long sumDownNum=0;
//
//        for (ComparableBean numberBean:values
//             ) {
//               sumDownNum+=numberBean.getDownFlow();
//               sumUpNum+=numberBean.getUpFlow();
//        }
//        ComparableBean numberBean1=new ComparableBean();
//        numberBean1.setUpFlow(sumUpNum);
//        numberBean1.setDownFlow(sumDownNum);
//        numberBean1.setSumFlow(sumDownNum+sumUpNum);
//        context.write(key,numberBean1);


//    }
}
