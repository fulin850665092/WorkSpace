package com.atguigu.mapreduce.outputfile;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class RecordWriterZdy extends RecordWriter<Text, NullWritable> {

    FSDataOutputStream fsDataOutputStream = null;
    FSDataOutputStream fsDataOutputStream1 = null;

    public RecordWriterZdy(TaskAttemptContext job) throws  Exception{
        //获取文件系统
        FileSystem fs = FileSystem.get(job.getConfiguration());
        //创建输出文件路径
        Path atguiguPath = new Path("/Users/fulin/test/log/atguigu.log");
        Path otherPath = new Path("/Users/fulin/test/log/other.log");
        //创建输出流
        fsDataOutputStream = fs.create(atguiguPath);
        fsDataOutputStream1 = fs.create(otherPath);

    }

    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        if (key.toString().contains("atguigu")){
            fsDataOutputStream.write(key.toString().getBytes());
        }else {
            fsDataOutputStream1.write(key.toString().getBytes());
        }


    }

    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(fsDataOutputStream1);
        IOUtils.closeStream(fsDataOutputStream);
    }
}
