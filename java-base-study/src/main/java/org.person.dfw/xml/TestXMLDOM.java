package org.person.dfw.xml;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.person.dfw.refelct.util.BeanUtil;
import org.person.dfw.util.Print.Style;
import org.person.dfw.util.collections.MapUtil;
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 节点类型        named constant        nodeName()的返回值      nodeValue()的返回值
 * element     ELEMENT_NODE          element name         null
 * Attr        ATTRIBUTE_NODE        属性名称                        属性值
 * text        TEXT_NODE             #text                节点内容
 */
public class TestXMLDOM {

	@Test public void parseXml() {
		// 1、获得dom解析器工厂（工作的作用是用于创建具体的解析器）   
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			 //2、获得具体的dom解析器   
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//3、解析一个xml文档，获得Document对象（根结点）
			InputStream resourceAsStream = TestXMLDOM.class.getResourceAsStream("domRead.xml");
			//Document docu = db.parse(TestDOMXML.class.getResourceAsStream("bool.xml"));//【这种方式就出问题，不知道为什么】
			Document docu = db.parse(resourceAsStream);
//			Document docu = db.parse(url); 			//通过url解析
//			Document docu = db.parse(inputStream); 	//通过inputStream解析
			
			//4、获取所有节点
			
			//获取根元素
			Element root = docu.getDocumentElement();
			System.out.println("根元素--name："+root.getNodeName());
			System.out.println("根元素--value："+root.getNodeValue());
			Map<String, Object> map = BeanUtil.getProVal(root, null);
			MapUtil.formatPrint(Style.Vertical, null, map);
			
			NodeList booklist = docu.getElementsByTagName("book");
			for (int i = 0; i < booklist.getLength(); i++) {
				Node book_item = booklist.item(i);
				System.out.println("第" + (i + 1) + "本书");
				
				// 读取属性，返回一个NamedNodeMap
				NamedNodeMap node_att = book_item.getAttributes();
				for (int j = 0; j < node_att.getLength(); j++) {
					Node node = node_att.item(j);
					System.out.printf("\t%-10s%s\n",node.getNodeName(), node.getNodeValue());
				}
				
				//读取子节点
				NodeList book_child = book_item.getChildNodes();
				for (int k = 0; k < book_child.getLength(); k++) {
					Node book_child_ele = book_child.item(k);
					if (book_child_ele.getNodeType() == Node.ELEMENT_NODE) {// 如果没有会打印出很多空格，因为text也是一种节点类型，
						// System.out.println(book_child_ele.getNodeName()+":"+book_child_ele.getFirstChild().getNodeValue());
						// //这个就是采集到这个<name></name>中所有的所有的text
						System.out.printf("\t%-12s%s\n",book_child_ele.getNodeName(),book_child_ele.getTextContent());
					}
				}
				System.out.println("--------------------------------------------");
			}
		} catch (ParserConfigurationException|SAXException|IOException e) {
			e.printStackTrace();
		} 
	}

	@Test public void creat_xml() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.newDocument();
			// 可以将standalone设置为true，这样就不会显示了，表示的意思是不需要说明文档
			document.setXmlStandalone(true);
			
			//设置节点
			Element bookstore = document.createElement("bookstore");
			Element book = document.createElement("book");
			Element name = document.createElement("name");
			name.setTextContent("安徒生童话");
			book.setAttribute("id", "1");
			book.setAttribute("size", "lower");
			book.appendChild(name);
			bookstore.appendChild(book);
			document.appendChild(bookstore);
			
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(document), new StreamResult(new File("basic/org/person/dfw/xml/domCreate.xml")));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}
	
	@Test public void parseXml1(){
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();   
	    DocumentBuilder db;
	    Element root = null;
		try {
			db = dbf.newDocumentBuilder();
			InputStream resourceAsStream = TestXMLDOM.class.getResourceAsStream("book.xml");
			Document doc = db.parse(resourceAsStream);   
			root = doc.getDocumentElement();   
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}   
	    //获得根元素结点   
	    parseElement(root); 
	}
	
	/**递归解析xml*/
	private  void parseElement(Element element) {
		
		String tagName = element.getNodeName();//获取标签名

		NodeList children = element.getChildNodes();//获取子标签
		System.out.print("<" + tagName);

		// element元素的所有属性所构成的NamedNodeMap对象，需要对其进行判断
		NamedNodeMap map = element.getAttributes();

		// 如果该元素存在属性
		if (null != map) {
			for (int i = 0; i < map.getLength(); i++) {
				// 获得该元素的每一个属性
				Attr attr = (Attr) map.item(i);
				String attrName = attr.getName();
				String attrValue = attr.getValue();
				System.out.print(" " + attrName + "=\"" + attrValue + "\"");
			}
		}
		System.out.print(">");

		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			// 获得结点的类型
			short nodeType = node.getNodeType();

			if (nodeType == Node.ELEMENT_NODE) {
				// 是元素，继续递归
				parseElement((Element) node);
			} else if (nodeType == Node.TEXT_NODE) {
				// 递归出口
				System.out.print(node.getNodeValue());
			} else if (nodeType == Node.COMMENT_NODE) {
				// 注释内容
				System.out.print("<!--");
				Comment comment = (Comment) node;
				String data = comment.getData();
				System.out.print(data);
				System.out.print("-->");
			}
		}
		System.out.print("</" + tagName + ">");
	}
}