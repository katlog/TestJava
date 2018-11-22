package org.person.dfw.swing.pane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;
/**
 * @moudle: TestJPane 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月28日 下午5:42:16
 *
 */
@SuppressWarnings("javadoc")
public class TestJPanel {
	
    static final int WIDTH=300;
    static final int HEIGHT=150;
	
    /**使用JPanel中间容器*/
	@Test public void test() throws InterruptedException {
		JFrame jf = new JFrame("测试程序");
		jf.setLayout(new BorderLayout());
		jf.setSize(WIDTH, HEIGHT);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		JPanel contentPane = new JPanel();// 创建六个中间容器，并且将contentPane放到顶层容器内
		jf.setContentPane(contentPane);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JButton b1 = new JButton("小赵");// 创建九个普通按钮组件，将p1到p5五个面板设置为流布局。
		JButton b2 = new JButton("小李");
		JButton b3 = new JButton("小王");
		JButton b4 = new JButton("小孙");
		JButton b5 = new JButton("小钱");
		JButton b6 = new JButton("小周");
		JButton b7 = new JButton("小政");
		JButton b8 = new JButton("小武");
		JButton b9 = new JButton("姓");
		FlowLayout fl = new FlowLayout();
		FlowLayout fl1 = new FlowLayout();
		FlowLayout fl2 = new FlowLayout();
		FlowLayout fl3 = new FlowLayout();
		FlowLayout fl4 = new FlowLayout();
		p1.setLayout(fl);
		p2.setLayout(fl1);
		p3.setLayout(fl2);
		p4.setLayout(fl3);
		p5.setLayout(fl4);

		p1.add(b1); // 将b1、b2加到p1中，将b3、b4加到p2中，将b5、b6加到p3中，将b7、b8加到p4中，将b9加到p5中
		p1.add(b2);
		p2.add(b3);
		p2.add(b4);
		p3.add(b5);
		p3.add(b6);
		p4.add(b7);
		p4.add(b8);
		p5.add(b9);
		contentPane.add(p1, "North");// 将p1到p5五个面板按照BorderLayout布局方式放置到contentPane面板中
		contentPane.add(p2, "South");
		contentPane.add(p3, "East");
		contentPane.add(p4, "West");
		contentPane.add(p5, "Center");
		
		Thread.sleep(20000);
	}
}
