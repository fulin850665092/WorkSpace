package com.atguigu.outputreducejoin;

import com.atguigu.bean.OrderBean;
import com.atguigu.bean.OrderJoinBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class reducejoinMapper extends Mapper<LongWritable, Text, Text, OrderJoinBean> {

    private  String name;
    OrderJoinBean order= new OrderJoinBean();
    Text k= new Text();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        name = fileSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split("\t");

        //对不同数据来源分开处理
        if (name.contains("order")) {
            k.set(fields[1]);
            order.setId(fields[0]);
            order.setPid(fields[1]);
            order.setAmount(Integer.parseInt(fields[2]));
            order.setPname("");
            order.setFlag("order");
        } else if (name.contains("pd")){
            k.set(fields[0]);
            order.setPid(fields[0]);
            order.setPname(fields[1]);
            order.setAmount(0);
            order.setId("");
            order.setFlag("pd");
        }
        context.write(k,order);
    }
}
