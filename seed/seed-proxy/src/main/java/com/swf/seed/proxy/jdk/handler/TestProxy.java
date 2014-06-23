package com.swf.seed.proxy.jdk.handler;

import java.lang.reflect.Proxy;

import com.swf.seed.proxy.jdk.Service;
import com.swf.seed.proxy.jdk.ServiceIF;

public class TestProxy {
	public static void main(String[] args) {
		Service realService = new Service();
		ServiceIF proxy = 	(ServiceIF)Proxy.newProxyInstance(realService.getClass().getClassLoader(), realService.getClass().getInterfaces(), new DynamicProxyInvocationHandler(realService));
		proxy.doService();
	}
}
