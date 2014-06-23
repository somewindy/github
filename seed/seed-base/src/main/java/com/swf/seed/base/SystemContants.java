package com.swf.seed.base;

public class SystemContants {
	//行分隔符 WINDOWS \r\n  LINUX \n
	public static final String line_separator = System.getProperty("line.separator") ;
	//文件分隔符 WINDOWS \ LINUX /
	public static final String file_separator = System.getProperty("file.separator");
	//项目目录
	public static final String PROJECT_PATH =System.getProperty("user.dir") ;
}
