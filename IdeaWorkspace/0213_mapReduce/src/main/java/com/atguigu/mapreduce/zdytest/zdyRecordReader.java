package com.atguigu.mapreduce.zdytest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class zdyRecordReader extends RecordReader {
    private FileSplit fileSplit;
    private Configuration configuration;
    private Text currentKey= new Text();
    private BytesWritable currentValue=new BytesWritable();
    private boolean flag=true;
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        fileSplit= (FileSplit) split;
        configuration = context.getConfiguration();
    }

    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (flag) {
            Path path = fileSplit.getPath();
            FileSystem fs = path.getFileSystem(configuration);

            FSDataInputStream open = fs.open(path);

            //自定义缓冲区
            byte[] bytes = new byte[(int) fileSplit.getLength()];
            IOUtils.readFully(open, bytes, 0, bytes.length);

            //设置value
            currentValue.set(bytes, 0, bytes.length);
            //设置key
            currentKey.set(path.toString());
            flag=false;
            return true;
        }
        return false;
    }

    public Object getCurrentKey() throws IOException, InterruptedException {
        return currentKey;
    }

    public Object getCurrentValue() throws IOException, InterruptedException {
        return currentValue;
    }

    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    public void close() throws IOException {

    }
}
