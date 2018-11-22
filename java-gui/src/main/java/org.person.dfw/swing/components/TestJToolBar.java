package org.person.dfw.swing.components;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import org.junit.Test;


/**
 * @moudle: TestJToolBar 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月30日 下午8:03:01
 *
 */
@SuppressWarnings("javadoc")
public class TestJToolBar {
	
	static final int WIDTH=600;
	static final int HEIGHT=300;
	
	@Test public void test1() throws InterruptedException{
		
		JFrame j = new JFrame("工具条测试窗口");
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pane = new JPanel();
		j.setContentPane(pane);
		
		// /创建三个普通按钮组件
		JButton button1 = new JButton("图标一");
		JButton button2 = new JButton("图标二");
		JButton button3 = new JButton("图标三");
		
		// /创建一个工具条，再将上面创建的三个普通按钮组件添加到工具条中
		JToolBar bar = new JToolBar();
		bar.add(button1);
		bar.add(button2);
		bar.add(button3);
		
		// 创建一个文本区组件
		JTextArea edit = new JTextArea(8, 40);
		JScrollPane scroller = new JScrollPane(edit);
		
		BorderLayout bord = new BorderLayout();
		pane.setLayout(bord);
		pane.add("North", bar);
		pane.add("Center", scroller);
		j.pack();
		j.setVisible(true);
		
		Thread.sleep(15000);
	}
	
	@Test public void test2() throws InterruptedException{
		
		Thread.sleep(15000);
	}
	
	@Test public void test3() throws InterruptedException{
		
		Thread.sleep(15000);
	}
}
