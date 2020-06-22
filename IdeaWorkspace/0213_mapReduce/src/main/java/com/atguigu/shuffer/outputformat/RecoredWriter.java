package com.atguigu.shuffer.outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class RecoredWriter extends RecordWriter<Text, NullWritable> {
    FileSystem fileSystem;
    FSDataOutputStream atguigu;
    FSDataOutputStream other;
    public  RecoredWriter(TaskAttemptContext taskAttemptContext)throws  Exception{
        Configuration configuration = taskAttemptContext.getConfiguration();
        fileSystem = FileSystem.get(configuration);
        atguigu= fileSystem.create(new Path("/Users/fulin/test/shuffer_outputformat/atguigu.log"));
        other = fileSystem.create(new Path("/Users/fulin/test/shuffer_outputformat/other.log"));
    }
    public void write(Text text, NullWritable nullWritable) throws IOException, InterruptedException {
        if (text.toString().contains("atguigu")){
            atguigu.write((text.toString()+"\n\r").getBytes());
        }else {
            other.write((text.toString()+"\n\r").getBytes());
        }
    }

    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        other.close();
        atguigu.close();
    }
}
