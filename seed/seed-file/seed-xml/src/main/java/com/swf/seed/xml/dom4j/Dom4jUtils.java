package com.swf.seed.xml.dom4j;

import java.io.*;
import java.util.*;

import org.dom4j.*;
import org.dom4j.io.*;
import org.xml.sax.*;

public final class Dom4jUtils {
	/**
	 * 禁用默认构造器
	 */
	private Dom4jUtils() {
	}

	/**
	 * 防止dtd产生的问题，不校验dtd
	 * @param text
	 * @return
	 * @throws DocumentException
	 */
	public static Document parseTextIngoreDtd(String text)
			throws DocumentException {
		Document result = null;
		SAXReader reader = new SAXReader();

		reader.setEntityResolver(new NoDtdValidationResolver());
		String encoding = getEncoding(text);

		InputSource source = new InputSource(new StringReader(text));
		source.setEncoding(encoding);

		result = reader.read(source);

		// if the XML parser doesn't provide a way to retrieve the encoding,
		// specify it manually
		if (result.getXMLEncoding() == null) {
			result.setXMLEncoding(encoding);
		}

		return result;
	}

	private static String getEncoding(String text) {
		String result = null;

		String xml = text.trim();

		if (xml.startsWith("<?xml")) {
			int end = xml.indexOf("?>");
			String sub = xml.substring(0, end);
			StringTokenizer tokens = new StringTokenizer(sub, " =\"\'");

			while (tokens.hasMoreTokens()) {
				String token = tokens.nextToken();

				if ("encoding".equals(token)) {
					if (tokens.hasMoreTokens()) {
						result = tokens.nextToken();
					}

					break;
				}
			}
		}

		return result;
	}
}

class NoDtdValidationResolver implements EntityResolver {

	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException {
		return new InputSource(new ByteArrayInputStream("".getBytes()));
	}
}