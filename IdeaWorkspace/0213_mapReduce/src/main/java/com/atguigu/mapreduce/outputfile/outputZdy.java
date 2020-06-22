package com.atguigu.mapreduce.outputfile;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class outputZdy extends FileOutputFormat<Text, NullWritable> {
    public RecordWriter getRecordWriter(TaskAttemptContext job) {
        RecordWriterZdy recordWriterZdy = null;

        try {
            recordWriterZdy=new RecordWriterZdy(job);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return recordWriterZdy ;

    }
}

