package com.atguigu.zdy;

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

public class WholeRecordReader extends RecordReader<Text, BytesWritable> {

   private FileSplit fileSplit;
   private  Configuration configuration;
   private  Text currentKey=new Text();
   private BytesWritable currentValue= new BytesWritable();
   private  boolean flag=true;


    /**
     * 初始化方法
     * @param split
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {

       fileSplit= (FileSplit) split;
       configuration =context.getConfiguration();


    }

    public boolean nextKeyValue() throws IOException, InterruptedException {

        if (flag){

            //获取文件名
            String name = fileSplit.getPath().toString();

            //获取文件系统
            FileSystem fs= FileSystem.get(configuration);
            //获取输入流
            FSDataInputStream is = fs.open(new Path(name));

            //安照切片大小读
            byte[] bytes=new byte[(int) fileSplit.getLength()];
            //读取文件
            IOUtils.readFully(is,bytes,0,bytes.length);

            currentKey.set(name);
            currentValue.set(bytes,0,bytes.length);

            flag=false;
            return  true;

        }

        return false;
    }

    public Text getCurrentKey() throws IOException, InterruptedException {

        return currentKey;
    }

    public BytesWritable getCurrentValue() throws IOException, InterruptedException {

        return currentValue;
    }

    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    public void close() throws IOException {

    }
}
