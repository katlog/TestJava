package name.katlog.xml;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TestXMLSAX {

	@Test public void sax1() {
		// step1： 获得SAX解析器工厂实例
		SAXParserFactory factory = SAXParserFactory.newInstance();

		// step2: 获得SAX解析器实例
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			// step3: 开始进行解析
			parser.parse(new File(TestXMLSAX.class.getResource("book.xml").getFile()), new MyHandler());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	@Test public void sax2() {
		// step1： 获得SAX解析器工厂实例
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		// step2: 获得SAX解析器实例
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			// step3: 开始进行解析
			parser.parse(new File(TestXMLSAX.class.getResource("book.xml").getFile()), new MyHandler2());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}

class MyHandler extends DefaultHandler {
	@Override
	public void startDocument() throws SAXException {
		System.out.println("parse began");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("parse finished");
	}

	@Override//开始xml文件中节点的开始的标签
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("start element");
	}

	@Override//xml文件节点的结束标签 
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("finish element");
	}
	
	
}
class MyHandler2 extends DefaultHandler {
	private Stack<String> stack = new Stack<String>();
	
	private String name;
	
	private String gender;
	
	private String age;
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		stack.push(qName);
		
		for (int i = 0; i < attributes.getLength(); i++) {
			String attrName = attributes.getQName(i);
			String attrValue = attributes.getValue(i);
			
			System.out.println(attrName + "=" + attrValue);
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String tag = stack.peek();
		
		if ("姓名".equals(tag)) {
			name = new String(ch, start, length);
		} else if ("性别".equals(tag)) {
			gender = new String(ch, start, length);
		} else if ("年龄".equals(tag)) {
			age = new String(ch, start, length);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		stack.pop(); // 表示该元素已经解析完毕，需要从栈中弹出
		
		if ("学生".equals(qName)) {
			System.out.println("姓名：" + name);
			System.out.println("性别：" + gender);
			System.out.println("年龄：" + age);
			
			System.out.println();
		}
		
	}
}