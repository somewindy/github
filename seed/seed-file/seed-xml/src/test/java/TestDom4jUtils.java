import java.io.IOException;

import org.dom4j.DocumentException;

import com.swf.seed.xml.dom4j.Dom4jUtils;
import com.swf.seed.xml.dom4j.SeedXmlUtils;


public class TestDom4jUtils {
	public static void main(String[] args) throws DocumentException, IOException {
		System.out.println(SeedXmlUtils.formatPrettyXml(Dom4jUtils.parseTextIngoreDtd("<root><a>1</a></root>").getRootElement().asXML()));
	}
}
