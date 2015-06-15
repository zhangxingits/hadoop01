package com.zx.hadoop.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

public class RPCClient {

	public static void main(String[] args) throws IOException {
		//配置代理对象
		//获取代理对象
		Bizable proxy = RPC.getProxy(Bizable.class, 10010, new InetSocketAddress("192.168.56.88",9527), new Configuration());
		//调用远程方法
		String result=proxy.sayHi("hadoop!");
		//输出
		System.out.println(result);
		//关闭远程代理
		RPC.stopProxy(proxy);
	}

}
