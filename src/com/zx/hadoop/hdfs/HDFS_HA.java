package com.zx.hadoop.hdfs;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;



public class HDFS_HA {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://ns1");
		conf.set("dfs.nameservices", "ns1");
		conf.set("dfs.ha.namenodes.ns1", "nn1,nn2");
		conf.set("dfs.namenode.rpc-address.ns1.nn1", "cent08:9000");
		conf.set("dfs.namenode.rpc-address.ns1.nn2", "cent07:9000");
		conf.set("dfs.client.failover.proxy.provider.ns1", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");

		FileSystem fs = FileSystem.get(new URI("hdfs://ns1"), conf, "root");
		//下载
		InputStream in = fs.open(new Path("/log"), 4096);
		OutputStream out = new FileOutputStream("C:\\Users\\新\\Desktop\\log.txt");
		//上传
//		InputStream in =new FileInputStream("C:\\Users\\新\\Desktop\\hadoop2.6.0.txt");
//		OutputStream out = fs.create(new Path("/hadoop2.6.0.txt"));
		IOUtils.copyBytes(in, out, 4096, true);
	}

}
