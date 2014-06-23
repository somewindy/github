package com.swf.seed.xml.dom4j;

import org.dom4j.io.*;

public class SeedOutputFormat extends OutputFormat{
	
	/**
	 * 格式化xml
	 * @param tabCount每行前空格数
	 * @return
	 */
	public static OutputFormat createPrettyPrint(int tabCount) {
        OutputFormat format = new OutputFormat();
        format.setIndentSize(tabCount);
        format.setNewlines(true);
        format.setTrimText(true);
        format.setPadText(true);

        return format;
    }
}
