package org.person.dfw.swing.layout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.junit.Test;

/**
 * @moudle: TestBorderLayout 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月24日 下午8:27:55
 *
 */
public class TestSpringLayout {
	
    static final int WIDTH=300;
    static final int HEIGHT=200;
	
	@Test public void _border1() throws InterruptedException{
		JFrame jf = new JFrame("测试程序");
		jf.setSize(WIDTH, HEIGHT);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		JPanel contentPane = new JPanel();
		jf.setContentPane(contentPane);
		
		JButton button1 = new JButton("测试程序模块1");// 创建了两个普通按钮组件、一个标签组件，将它们添加到中间容器中
		JButton button2 = new JButton("测试程序模块2");
		JLabel label = new JLabel("测试程序");
		contentPane.add(label);
		contentPane.add(button2);
		contentPane.add(button1);
		
		// 创建一个 SpringLayout布局管理器，并且将之作为中间容器的布局方式
		SpringLayout lay = new SpringLayout();
		contentPane.setLayout(lay);
		
		// 针对每个组件设置其与边界的距离
		lay.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, contentPane);			// label的北 到 pane的北  5piex
		lay.putConstraint(SpringLayout.WEST, label, 85, SpringLayout.WEST, contentPane);			// label的西 到 pane的西  85piex
		lay.putConstraint(SpringLayout.EAST, label, 85, SpringLayout.EAST, contentPane);			// label的东 到 pane的东  85piex
		
		lay.putConstraint(SpringLayout.NORTH, button1, 55, SpringLayout.NORTH, contentPane);		// button1的北 到 pane的北  55piex
		lay.putConstraint(SpringLayout.WEST, button1, 5, SpringLayout.WEST, contentPane);			// button1的西 到 pane的西  5piex
		lay.putConstraint(SpringLayout.EAST, button1, 25, SpringLayout.EAST, contentPane);			// button1的东 到 pane的东  25piex
		
		lay.putConstraint(SpringLayout.NORTH, button2, 105, SpringLayout.NORTH, contentPane);		// button2的北 到 pane的北  105piex
        lay.putConstraint(SpringLayout.WEST,button2, 5,SpringLayout.WEST,contentPane);				// button2的西 到 pane的西  5piex
        lay.putConstraint(SpringLayout.EAST,button2, 25,SpringLayout.EAST,contentPane);				// button2的东 到 pane的东  25piex
		
		Thread.sleep(10000);
	}
	
	
}
