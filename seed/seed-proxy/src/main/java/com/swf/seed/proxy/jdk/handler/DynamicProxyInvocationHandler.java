package com.swf.seed.proxy.jdk.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyInvocationHandler implements InvocationHandler {
	private Object realService = null;
	
	public DynamicProxyInvocationHandler(Object service) {
		this.realService = service;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		System.out.println("你正在申请服务："+method.getName());
		try {
			result = method.invoke(realService, args);
		} catch (Exception e) {
			System.exit(-1);
		}
		System.out.println("您申请的服务"+method.getName()+"完成");
		return result;
	}

}
