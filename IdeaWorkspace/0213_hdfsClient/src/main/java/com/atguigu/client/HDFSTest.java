package com.atguigu.client;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.Arrays;

public class HDFSTest {

    Configuration configuration=null;
    FileSystem fileSystem=null;

      @Before
    public  void  getContection() throws  Exception{
        configuration= new Configuration();
        fileSystem=FileSystem.get(new URI("hdfs://hadoop102:8020"),configuration,"atguigu");
    }
    @After
    public void closeContection() throws  Exception{
        fileSystem.close();
    }
    @Test
    public  void testMkdir() throws  Exception{

        Configuration con = new Configuration();

        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"),con,"atguigu");

        boolean mkdirs = fs.mkdirs(new Path("/test1"));

        fs.close();
    }
    /**
     *上传文件到HDFS1
     * 不设置ref（副本数），设置集群默认配置
     */
    @Test
    public void  testCopyFromLocal() throws Exception{

        Configuration configuration=new Configuration();


        FileSystem fSystem= FileSystem.get(new URI("hdfs://hadoop102:8020"),configuration,"atguigu");


        fSystem.copyFromLocalFile(new Path("/Users/fulin/test/test.txt"), new Path("/test/test.txt"));

        fSystem.close();
    }
    /**
     *上传文件到HDFS2
     * xml配置ref，程序代码不设置，使用xml配置的副本数
     */
    @Test
    public void  testCopyFromLocalXmlRep() throws Exception{

        Configuration configuration=new Configuration();

        FileSystem fSystem= FileSystem.get(new URI("hdfs://hadoop102:8020"),configuration,"atguigu");


        fSystem.copyFromLocalFile(new Path("/Users/fulin/test/test.txt"), new Path("/test/test1.txt"));

        fSystem.close();
    }
    /**
     *上传文件到HDFS3
     * xml配置ref，程序同样设置ref，使用程序代码配置的副本数（配置优先级：程序代码>xml配置>集群（默认）配置）
     */
    @Test
    public void  testCopyFromLocalTwoRep() throws Exception{

        Configuration configuration=new Configuration();

        configuration.set("dfs.replication", "2");

        FileSystem fSystem= FileSystem.get(new URI("hdfs://hadoop102:8020"),configuration,"atguigu");


        fSystem.copyFromLocalFile(new Path("/Users/fulin/test/test.txt"), new Path("/test/test2.txt"));

        fSystem.close();
    }
    /**
     * 文件下载
     */
    @Test
    public  void down() throws  Exception{
        fileSystem.copyToLocalFile(true,new Path("/test/test.txt"),new Path("/Users/fulin/test10/test10.txt"));
    }
    /**
     * 文件删除
     */
    @Test
    public  void delete() throws  Exception{
        fileSystem.delete(new Path("/test/test.txt"));
    }
    /**
     * 文件夹删除
     */
    @Test
    public  void deleteDir() throws  Exception{
        fileSystem.delete(new Path("/test/"),true);
    }
    /**
     * 修改文件名
     */
    @Test
    public  void rename() throws  Exception{
        fileSystem.rename(new Path("/test/test.txt"),new Path("/test/test2.txt"));
    }
    /**
     * 修改文件名并移动
     */
    @Test
    public  void renameAndMove() throws  Exception{
        fileSystem.rename(new Path("/test/test2.txt"),new Path("/test4.txt"));
    }
    /**
     * 文件详情查看
     */
    @Test
    public  void viewDetil() throws  Exception{

        RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path("/"), true);

        while (listFiles.hasNext()){
            //取下一个
            LocatedFileStatus status = listFiles.next();
            //每个文件的详情
            System.out.println(status.getPermission()+"\t"
                    +status.getGroup()+"\t"
                    +status.getOwner()+"\t"
                    +status.getLen()+"\t"
                    +status.getReplication()+"\t"
                    +status.getPath().toString()+"\t"
                    +status.getPath().getName());
            //当前文件的快信息
            BlockLocation[] blockLocations = status.getBlockLocations();
            System.out.println(Arrays.toString(blockLocations));
            System.out.println("===================");
        }
    }
    /**
     * 文件夹还是文件的判断
     */
    @Test
    public  void isDreOrFile() throws  Exception{
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/"));

        for (FileStatus status:fileStatuses
             ) {
            if (status.isDirectory()){
                System.out.println(status.getPath().getName().toString()+"是一个目录");
            }else {
                System.out.println(status.getPath().getName().toString()+"是一个文件");
            }
        }
    }

    /**
     *递归判断是文件夹或者文件
     * @throws Exception
     */
    @Test
    public void tesr() throws Exception{

        testListStatusMethod("/",fileSystem);

    }
    public void testListStatusMethod(String dir,FileSystem fs) throws Exception{



        // 2 判断是文件还是文件夹
        FileStatus[] listStatus = fs.listStatus(new Path(dir));

        for (FileStatus fileStatus : listStatus) {
            if (fileStatus.isFile()) {
                if (dir.equals("/")) {
                    System.out.println("file:"+dir+fileStatus.getPath().getName());
                }else {
                    System.out.println("file:"+dir+"/"+fileStatus.getPath().getName());
                }
            }else {
                System.out.println("dir:"+fileStatus.getPath().getName());
                 System.out.println("--------");
//                System.out.println("路径："+fileStatus.getPath().toString());
//                System.out.println("截取的路径："+fileStatus.getPath().toString().substring("hdfs://hadoop102:9000".length()));
                testListStatusMethod(fileStatus.getPath().toString().substring("hdfs://hadoop102:9000".length()),fs);
            }
        }

    }

    /**
     * 基于io实现文件的上传和下载
     */
    @Test
    public  void upload() throws  Exception{

        String src= "/Users/fulin/test/test.txt";

        FileInputStream fileInputStream = new FileInputStream(new File(src));

        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("/test.txt"));

        IOUtils.copyBytes(fileInputStream,fsDataOutputStream,configuration);

        IOUtils.closeStream(fsDataOutputStream);
        IOUtils.closeStream(fileInputStream);

    }
    /**
     * 基于io实现文件的下载
     */
    @Test
    public  void downFile() throws  Exception{

        FSDataInputStream fsDataInputStream = fileSystem.open(new Path("/test.txt"));


        String src= "/Users/fulin/test/test00.txt";

        FileOutputStream fileOutputStream = new FileOutputStream(new File(src));

        IOUtils.copyBytes(fsDataInputStream,fileOutputStream,configuration);

        IOUtils.closeStream(fileOutputStream);
        IOUtils.closeStream(fileOutputStream);

    }
    /**
     * 定位读取文件128M
     */
    @Test
    public void ssssw() throws Exception {


        FSDataInputStream is = fileSystem.open(new Path("/user/0508/hadoop-2.7.2.tar.gz"));
        //输出流
        FileOutputStream outputStream = new FileOutputStream("/Users/fulin/test/下载/hadoop-2.7.2.tar.gz");
        //参数代表是指针的位置，定位读取128M  is.seek(1024*1024*128)
        is.seek(0);

        //流的 对拷
        IOUtils.copyBytes(is, outputStream, configuration);


        IOUtils.closeStream(outputStream);
        IOUtils.closeStream(is);
    }
}
