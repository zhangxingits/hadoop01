package com.zx.hadoop.hdfs;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class HdfsDemo {
	
	FileSystem fileSystem=null;
	
	//在执行 @Test 之前先执行初始化
	@Before
	public void init() throws IOException, InterruptedException, URISyntaxException {
		//伪装成root用户
		fileSystem=FileSystem.get(new URI("hdfs://192.168.1.168:9000"), new Configuration(),"root");
		
	}
	@Test
	public void testUpload() throws IllegalArgumentException, IOException {
		//读取本地文件系统的文件，返回输入流
		InputStream inputStream=new FileInputStream("G:\\UU\\FXFK.rar");
		//在hdfs创建一个文件，返回输出流
		OutputStream outputStream=fileSystem.create(new Path("/fxfk.rar"));
		//输入->输出
		IOUtils.copyBytes(inputStream, outputStream, 4096, true);
	}
	@Test
	public void downLoad() throws IllegalArgumentException, IOException {
		fileSystem.copyToLocalFile(true, new Path("/fxfk.rar"), new Path("g://f.tar"));
	}
	@Test
	public void delTest() throws IllegalArgumentException, IOException{
		//当目录不空时不可删除
		boolean flag = fileSystem.delete(new Path("/dirs"), true);
		System.out.println(flag);
	}
	@Test
	public void testMkdir() throws IllegalArgumentException, IOException {
		//添加目录
		boolean flag = fileSystem.mkdirs(new Path("/dirs"));
		System.out.println(flag);
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		FileSystem fileSystem=FileSystem.get(new URI("hdfs://192.168.1.168:9000"), new Configuration());
		InputStream inputStream=fileSystem.open(new Path("/had"));
		OutputStream outputStream = new FileOutputStream("G://hda2.6");
		IOUtils.copyBytes(inputStream, outputStream, 4096, true);
	}

}
