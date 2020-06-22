package com.atguigu.mapreduce.zdytest;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

public class zdyFileInputformat extends FileInputFormat {
    @Override
    protected boolean isSplitable(JobContext context, Path filename) {
        return  false;
    }

    public RecordReader createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {

        zdyRecordReader recordReader= new zdyRecordReader();
        recordReader.initialize(split,context);
        return recordReader;
    }
}
