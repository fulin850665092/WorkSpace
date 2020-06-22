package com.atguigu.friend;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class fristmapper extends Mapper<LongWritable, Text,Text,Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //第一行：A:B,C,D,F,E,O
        String[] line = value.toString().split(":");
        //人：A
        String person = line[0];
        //友；B,C,D,F,E,O
        String friStr=line[1];
        //想得到：(友,人)；
        String[] friends = friStr.split(",");
        for (String friend : friends) {
            context.write(new Text(friend),new Text(person));
        }


    }
}
