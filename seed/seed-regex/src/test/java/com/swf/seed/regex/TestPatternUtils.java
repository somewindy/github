package com.swf.seed.regex;

import java.util.List;
import java.util.regex.Pattern;

public class TestPatternUtils {
	public static void main(String[] args) {
		/*List<String> list = null;
		long s = System.currentTimeMillis();
		for (int i=0;i<10000000;i++){
		 list= PatternUtils.matchedList(Pattern.CASE_INSENSITIVE, "a BB cc DD \n A tt vv SS", "(a+).(\\w+).(\\w+).(\\w+)");
		}
		long e = System.currentTimeMillis();
		System.out.println(e-s);*/
		String content = "AAA";
		System.out.println(PatternUtils.replaceRegex(content, "aa", "cc",Pattern.CASE_INSENSITIVE));
		
		System.out.println(content);
		//11511 11487
	}
}
