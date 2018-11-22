package org.person.dfw.swing.layout;

//这段程序主要是为读者展示如何使用BoxLayout 布局管理器针对组件进行布局
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TestBoxLayout {
	public static void main(String[] args) {
		BoxLayoutFrame frame1 = new BoxLayoutFrame();
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.show();
	}
}

class BoxLayoutFrame extends JFrame {
	
	private static final int WIDTH = 300;
	private static final int HEIGHT = 200;

	public BoxLayoutFrame() {
		
		setTitle("测试箱式布局管理器");// 设置顶层容器名称、大小
		setSize(WIDTH, HEIGHT);
		Container con = getContentPane();// 创建一个中间容器
		
		Box hbox1 = Box.createHorizontalBox();		// 创建一个水平箱子
		JLabel label1 = new JLabel(" 姓名：");		// 创建标签组件、文本框组件
		JTextField textField1 = new JTextField(10);
		textField1.setMaximumSize(textField1.getPreferredSize());
		hbox1.add(label1); 							// 在水平箱子上添加一个标签组件
		hbox1.add(Box.createHorizontalStrut(20));	// 在水平箱子上创建一个不可见的、20个单位的组件
		hbox1.add(textField1);						// 在水平箱子上再添加一个文本框组件
		
		Box hbox2 = Box.createHorizontalBox();		// 创建一个水平箱子
		JLabel label2 = new JLabel(" 密码：");		// 创建标签组件、文本框组件
		JTextField textField2 = new JTextField(10);
		textField2.setMaximumSize(textField2.getPreferredSize());
		hbox2.add(label2); 							// 在水平箱子上添加一个标签组件
		hbox2.add(Box.createHorizontalStrut(20));	// 在水平箱子上创建一个不可见的、20个单位的组件
		hbox2.add(textField2);						// 在水平箱子上再添加一个文本框组件
		
		
		Box hbox3 = Box.createHorizontalBox();		// 创建一个水平箱子
		JButton button1 = new JButton("确定");		// 创建两个普通按钮组件
		JButton button2 = new JButton("取消");
		hbox3.add(button1);							//将两个按钮添加到箱子中
		hbox3.add(button2);
		
		Box vbox = Box.createVerticalBox();			// 创建一个垂直箱子，这个箱子将两个水平箱子添加到其中，创建一个横向 glue 组件。
		vbox.add(hbox1);
		vbox.add(hbox2);
		vbox.add(Box.createVerticalGlue());
		vbox.add(hbox3);
		con.add(vbox, BorderLayout.CENTER); 		// 将垂直箱子添加到BorderLayout布局管理器中的中间位置
	}
}
