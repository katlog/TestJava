package name.katlog.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;
import org.dom4j.*;
import org.dom4j.io.*;
/**
 * @moudle: TestXMLDOM4J 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年7月24日 下午2:07:16
 *
 */
public class TestXMLDOM4J {
	
	@Test public void createXml(){
		try {
			// 创建文档并设置文档的根元素节点 ：第一种方式
			// Document document = DocumentHelper.createDocument();
			// Element root = DocumentHelper.createElement("student");
			// document.setRootElement(root);
			
			// 创建文档并设置文档的根元素节点 ：第二种方式
			Element root = DocumentHelper.createElement("student");
			Document document = DocumentHelper.createDocument(root);
			
			//设置属性及子元素
			root.addAttribute("name", "zhangsan");
			Element helloElement = root.addElement("hello");
			Element worldElement = root.addElement("world");
			helloElement.setText("hello");
			worldElement.setText("world");
			helloElement.addAttribute("age", "20");
			
			//输出
			XMLWriter xmlWriter = new XMLWriter();
			xmlWriter.write(document);
			//设置格式化 indent:缩进 newline:换行 
			OutputFormat format = new OutputFormat("  ", true);
			XMLWriter xmlWriter2 = new XMLWriter(new FileOutputStream("basic/org/person/dfw/xml/dom4jCreate.xml"), format);
			xmlWriter2.write(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test public void parseXml(){
		
		SAXReader saxReader = new SAXReader();   
	    Document doc = null;
		try {
			doc = saxReader.read(new File("basic/org/person/dfw/xml/dom4jCreate.xml"));
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}   
	    
	    //获取根元素
	    Element root = doc.getRootElement();   
	    System.out.println("root element: " + root.getName());   

	    //子元素
	    Element first = root.element("hello");   
	    System.out.println(first.attributeValue("age"));   
		for (Iterator iter = root.elementIterator(); iter.hasNext();) {
			Element e = (Element) iter.next();
			System.out.println(e.attributeValue("age"));
		}
	    System.out.println("---------------------------");   
	       
	    //dom转dom4j
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();   
	    DocumentBuilder db;
	    org.w3c.dom.Document document = null;
		try {
			db = dbf.newDocumentBuilder();
			document = db.parse(new File("basic/org/person/dfw/xml/dom4jCreate.xml"));   
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}   
	       
	    DOMReader domReader = new DOMReader();   
	    //将JAXP的Document转换为dom4j的Document   
	    Document d = domReader.read(document);   
	    Element rootElement = d.getRootElement();   
	    System.out.println(rootElement.getName());   
	}
		
}
