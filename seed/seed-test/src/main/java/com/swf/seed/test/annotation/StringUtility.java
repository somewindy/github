package com.swf.seed.test.annotation;

@TestParameters(testMethods = "testConcat,testSubstring", testOutputType = "screen", testStage = "Unit",testOutput="")
public class StringUtility {
	public String concat(String s1,String s2){
		return s1+s2;
	}
	
	public String substring(String str,int beginIndex,int endIndex){
		return str.substring(beginIndex, endIndex);
	}
	
	
	public boolean testConcat(){
		String s1 = "test";
		String s2 = "123";
		return concat(s1, s2).equals("test123");
	}
	
	public boolean testSubstring(){
		String str = "The cat landed on its feet";
		return substring(str, 4, 7).equals("cat");
	}
}
