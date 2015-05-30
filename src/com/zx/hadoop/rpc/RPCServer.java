package com.zx.hadoop.rpc;

import java.io.IOException;

import javax.ws.rs.core.NewCookie;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

public class RPCServer implements Bizable {

	public String sayHi(String string) {
		return "Hi~!"+string;
	}
	
	public static void main(String[] args) throws HadoopIllegalArgumentException, IOException {
		Configuration conf=new Configuration();
		Server server = new RPC.Builder(conf).setProtocol(Bizable.class).setInstance(new RPCServer()).setBindAddress("192.168.8.88").setPort(9527).build();

	}

}
