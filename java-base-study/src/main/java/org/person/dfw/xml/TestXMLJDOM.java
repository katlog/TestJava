package org.person.dfw.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Test;
/**
 * @moudle: TestXMLJDOM
 * @version:v1.0
 * @author: katlog
 * @date: 2017年7月24日 下午1:46:40
 *
 */
public class TestXMLJDOM {

	@Test public void createXml( ) {

		Document document = new Document();

		//根元素
		Element root = new Element("root");
		document.addContent(root);
		//注释
		Comment comment = new Comment("This is my comments");
		root.addContent(comment);
		//1级子元素
		Element e = new Element("hello");
		e.setAttribute("sohu", "www.sohu.com");//属性
		root.addContent(e);//添加到root
		//2级子元素
		Element e2 = new Element("world");
		Attribute attr = new Attribute("test", "hehe");
		e2.setAttribute(attr);
		e.addContent(e2);//添加到e1
		//3级子元素
		e2.addContent(new Element("aaa").setAttribute("a", "b")
				.setAttribute("x", "y").setAttribute("gg", "hh")
				.setText("text content"));

		//格式化
		Format format = Format.getPrettyFormat();
		format.setIndent("   ");//设置缩进
		// format.setEncoding("gbk");//设置encoding

		//创建xml
		XMLOutputter out = new XMLOutputter(format);
		try {
			out.output(document, new FileWriter("basic/org/person/dfw/xml/jDomCreate.xml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test public void parseXml(){
		
		//创建解析器
		SAXBuilder builder = new SAXBuilder();
		Document doc;
		try {
			doc = builder.build(new File("basic/org/person/dfw/xml/jDomRead.xml"));
			
			//根元素
			Element element = doc.getRootElement();
			System.out.println(element.getName());

			//子元素
			Element hello = element.getChild("hello");
			System.out.println(hello.getText());

			//属性
			List list = hello.getAttributes();
			for (int i = 0; i < list.size(); i++) {
				Attribute attr = (Attribute) list.get(i);
				System.out.println(attr.getName() + "=" + attr.getValue());
			}

			//删除子元素
			hello.removeChild("world");

			//输出
			XMLOutputter out = new XMLOutputter(Format.getPrettyFormat().setIndent("  "));
			out.output(doc, new FileOutputStream("basic/org/person/dfw/xml/jDomReadUpdate.xml"));
		} catch (JDOMException | IOException e1) {
			e1.printStackTrace();
		}  
	}
}
