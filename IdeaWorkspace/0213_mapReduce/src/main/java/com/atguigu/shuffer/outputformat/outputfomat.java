package com.atguigu.shuffer.outputformat;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class outputfomat extends FileOutputFormat<Text, NullWritable> {
    public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        RecoredWriter recoredWriter= null;
        try {
            recoredWriter = new RecoredWriter(taskAttemptContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recoredWriter;
    }
}
