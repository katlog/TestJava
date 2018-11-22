package org.person.dfw.swing.components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.Test;

/**
 * @moudle: TestJTextField 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月24日 下午7:58:06
 *
 */
public class TestJTextField {
	
	static final int WIDTH=300;
    static final int HEIGHT=200;
    JTextField text1;
    JTextField text2;
    String str;

	@Test public void _test1() throws InterruptedException {
    	JFrame frame=new JFrame();
    	frame.setTitle("测试窗口");
    	frame.setSize(WIDTH,HEIGHT);
    	frame.setVisible(true);
    	
    	JPanel panel = new JPanel();
    	frame.setContentPane(panel);
    	
    	text1=new JTextField(5);//这个文本组件用来输入带计算的数据
    	text2=new JTextField(5);//这个文本框用来显示计算结果
    	JLabel label1=new JLabel("平方");
    	JLabel label2=new JLabel("立方");
    	JLabel label3=new JLabel("四次方");
    	JButton button1=new JButton("平方结果");
    	JButton button2=new JButton("立方结果");
    	JButton button3=new JButton("四次方结果");
    	
    	panel.setLayout(new GridLayout(4,2));//让面板具有GridLayout布局管理器，其知识在后面会有所介绍
    	panel.add(text1);
    	panel.add(text2);
    	panel.add(label1);
    	panel.add(button1);
    	panel.add(label2);
    	panel.add(button2);
    	panel.add(label3);
		panel.add(button3);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				str = text1.getText();
				text2.setText("" + Math.pow(Double.parseDouble(str), 2));
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				str = text1.getText();
				text2.setText("" + Math.pow(Double.parseDouble(str), 3));
			}
		});
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				str = text1.getText();
				text2.setText("" + Math.pow(Double.parseDouble(str), 4));
			}
		});
    	
		
		Thread.sleep(20000);
    }
}
