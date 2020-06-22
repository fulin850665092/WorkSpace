package com.atguigu.hdfs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

public class hdfsDemo {
	
	
	@Test
	private void testXML() throws IOException, InterruptedException, URISyntaxException {
	
		Configuration configuration = new Configuration();
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop201:9000"), configuration, "root");
		
		fileSystem.copyToLocalFile(new Path("/opt/module/hadoop-2.7.2/data/tmp/dfs/name/current/fsiage.xml"), new Path("/Users/fulin/test/下载/fsiage.xml"));
		
		fileSystem.close();
	}
	
	/**
	 * 定位读取文件128M
	 */
	@Test
	public void ssssw() throws Exception {
		//获取hdfs文件系统连接
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(new  URI("hdfs://hadoop102:9000"), configuration, "root");
		
		FSDataInputStream is = fs.open(new Path("/user/0508/hadoop-2.7.2.tar.gz"));
	   //输出流
		FileOutputStream outputStream = new FileOutputStream("/Users/fulin/test/下载/hadoop-2.7.2.tar.gz");
		//参数代表是指针的位置，定位读取128M  is.seek(1024*1024*128)
		is.seek(0);
				
		//流的 对拷
		IOUtils.copyBytes(is, outputStream, configuration);
		
		
		IOUtils.closeStream(outputStream);
		IOUtils.closeStream(is);
	}
	/**
	 * 定位读取文件
	 */
	@Test
	public void ssss() throws Exception {
		//获取hdfs文件系统连接
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(new  URI("hdfs://hadoop102:9000"), configuration, "root");
		
		FSDataInputStream is = fs.open(new Path("/user/0508/hadoop-2.7.2.tar.gz"));
	   //输出流
		FileOutputStream outputStream = new FileOutputStream("/Users/fulin/test/下载/hadoop-2.7.2.tar.gz");
		byte[] byet= new byte[1024];
		
		for (int i = 0; i < 1024*128; i++) {
			is.read(byet);
			outputStream.write(byet);
		}
		IOUtils.closeStream(outputStream);
		IOUtils.closeStream(is);
	}
	/**
	 * 基础IO流文件下载
	 * @throws URISyntaxException 
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@Test
	@SuppressWarnings("resource")
	public void name() throws Exception{
		 //获取hdfs文件系统连接
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(new  URI("hdfs://hadoop102:9000"), configuration, "root");
			//输入流
		FSDataInputStream is = fs.open(new Path("/user/0508/inoput/test3.rtf"));
		   //输出流
		FileOutputStream outputStream = new FileOutputStream("/Users/fulin/test/下载/test3.rtf");
		 //对拷
		IOUtils.copyBytes(is, outputStream, configuration);
		//关闭流
		IOUtils.closeStream(outputStream);
		IOUtils.closeStream(is);
	} 	
	/**
	 * 基础IO流上传文件
	 * @throws Exception 
	 * 
	 * @throws Exception
	 */
	@Test
	public void vod() throws Exception {
		//读取本地文件
		FileInputStream is= new FileInputStream("/Users/fulin/test/test3.rtf");
		
		//输出流写入到 hdfs
		Configuration configuration= new Configuration();
		FileSystem fSystem=FileSystem.get(new URI("hdfs://hadoop102:9000"), configuration,"root");
		FSDataOutputStream outputStream = fSystem.create(new Path("/user/0508/inoput/test3.rtf"));
		
		//对拷
		IOUtils.copyBytes(is, outputStream, configuration);
		
		//关闭流
		IOUtils.closeStream(outputStream);
		IOUtils.closeStream(is);
		
		
	}
	
	@Test
	public void tesr() throws Exception{
		// 1 获取文件配置信息
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), configuration, "root");
		testListStatusMethod("/",fs);
			
		// 3 关闭资源
		fs.close();		
	}
	/**
	 *递归判断是文件夹或者文件
	 * @throws Exception
	 */
	
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
				System.out.println("路径："+fileStatus.getPath().toString());
				System.out.println("截取的路径："+fileStatus.getPath().toString().substring("hdfs://hadoop102:9000".length()));
				testListStatusMethod(fileStatus.getPath().toString().substring("hdfs://hadoop102:9000".length()),fs);
			}
		}

	}
	/**
	 * 文件夹或者文件判断
	 * @throws Exception
	 */
	@Test
	public void testListStatusMethod() throws Exception{
			
		// 1 获取文件配置信息
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), configuration, "root");
			
		// 2 判断是文件还是文件夹
		FileStatus[] listStatus = fs.listStatus(new Path("/"));
			
		for (FileStatus fileStatus : listStatus) {
			if (fileStatus.isFile()) {
				System.out.println("file:"+fileStatus.getPath().getName());
			}else {
				System.out.println("dir"+fileStatus.getPath().getName());
				
			}
		}
			
		// 3 关闭资源
		fs.close();
	}
	/**
	 * 文件夹或者文件判断
	 * @throws Exception
	 */
	@Test
	public void testListStatus() throws Exception{
			
		// 1 获取文件配置信息
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), configuration, "root");
			
		// 2 判断是文件还是文件夹
		FileStatus[] listStatus = fs.listStatus(new Path("/"));
			
		for (FileStatus fileStatus : listStatus) {
			
			// 如果是文件
			if (fileStatus.isFile()) {
					System.out.println("f:"+fileStatus.getPath().getName());
				}else {
					System.out.println("d:"+fileStatus.getPath().getName());
			
				}
			}
			
		// 3 关闭资源
		fs.close();
	}
	/**
	 *文件详情查看
	 */
	@Test
	public void testListFiles() throws Exception{

		// 1获取文件系统
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), configuration, "root"); 
			
		// 2 获取文件详情
		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
			
		while(listFiles.hasNext()){
			LocatedFileStatus status = listFiles.next();
				
			// 输出详情
			// 文件名称
			System.out.println(status.getPath().getName());
			// 长度
			System.out.println(status.getLen());
			// 权限
			System.out.println(status.getPermission());
			// 分组
			System.out.println(status.getGroup());
				
			// 获取存储的块信息
			BlockLocation[] blockLocations = status.getBlockLocations();
				
			for (BlockLocation blockLocation : blockLocations) {
					
				// 获取块存储的主机节点
				String[] hosts = blockLocation.getHosts();
					
				for (String host : hosts) {
					System.out.println(host);
				}
			}
				
			System.out.println("-----------班长的分割线----------");
		}

	// 3 关闭资源
	fs.close();
	}
	/**
	 *更改hdfs文件的名字
	 */
	@Test
	public void  rename() throws Exception{
			
		Configuration configuration=new Configuration();
				
		FileSystem fSystem= FileSystem.get(new URI("hdfs://hadoop102:9000"),configuration,"root");
		
		
		fSystem.rename(new Path("/0508/dashenban/test3.rtf"),new Path("/0508/dashenban/tedfdf.rtf"));

		
		fSystem.close();
	}
	/**
	 *删除hdfs文件夹
	 */
	@Test
	public void  rmFileFold() throws Exception{
			
		Configuration configuration=new Configuration();
				
		FileSystem fSystem= FileSystem.get(new URI("hdfs://hadoop102:9000"),configuration,"root");
		
		//true 递归删除
		fSystem.delete(new Path("/ddd"),false);

		
		fSystem.close();
	}
	/**
	 *从HDFS下载文件
	 */
	@Test
	public void  copyToLocalFile() throws Exception{
			
		Configuration configuration=new Configuration();
		
		configuration.set("dfs.replication", "2");
		
		FileSystem fSystem= FileSystem.get(new URI("hdfs://hadoop102:9000"),configuration,"root");
		
		
//		fSystem.copyToLocalFile(new Path("/0508/dashenban/test3.rtf"),new Path("/Users/fulin/test/下载"));
		fSystem.copyToLocalFile(false,new Path("/0508/dashenban/test.rtf"),new Path("/Users/fulin/test/下载"),true);

		
		fSystem.close();
	}
	
	/**
	 *上传文件到HDFS
	 */
	@Test
	public void  testCopyFromLocal() throws Exception{
			
		Configuration configuration=new Configuration();
		
		configuration.set("dfs.replication", "2");
		
		FileSystem fSystem= FileSystem.get(new URI("hdfs://hadoop102:9000"),configuration,"root");
		
		
		fSystem.copyFromLocalFile(new Path("/Users/fulin/test/test3.rtf"), new Path("/0508/dashenban"));
		
		fSystem.close();
	}
	
	/**
	 * 打通客户端与HDFS的连接
	 * @throws Exception 
	 * 
	 */
	@Test
	public void testClientConnectionHDFS() throws Exception {
		
		Configuration conf= new Configuration();
		
		FileSystem fs= FileSystem.get(new URI("hdfs://hadoop102:9000"), conf, "root");
		
		fs.mkdirs(new Path("/0508/dashenban"));
		
		fs.close();
		
	}

}
