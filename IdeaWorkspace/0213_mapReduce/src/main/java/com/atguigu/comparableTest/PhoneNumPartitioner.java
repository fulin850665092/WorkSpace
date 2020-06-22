package com.atguigu.comparableTest;

import com.atguigu.bean.ComparableBean2;
//import com.atguigu.mr.writableComparable.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class PhoneNumPartitioner extends Partitioner<ComparableBean2, Text> {


    @Override
    public int getPartition(ComparableBean2 comparableBean2, Text text, int numPartitions) {
      int partitioner  ;
      String phoneNum = text.toString();
      if(phoneNum.startsWith("136")){
          partitioner = 0;
      }else if (phoneNum.startsWith("137")){
          partitioner = 1;
      }else if (phoneNum.startsWith("138")){
          partitioner = 2;
      }else if (phoneNum.startsWith("139")){
          partitioner = 3;
      }else{
          partitioner = 4 ;
      }
      return partitioner;
    }
}
