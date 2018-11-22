package org.person.dfw.swing.pane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import org.junit.Test;

/**
 * @moudle: TestJTablePane 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月28日 下午5:43:03
 *
 */
@SuppressWarnings("javadoc")
public class TestJTabbedPane {
	
	/**创建一个JTabbedPane面板，为每一个标签设置一个名称*/
	@Test public void test1() throws InterruptedException {
		JFrame frame = new JFrame("资金状况");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);// 默认为false
		
		JTabbedPane tp = new JTabbedPane();// 创建一个选项卡容器，将之添加到顶层容器内
		frame.setContentPane(tp);
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		
		// 添加选项卡容器，并且设置其中每个选项卡的标签以及其是否可启用
		tp.addTab("panel1", panel1); 
		tp.setEnabledAt(0, false);					//设置是否启用index位置的选项卡【0个是必须启用的】
		tp.setTitleAt(0, "个人收入状况");			// 将index位置的标题设置为title，它可以为null
		
		tp.addTab("panel2", panel2);
		tp.setEnabledAt(1, false);					//设置是否启用index位置的选项卡【不启用时变灰色】
		tp.setTitleAt(1, "工资");
		
		tp.addTab("panel3", panel3);
		tp.setEnabledAt(2, true);				
		tp.setTitleAt(2, "奖金");
		//【标签颜色】
		tp.setBackgroundAt(2, Color.blue);			//将index位置的背景色设置为background.它可以为null。在这种情况下选項卡的背景色默认为TabbedPane的背景色
		
		tp.addTab("panel4", panel4);
		tp.setEnabledAt(0, true);
		tp.setTitleAt(3, "津贴");
		//【标签文字颜色】
		tp.setForegroundAt(3, Color.cyan);			//将index位置的前景色设置为foreground，它可以为null,在这种情况下选项卡的前景色默认为此TabbedPane的前景色
		
		tp.addTab("panel5", panel5);
		tp.setEnabledAt(4, false);
		tp.setTitleAt(4, null);
		
		// /设置其大小以及其选项卡的位置方向
		tp.setPreferredSize(new Dimension(500, 200));
		tp.setTabPlacement(JTabbedPane.TOP);
		// /设置选项卡在容器内的显示形式
		//设置在一次运行中不能放入所有的选项卡时，选項卡窗格进行布局安排的策略
		//箭头的形式
		tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);				
		
		frame.pack();
		
		Thread.sleep(20000);
	}
	
	
	@Test public void test2() throws InterruptedException {
		try {// 显示外观风格
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		JFrame frame = new JFrame("资金状况");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);// 默认为false
		
		JTabbedPane tp = new JTabbedPane();// 创建一个选项卡容器，将之添加到顶层容器内
		frame.setContentPane(tp);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		tp.addTab("panel1", panel1); // 添加选项卡容器，并且设置其中每个选项卡的标签以及其是否可启用
		tp.setEnabledAt(0, true);
		tp.setTitleAt(0, "个人收入状况");
		tp.addTab("panel2", panel2);
		tp.setEnabledAt(1, true);
		tp.setTitleAt(1, "工资");
		tp.addTab("panel3", panel3);
		tp.setEnabledAt(2, true);
		tp.setTitleAt(2, "奖金");
		tp.addTab("panel4", panel4);
		tp.setEnabledAt(0, true);
		tp.setTitleAt(3, "津贴");
		tp.addTab("panel5", panel5);
		tp.setEnabledAt(4, true);
		tp.setTitleAt(4, "社保");
		// /设置其大小以及其选项卡的位置方向
		tp.setPreferredSize(new Dimension(500, 200));
		tp.setTabPlacement(JTabbedPane.TOP);
		// /设置选项卡在容器内的显示形式
		//设置在一次运行中不能放入所有的选项卡时，选項卡窗格进行布局安排的策略。
		//可能的值为JTabbedPane.WRAP_TAB_LAYOUT、JTabbedPane.SCROLL_TAB_LAYOUT(默认)
		tp.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);						
		frame.pack();
		
		// 创建八个标签组件，将五个中间容器设置为流布局，并且将标签组件分别放入到其中
		JLabel l1 = new JLabel("工资状况");
		JLabel l2 = new JLabel("3000元/月");
		JLabel l3 = new JLabel("奖金状况");
		JLabel l4 = new JLabel("1500元/月");
		JLabel l5 = new JLabel("津贴状况");
		JLabel l6 = new JLabel("500元/月");
		JLabel l7 = new JLabel("社保状况");
		JLabel l8 = new JLabel("200元/月");
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new FlowLayout());
		panel5.setLayout(new FlowLayout());
		panel2.add(l1);
		panel2.add(l2);
		panel3.add(l3);
		panel3.add(l4);
		panel4.add(l5);
		panel4.add(l6);
		panel5.add(l7);
		panel5.add(l8);
		frame.pack();
		
		Thread.sleep(20000);
	}
}
