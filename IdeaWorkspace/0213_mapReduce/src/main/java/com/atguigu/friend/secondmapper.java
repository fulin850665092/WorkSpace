package com.atguigu.friend;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class secondmapper  extends Mapper<LongWritable,Text,Text,Text> {


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //第一行：A	I,K,C,B,G,F,H,O,D,（友    人，人）
        String[] split = value.toString().split("\t");
        //友：A
        String friend=split[0];
        //人：I,K,C,B,G,F,H,O,D,
        String[] persons = split[1].split(",");
        //想得到：（人-人：友）
        //相同的k会进入同一个rudece
        for (int i = 0; i < persons.length-1; i++) {
            for (int j = i+1; j < persons.length; j++) {
             context.write(new Text(persons[i]+"-"+persons[j]),new Text(friend));
            }
        }
    }
}
