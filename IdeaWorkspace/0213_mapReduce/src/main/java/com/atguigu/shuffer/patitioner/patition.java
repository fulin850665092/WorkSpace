package com.atguigu.shuffer.patitioner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class patition  extends Partitioner<ComparableBean, Text> {
    public int getPartition(ComparableBean comparableBean, Text text, int i) {
        int patition=0;
        if (text.toString().startsWith("136")){
            patition=0;
        }else if (text.toString().startsWith("137")){
            patition=1;
        }else if (text.toString().startsWith("138")){
            patition=2;
        }else{
            patition=3;
        }

        return patition;
    }
}
