package org.person.dfw.swing.pane;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.junit.Test;

/**
 * @moudle: TestJSplitPane 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月28日 下午5:42:50
 *
 */
@SuppressWarnings("javadoc")
public class TestJSplitPane {
	
	/**创建一个分割内容面板将顶层容器分成两个部分，将两个普通按钮组件分别加到被分割的两个部分容器中*/
	@Test public void test1() throws InterruptedException {
		JButton b1 = new JButton("确定");// 创建两个普通按钮组件
		JButton b2 = new JButton("取消");
		
		JSplitPane splitPane = new JSplitPane();				// 创建一个分隔容器类
		splitPane.setOneTouchExpandable(true); 					// 让分割线显示出箭头
		splitPane.setContinuousLayout(true); 					// 当用户操作分割线箭头时，系统会重绘图形
		splitPane.setPreferredSize(new Dimension(100, 200));
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);	// 设置分割线为水平分割线
		splitPane.setLeftComponent(b1); 						// 将b1放到分割线左边，将b2放到分割线右边
		splitPane.setRightComponent(b2);
		splitPane.setDividerSize(3); 							// 设置分割线大小为3个单位
		splitPane.setDividerLocation(50); 						// 设置分割线的位置位于中间
		
		JFrame frame = new JFrame("测试窗口");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setContentPane(splitPane);
		frame.pack();
		
		Thread.sleep(20000);
	}
	/**顶层窗口分成两个部分，每一个部分添加一个带流布局的内容面板，再在内容面板中添加普通按钮组件*/
	@Test public void test2() throws InterruptedException {
		// /创建四个普通按钮组件
		JButton b1 = new JButton("确定");
		JButton b2 = new JButton("取消");
		JButton b3 = new JButton("优秀");
		JButton b4 = new JButton("良好");
		
		// /创建两个中间容器，并且被设置为流布局的布局方式
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.setLayout(new FlowLayout());
		p2.setLayout(new FlowLayout());
		
		// /将b1和b2放到p1中，将b3和b4放到p2中。
		p1.add(b1);
		p1.add(b2);
		p2.add(b3);
		p2.add(b4);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		splitPane.setContinuousLayout(true);
		splitPane.setPreferredSize(new Dimension(50, 100));
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(p1);
		splitPane.setRightComponent(p2);
		splitPane.setDividerSize(3);
		splitPane.setDividerLocation(50);
		JFrame frame = new JFrame("测试窗口");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setContentPane(splitPane);
		frame.pack();
		  
		Thread.sleep(20000);
	}
}
