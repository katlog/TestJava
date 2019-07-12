package org.person.dfw.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

/**
 * XSL转换（XSLT)机制
 * @moudle: TestXML_xlst 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年8月11日 下午7:59:27
 *
 */
public class TestXML_xlst {
	
	public static void main(String[] args) {
		TransformerFactory factory = TransformerFactory.newInstance();
		StreamSource styleSource;
		try {
			
			styleSource = new StreamSource(new FileInputStream(""));
			
			Transformer transformer = factory.newTransformer(styleSource);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");      
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			transformer.transform(null,null);
			
		} catch (FileNotFoundException | 
				TransformerException e) {
			e.printStackTrace();
		}
	}
}
