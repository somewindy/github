package com.swf.seed.test.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;

import org.omg.CORBA.portable.InvokeHandler;

public class TestFramework {
	static void executeTests(String className){
		try{
			Object obj = Class.forName(className).newInstance();
			TestParameters tp = obj.getClass().getAnnotation(TestParameters.class);
			if (tp != null){
				String methodList = tp.testMethods();
				StringTokenizer st = new StringTokenizer(methodList,",");
				while (st.hasMoreTokens()){
					String methodName = st.nextToken().trim();
					Method m = obj.getClass().getDeclaredMethod(methodName);
					System.out.println(" ");
					System.out.println(methodName);
					System.out.println("-------------");
					String result = invoke(m, obj);
					System.out.println("Result: "+result);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static String invoke(Method m,Object o){
		String result = "PASSED";
		try {
			m.invoke(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		args = new String[]{"com.swf.seed.test.annotation.StringUtility"};
		if (args.length == 0){
			System.out.println("Must specify class name (without an extension)");
		}else{
			executeTests(args[0]);
		}
	}
}
