package com.atguigu.join;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class mapjoin extends Mapper<LongWritable, Text,Text, NullWritable> {

    Map<String,String> ppdMap=new HashMap<String, String>();
    Text text=new Text();
    /**
     * map之前跳用一次
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        Configuration configuration = context.getConfiguration();
        URI[] uris = context.getCacheArchives();
        Path path = new Path(uris[0]);
        FileSystem fileSystem = FileSystem.get(configuration);
        FSDataInputStream open = fileSystem.open(path);
        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(open,"utf-8"));
        //按行读取
        String line;
        while ((line=bufferedReader.readLine())!=null){
            String[] split = line.split("\t");
            ppdMap.put(split[0],split[1]);
        }
        bufferedReader.close();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line=value.toString();
        //读取order的表数据一行
        String[] split = line.split("\t");
        String pname=ppdMap.get(split[1]);
        String k=split[0]+"\t"+pname+"\t"+split[2];
        text.set(k);
        context.write(text,NullWritable.get());
    }
}
