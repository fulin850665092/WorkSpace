package com.atguigu.decod;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.file.tfile.Compression;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

public class decodeTest {
    /**
     * 压缩文件
     * @throws Exception
     */
    @Test
    public  void Test() throws  Exception{
        String inpath="";
        String outpath="";
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(configuration);
        //输入流
        FSDataInputStream in = fs.open(new Path(inpath));
        //输出流
        FSDataOutputStream out = fs.create(new Path(outpath));
        //压缩类
        String calssname="";
        //包装输出流的编解码
        Class<?> forName = Class.forName(calssname);
        CompressionCodec compressionCodec=(CompressionCodec) ReflectionUtils.newInstance(forName, configuration);
        CompressionOutputStream outDe = compressionCodec.createOutputStream(out);

        IOUtils.copyBytes(in,outDe,configuration);

        IOUtils.closeStream(outDe);
        IOUtils.closeStream(in);
    }

    /**
     * 解压文件
     */
    @Test
    public  void Test2() throws Exception{
        String inpath="";
        String outpath="";
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(configuration);
//        Compression compression= new Compression(new Path(inpath));


    }
}
