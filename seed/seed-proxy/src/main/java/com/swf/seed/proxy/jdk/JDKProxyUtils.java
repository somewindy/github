package com.swf.seed.proxy.jdk;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class JDKProxyUtils {

	/**
	 * 获取所有的方法
	 * @param c
	 * @return
	 */
	public static Set<String> getMethod(Class<?> c) {
		Method m[] = c.getDeclaredMethods();
		Set<String> set = new TreeSet<String>();
		for (int i = 0; i < m.length; i++)
			set.add(m[i].getName());
		return set;
	}

	/**
	 * 获取类的方法名称
	 * @param c
	 * @return
	 */
	private static List<String> getMethodName(Class<?> c) {
		Method m[] = c.getDeclaredMethods();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < m.length; i++)
			list.add(m[i].toString());
		return list;
	}

	public static void main(String[] args) {
		System.out.println(JDKProxyUtils.getMethod(String.class));
		System.out.println(JDKProxyUtils.getMethodName(String.class));
	}
}
