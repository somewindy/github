package com.swf.seed.regex; 

import java.util.*;
import java.util.regex.*;

public final class PatternUtils {
	/**
	 * 禁用默认构造器	
	 */
	private PatternUtils() {
	}
	
	
	/**
	 * 通过正则表达式替换匹配的字符串
	 * @param content  需要替换的内容
	 * @param regex 正则表达式
	 * @param replaceTxt 替换的文字
	 * @return 替换后的字符串
	 */
	public static String replaceRegex(String content,String regex,String replaceTxt){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()){
			matcher.appendReplacement(sb, replaceTxt);
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	/**
	 * 通过正则表达式替换匹配的字符串
	 * @param content  需要替换的内容
	 * @param regex 正则表达式
	 * @param replaceTxt 替换的文字
	 * @param flags 匹配规则 是否忽略大小写等
	 * @return 替换后的字符串
	 */
	public static String replaceRegex(final String content,String regex,String replaceTxt,int flags){
		Pattern pattern = Pattern.compile(regex,flags);
		Matcher matcher = pattern.matcher(content);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()){
			matcher.appendReplacement(sb, replaceTxt);
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	
	/**
	 * 判断第一个字符串是否被包含在后面的字符串中
	 * @param content 需要判断的字符串
	 * @param args 所有的字符串
	 * @return
	 */
	public static boolean beContained(String content,String ...args){
		for (String arg:args){
			if (arg.equals(content)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断字符串以其中之一开始
	 * @param content
	 * @param args
	 * @return
	 */
	public static boolean startsWithEither(String content,String ...args){
		if (content == null){
			return false;
		}
		for (String arg:args){
			if (content.startsWith(arg)){
				return true;
			}
		}
		return false;
	}
	
	/** 
	 * 判断字符串以其中之一结束
	 * @param content
	 * @param args
	 * @return
	 */
	public static boolean endsWithEither(String content,String ...args){
		if (content == null){
			return false;
		}
		for (String arg:args){
			if (content.endsWith(arg)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断字符串content是否以某正则开始
	 * @param content
	 * @param regex
	 * @return
	 */
	public static boolean startsWith(String content,String regex){
		return startsWith(content,regex,0);
	}
	/**
	 * 判断字符串content是否以某正则开始
	 * @param content 需要判断的字符串
	 * @param regex 正则
	 * @param flags 正则格式是否忽略大小写
	 * @return
	 */
	public static boolean startsWith(String content,String regex,int flags){
		Pattern pattern =(flags!=0? Pattern.compile("^"+regex+".*",flags):Pattern.compile("^"+regex+".*"));
		Matcher m = pattern.matcher(content);
		return m.matches();
	}
	
	/**
	 * 判断字符串content是否以某正则结束
	 * @param content 需要判断的字符串
	 * @param regex 正则
	 * @param flags 正则格式是否忽略大小写
	 * @return
	 */
	public static boolean endsWith(String content,String regex,int flags){
		Pattern pattern =(flags!=0?Pattern.compile(".*"+regex+"$",flags):Pattern.compile(".*"+regex+"$"));
		Matcher m = pattern.matcher(content);
		return m.matches();
	}
	
	/**
	 * 判断字符串content是否以某正则结束
	 * @param content
	 * @param regex
	 * @return
	 */
	public static boolean endsWith(String content,String regex){
		return endsWith(content,regex,0);
	}
	
	/**
	 * 判断字符串是否匹配正则
	 * @param content
	 * @param regex
	 * @param flags
	 * @return
	 */
	public static boolean matches(String content,String regex,int flags){
		Pattern pattern = (flags!=0?Pattern.compile(regex,flags): Pattern.compile(regex));
		Matcher m = pattern.matcher(content);
		return m.matches();
	}
	
	/**
	 * 判断字符串是否匹配正则
	 * @param content
	 * @param regex
	 * @return
	 */
	public static boolean matches(String content,String regex){
		return matches(content, regex, 0);
	}
	
	/**
	 * 返回匹配的字符串列表
	 * @param content
	 * @param regex
	 * @return
	 */
	public static List<String> matchedList(String content,String regex){
		return matchedList(0,content, regex);
	}
	/**
	 * 返回匹配的字符串列表
	 * @param content 需要匹配的字符串
	 * @param regex 匹配的正则
	 * @param flags 匹配规则
	 * @return 匹配的字符串列表
	 */
	public  static List<String> matchedList(int flags,String content,String regex){
		List<String> list = new ArrayList<String>();
		Pattern pattern = (flags!=0?Pattern.compile(regex,flags):Pattern.compile(regex));
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()){
			list.add(matcher.group());
		}
		return list;
	}
	/**
	 * 返回匹配的字符串列表groups
	 * @param content 需要匹配的字符串
	 * @param regex 匹配的正则
	 * @param flags 匹配规则
	 * @param groupIds 匹配规则
	 * @return 匹配的字符串列表
	 */
	public static List<List<String>> matchedList(int flags,final String content,final String regex,int[] groupIds){
		List<List<String>> list = new ArrayList<List<String>>();
		Pattern pattern = (flags!=0?Pattern.compile(regex,flags):Pattern.compile(regex));
		Matcher matcher = pattern.matcher(content);
		List<String> groupList = null;
		while (matcher.find()){
			groupList = new ArrayList<String>();
			for (int groupId:groupIds){
				if (groupId<=matcher.groupCount()){
					groupList.add(matcher.group(groupId));
				}else{
					groupList.add(null);
				}
			}
			list.add(groupList);
		}
		return list;
	}
	/**
	 * 返回匹配的字符串列表group
	 * @param content 需要匹配的字符串
	 * @param regex 匹配的正则
	 * @param flags 匹配规则
	 * @param groupIds 组
	 * @return 匹配的字符串列表
	 */
	public static List<List<String>> matchedList(String content,String regex,int[] groupIds){
		return matchedList(0,content,regex,groupIds);
	}
	
	/**
	 * 返回匹配的字符串集合，字符串唯一不重复
	 * @param content
	 * @param regex
	 * @param flags
	 * @return
	 */
	public static Set<String> matchedSet(int flags,String content,String regex){
		Set<String> set = new HashSet<String>();
		Pattern pattern = (flags!=0?Pattern.compile(regex,flags):Pattern.compile(regex));
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()){
			set.add(matcher.group());
		}
		return set;
	}
	
	/**
	 * 返回匹配的字符串集合，字符串唯一不重复
	 * @param content
	 * @param regex
	 * @param flags
	 * @return
	 */
	public final static Set<String> matchedSet(String content,String regex){
		return matchedSet(0,content, regex);
	}
	
	/**
	 * 返回匹配的字符串集合，只返回第一个
	 * @param content
	 * @param regex
	 * @param flags
	 * @return
	 */
	public final static String matchedFirstOne(String content,String regex,int flags){
		Pattern pattern = (flags!=0?Pattern.compile(regex,flags):Pattern.compile(regex));
		Matcher matcher = pattern.matcher(content);
		return matcher.find()? matcher.group():null;
	}
	/**
	 * 返回匹配的字符串集合，只返回第一个的第groupId个捕获组
	 * @param content
	 * @param regex
	 * @param flags
	 * @return
	 */
	public static String matchedFirstOne(String content,String regex,int groupId,int flags){
		Pattern pattern = (flags!=0?Pattern.compile(regex,flags):Pattern.compile(regex));
		Matcher matcher = pattern.matcher(content);
		return matcher.find()? matcher.group(groupId):null;
	}
	
	/**
	 * 返回匹配的字符串集合，只返回第一个
	 * @param content
	 * @param regex
	 * @param flags
	 * @return
	 */
	public static String matchedFirstOne(String content,String regex){
		return matchedFirstOne(content, regex, 0);
	}
	
	/**
	 * 返回匹配的字符串集合，只返回第一个的第groupId个捕获组
	 * @param content
	 * @param regex
	 * @param flags
	 * @return
	 */
	public static String matchedFirstOne(String content,int groupId,String regex){
		return matchedFirstOne(content, regex,groupId, 0);
	}
	

	/**
	 * 返回匹配的字符串集合，只返回第一个
	 * @param content
	 * @param regex
	 * @param flags
	 * @return
	 */
	public static String matchedLastOne(String content,String regex,int flags){
		Pattern pattern = (flags!=0?Pattern.compile(regex,flags):Pattern.compile(regex));
		Matcher matcher = pattern.matcher(content);
		String result=null;
		while (matcher.find()){
			result = matcher.group();
		}
		return result;
	}
	
	/**
	 * 返回匹配的字符串集合，只返回最后一个
	 * @param content
	 * @param regex
	 * @param flags
	 * @return
	 */
	public static String matchedLastOne(String content,String regex){
		return matchedLastOne(content, regex, 0);
	}
	
	/**
	 * 返回匹配的字符串集合，只返回第n个,索引从0开始
	 * @param content
	 * @param regex
	 * @param flags
	 * @return
	 */
	public static String matchedNthOne(String content,String regex,int index,int flags){
		Pattern pattern = (flags!=0?Pattern.compile(regex,flags):Pattern.compile(regex));
		Matcher matcher = pattern.matcher(content);
		String result=null;
		int i=0;
		while (matcher.find()){
			if (index==i++){
				result = matcher.group();
				break;
			}
		}
		return result;
	}
	
	/**
	 * 返回匹配的字符串集合，只返回第n个，索引从零开始
	 * @param content
	 * @param regex
	 * @param flags
	 * @return
	 */
	public static String matchedNthOne(String content,String regex,int index){
		return matchedNthOne(content, regex,index,0);
	}
	
	
	
	public static void main(String[] args) {
		String content = "abcab";
		String regex = "\\w";
		System.out.println(matchedNthOne(content, regex,2));
		System.out.println("a b  c \t  ff".split("\\s")[2]);
	}
}
