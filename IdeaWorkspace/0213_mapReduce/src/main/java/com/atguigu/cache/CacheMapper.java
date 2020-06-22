package com.atguigu.cache;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
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

public class CacheMapper extends Mapper<LongWritable, Text, Text,NullWritable> {

    private Map<String,String> pdMap=new HashMap<String, String>();
    private  Text line= new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        URI[] uris = context.getCacheArchives();
        Path path = new Path(uris[0]);

        FileSystem fs = path.getFileSystem(context.getConfiguration());
        FSDataInputStream ds = fs.open(path);

        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(ds,"utf-8"));

        String line;
        while (StringUtils.isNotEmpty(line=bufferedReader.readLine())){

            String[] split = line.split("\t");

            pdMap.put(split[0],split[1]);

        }
        IOUtils.closeStream(bufferedReader);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] split = value.toString().split("\t");

        String pname = pdMap.get(split[1]);

        line.set(split[0]+"\t"+pname+"\t"+split[2]);

        context.write(line, NullWritable.get());


    }
}
