package org.person.dfw.swing;

import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.Test;

/**
 * @moudle: TestJframe 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月23日 上午11:09:04
 *	
 *	swing中3个顶层容器：都可以独立显示，一般作为一个图形界面的最顶层窗口
 *		(1) JFrame用来设计类似于Windows系统中的窗口形式的应用程序
 *		(2) JDialog和JFrame类似，只不过JDialog用来设计对话框
 *		(3) JApp]et用来设计可以嵌入在网页中的Java小程序
 */
@SuppressWarnings("javadoc")
public class TestJframe {
	
	//region  顶层容器添加面板Pane的两种方式 
	/**
	 * 一种方式是用getContentPane()方法获得JFrame的内容面板，再在这个内容面板中加组件，方法为frame.getContentPane()add(childComponent)
	 */
	@Test public void _addPane1() throws InterruptedException{
		JFrame jFrame = new JFrame("添加面板1");
		jFrame.setSize(200, 100);										//不设的话长宽为0
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//设置顶层容器类对象的关闭功能【不设的话点关闭后程序还是不会退出】
		jFrame.setVisible(true);										//设置后才可见
		
		Container contentPane = jFrame.getContentPane();
		contentPane.add(new JButton("点击"));
		contentPane.add(new JButton("确定"));							//后面添加的button会覆盖掉前面添加的
		
		contentPane.setSize(200, 100);									//【不设frame只设它没用】
		
		Thread.sleep(10000);
	}
	
	/**
	 *另一种方式是首先建立一个JPanel或JDesktopPane之类的中间容器，把组件添加到容器中，然后再用setContentPane方法把该容器置为JFrame的内容面板 
	 */
	public static void _addPane2() throws InterruptedException{
		JFrame jFrame = new JFrame("添加面板2");
		jFrame.setSize(200, 100);										//不设的话长宽为0
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//设置顶层容器类对象的关闭功能【不设的话点关闭后程序还是不会退出】
		jFrame.setVisible(true);										//设置后才可见
		
		JPanel jPanel = new JPanel();
		jPanel.add(new JTextField("请填写1",10));							
		jPanel.add(new JTextField("请填写2",10));						//后面添加的TextField不会覆盖掉前面添加的【想想和上面的区别】
		jFrame.setContentPane(jPanel);
		
		Thread.sleep(10000);
	}
//endregion 顶层容器添加面板Pane的两种方式 
	
	
	@Test public  void _addMenu() throws InterruptedException{
		JFrame jFrame = new JFrame("添加菜单");
		jFrame.setSize(300,200);										//不设的话长宽为0
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//设置顶层容器类对象的关闭功能【不设的话点关闭后程序还是不会退出】
		jFrame.setVisible(true);										//设置后才可见
		
		JMenuBar bar = new JMenuBar();									// 相当于菜单栏
		JMenu menu1 = new JMenu("菜单1");
		menu1.add(new JMenuItem("操作1"));
		menu1.add(new JMenuItem("操作2"));
		JMenu menu2 = new JMenu("菜单2");
		menu2.add(new JMenuItem("执行1"));
		menu2.add(new JMenuItem("执行2"));
		bar.add(menu1);
		bar.add(menu2);
		
//		jFrame.add(bar);												//这种方式bar会被当成一般组件放到中间											
		jFrame.setJMenuBar(bar);										//按照正常的菜单栏显示
		
		Thread.sleep(10000);
	}
	
	@Test public void _addImage() throws InterruptedException{
		JFrame jFrame = new JFrame("图标");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		Image image = toolkit.getImage("gui/org/person/dfw/swing/icon.gif");
		jFrame.setIconImage(image);
		
		jFrame.setVisible(true);
		jFrame.setSize(200, 100);
		Thread.sleep(10000);
	}
}
