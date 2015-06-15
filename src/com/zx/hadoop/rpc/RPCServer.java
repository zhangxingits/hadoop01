package com.zx.hadoop.rpc;

import java.io.IOException;

import javax.ws.rs.core.NewCookie;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

public class RPCServer implements Bizable {

	public String sayHi(String string) {
		return "Hi~~!"+string;
	}
	
	public static void main(String[] args) throws HadoopIllegalArgumentException, IOException {
		Configuration conf=new Configuration();
		// setProtocol 设置客户端和远程服务端共同实现的接口
		// setInstance 设置远程服务端实现调用方法的代理对象
		// setBindAddress 设置服务端地址为本机Windows上的地址
		// setPort 设置端口
		
		Server server = new RPC.Builder(conf).setProtocol(Bizable.class).setInstance(new RPCServer())
				.setBindAddress("192.168.56.88").setPort(9527).build();
		server.start();
	}

}
