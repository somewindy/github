package com.swf.seed.xml.dom4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.swf.seed.base.Charset;
import com.swf.seed.regex.PatternUtils;



public class SeedXmlUtils {
	/**
	 * 格式化xml字符串，以格式化后的格式输出,默认为UTF-8
	 * @param text
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static String formatPrettyXml(String text) throws DocumentException, IOException{
		return formatPrettyXml(text,Charset.UTF_8);
	}
	
	/**
	 * 格式化xml字符串，以格式化后的格式输出
	 * 第一行例如<?xml version="1.0" encoding="UTF-8"?>
	 * @param text xml内容
	 * @param charset 字符编码 使用FwsCharst获取
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static String formatPrettyXml(String text,String charset) throws DocumentException, IOException{
		Document doc = DocumentHelper.parseText(text);
		OutputFormat format = SeedOutputFormat.createPrettyPrint(4);
		if (charset!=null) {
			format.setEncoding(charset);
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLWriter writer = new XMLWriter(baos,format);
		writer.write(doc);
		return baos.toString();
	}
	
	/**
	 * 输出指定格式的xml
	 * @param text 内容
	 * @param format xml格式
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static String formatXml(String text,OutputFormat format) throws DocumentException, IOException{
		Document doc = DocumentHelper.parseText(text);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLWriter writer = new XMLWriter(baos,format);
		writer.write(doc);
		return baos.toString();
	}
	
	/**
	 * 格式化字符串，不显示xml编码
	 * @param text
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static String formatPrettyXmlWithOutEncoding(String text) throws DocumentException, IOException{
		return removeEncoding(formatPrettyXml(text));
	}
	
	/**
	 * 移除xml头部的xml编码<?xml version="1.0" encoding="UTF-8"?>
	 * @param text
	 * @return
	 */
	private static String removeEncoding(String text){
		return  PatternUtils.replaceRegex(text, "^(?:<\\?xml.*?\\?>)?[\\S\\s]*?(<[\\S\\s]*>)", "$1");
	}
}
