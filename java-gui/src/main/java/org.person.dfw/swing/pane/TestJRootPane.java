package org.person.dfw.swing.pane;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRootPane;

import org.junit.Test;
/**
 * @moudle: TestJRootPane 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月28日 下午5:44:18
 *
 */
@SuppressWarnings("javadoc")
public class TestJRootPane {
	
	static final int WIDTH=600;
    static final int HEIGHT=300;
	@Test public void test() throws InterruptedException {
		
		JFrame jFrame = new JFrame("测试窗口");// 设置顶层容器的标题，并且将rootPane设置为其依附在顶层容器上的面板
		
		JRootPane rp = new JRootPane();
		jFrame.setContentPane(rp);
		
		JMenuBar menubar1 = new JMenuBar();// 创建一个菜单，并且将菜单添加到rootPane 中。
		rp.setJMenuBar(menubar1);
		
		// 创建菜单项
		JMenu menu1 = new JMenu("文件");
		JMenu menu2 = new JMenu("编辑"); 
		JMenu menu3 = new JMenu("视图");
		JMenu menu4 = new JMenu("帮助");
		menubar1.add(menu1);
		JMenuItem item1 = new JMenuItem("打开");
		JMenuItem item2 = new JMenuItem("保存");
		JMenuItem item3 = new JMenuItem("打印");
		JMenuItem item4 = new JMenuItem("退出");
		menu1.add(item1);
		menu1.add(item2);
		menu1.addSeparator();
		menu1.add(item3);
		menu1.addSeparator();
		menu1.add(item4);
		menubar1.add(menu2);
		menubar1.add(menu3);
		
		
		jFrame.setSize(WIDTH, HEIGHT);
		jFrame.setVisible(true);
		
		Thread.sleep(20000);
	}
}
