package com.atguigu.codec;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MapOutCodecWordCountDriver {

    public static void main(String[] args) throws  Exception {

        args=new String[]{"/Users/fulin/test/test.txt","/Users/fulin/test/mapoutcodec"};

        //1.获取连接以及封装任务
        Configuration configuration = new Configuration();

        //开启map输出端压缩
        configuration.setBoolean("mapreduce.map.output.compress",true);
        //设置map端输出压缩的方式
        configuration.setClass("mapreduce.map.output.compress.codec", BZip2Codec.class,CompressionCodec.class);


        Job job = Job.getInstance(configuration);
        //2设置jar的加载路径
        job.setJarByClass(MapOutCodecWordCountDriver.class);
        //3.设置map和reduce类
        job.setMapperClass(WordCOuntMapper.class);
        job.setReducerClass(WordCountReduce.class);
        //4设置map的输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //5设置最终输出的kv的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //6.设置输入和输出的路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //7提交
        boolean result = job.waitForCompletion(true);
        System.out.println(result ? 0 :1);

    }
}
