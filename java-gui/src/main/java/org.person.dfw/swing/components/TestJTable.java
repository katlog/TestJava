package org.person.dfw.swing.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.junit.Test;


/**
 * @moudle: TestJTable 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月28日 下午9:30:43
 *
 */
public class TestJTable {
	
	Object[][] playerInfo={											//创建表格中的数据
			{"王鹏",91,100,191,true},
			{"朱雪莲",82,69,151,true},
			{"梅庭",47,57,104,false},
			{"赵龙",61,57,118,false},
			{"李兵",90,87,177,true},
	};
	String[] Names={"姓名","语文","数学","总分","及格"};					//创建表格中的横标题
	
	@Test public void _constructor1() throws InterruptedException{
		 JFrame f=new JFrame();
		 JTable table=new JTable(playerInfo,Names);							//创建一个表格，表格可编辑
		 table.setPreferredScrollableViewportSize(new Dimension(550,30));	//设置此表视口的首选大小
		 JScrollPane scrollPane=new JScrollPane(table);						//将表格加入到滚动条组件中
		 f.getContentPane().add(scrollPane,BorderLayout.CENTER);			//再将滚动条组件添加到中间容器中
		 f.setTitle("表格测试窗口");
		 f.pack();
		 f.setVisible(true);
		 f.addWindowListener(new WindowAdapter() {
			 public void windowClosing(WindowEvent e) {
				 System.exit(0);
			 }
		 });
		
		Thread.sleep(20000);
	}
	
	@Test public void _constructor2() throws InterruptedException{
		
		 JFrame f=new JFrame();
		 JTable table=new JTable(10,10);									//创建一个十行和十列的空表格
		 table.setPreferredScrollableViewportSize(new Dimension(550,30));
		 JScrollPane scrollPane=new JScrollPane(table);
		 f.getContentPane().add(scrollPane,BorderLayout.CENTER);
		 f.setTitle("表格测试窗口");
		 f.pack();
		 f.setVisible(true);
		 f.addWindowListener(new WindowAdapter() {
			 public void windowClosing(WindowEvent e) {
				 System.exit(0);
			 }
		 });
		
		Thread.sleep(20000);
	}
	
	/**将表格加入容器*/
	@Test public void test3() throws InterruptedException{
		
		 JFrame f=new JFrame();
		 JTable table=new JTable(playerInfo,Names);								//表格的横标题不见了
		 table.setPreferredScrollableViewportSize(new Dimension(550,30));
		 f.getContentPane().add(table,BorderLayout.CENTER);						//使用的将表格直接添加到中间容器中
		 f.setTitle("表格测试窗口");
		 f.pack();
		 f.setVisible(true);
		 f.addWindowListener(new WindowAdapter() {
			 public void windowClosing(WindowEvent e) {
				 System.exit(0);
			 }
		 });
		
		Thread.sleep(20000);
	}
	
	/**将表格加入容器*/
	@Test public void test4() throws InterruptedException{
		
		JFrame f = new JFrame();
		JTable table = new JTable(playerInfo, Names);
		table.setPreferredScrollableViewportSize(new Dimension(550, 30));
		f.getContentPane().add(table, BorderLayout.CENTER);
		f.getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);			// 添加表格的横标题
		f.setTitle("表格测试窗口");
		f.pack();
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		Thread.sleep(20000);
	}
	
	static final int WIDTH=600;
	static final int HEIGHT=300;
	static JPanel p;
	@Test public void test5() throws InterruptedException{
		
		JFrame f;
		JMenuItem item1 = new JMenuItem();
		JTable table = new JTable(playerInfo, Names);
		table.setPreferredScrollableViewportSize(new Dimension(850, 300));
		item1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent Event) {
				table.setPreferredScrollableViewportSize(new Dimension(850, 300));
				p.add(table.getTableHeader(), BorderLayout.NORTH);
				p.add(table, BorderLayout.CENTER);
			}
		});
		
		Thread.sleep(20000);
	}
}
