package com.atguigu.hbase.mrHdfs;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.io.StringWriter;

public class map extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] strs = value.toString().split("\t");

        if (strs.length<3){
            return;
        }
        Put put = new Put(Bytes.toBytes(strs[0]));
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("name"),Bytes.toBytes(strs[1]));
        put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("sex"),Bytes.toBytes(strs[2]));
        context.write(new ImmutableBytesWritable(Bytes.toBytes(strs[0])),put);
    }
}
