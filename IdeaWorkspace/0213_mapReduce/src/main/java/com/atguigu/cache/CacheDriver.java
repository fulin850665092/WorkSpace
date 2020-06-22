package com.atguigu.cache;

import com.atguigu.bean.OrderJoinBean;
import com.atguigu.mapreduce.outputreducejoin.joinDriver;
import com.atguigu.mapreduce.outputreducejoin.reducejoinMapper;
import com.atguigu.mapreduce.outputreducejoin.reducejoinReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.net.URI;

public class CacheDriver {

    public static void main(String[] args) throws  Exception{
        // 输入输出路径需要根据自己电脑上实际的输入输出路径设置
        args=new String[]{"/Users/fulin/test/mapjoin/order.txt","/Users/fulin/test/mapjointest"};

        // 1 获取配置信息
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 2 设置jar包加载路径
        job.setJarByClass(CacheDriver.class);

        // 3 加载map/reduce类
        job.setMapperClass(CacheMapper.class);
//        job.setReducerClass(reducejoinReduce.class);

        // 4 设置map输出数据key和value类型
//        job.setMapOutputKeyClass(Text.class);
//        job.setMapOutputValueClass(OrderJoinBean.class);

        // 5 设置最终输出数据的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        // 6 设置输入数据和输出数据路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //加载缓存文件
        job.addCacheArchive(new URI("/Users/fulin/test/mapjoin/pd.txt"));
        //设置redunceTask的数量为0（mapjoin不需要reduce阶段）
        job.setNumReduceTasks(0);

        // 7 提交
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);


    }
}
