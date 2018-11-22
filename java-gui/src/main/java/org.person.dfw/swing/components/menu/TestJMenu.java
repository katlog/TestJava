package org.person.dfw.swing.components.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.junit.Test;
import org.person.dfw.swing.FrameUtils;

/**
 * @moudle: TestJMenu 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月29日 下午4:59:28
 *
 */
@SuppressWarnings({"javadoc","serial"})
public class TestJMenu {
	
	static final int WIDTH = 600;
	static final int HEIGHT = 300;
	
	JMenu menu1 = new JMenu("文件");					// 创建几个菜单
	JMenu menu2 = new JMenu("编辑");
	JMenu menu3 = new JMenu("视图");
	JMenu menu4 = new JMenu("运行");
	JMenu menu5 = new JMenu("工具");
	JMenu menu6 = new JMenu("帮助");
	
	JMenuItem item1 = new JMenuItem("打开");// 创建菜单项组件
	JMenuItem item2 = new JMenuItem("保存");
	JMenuItem item3 = new JMenuItem("打印");
	JMenuItem item4 = new JMenuItem("退出");
	JMenuItem item5 = new JMenuItem("查找");
	JMenuItem item6 = new JMenuItem("替换");
	JMenuItem item7 = new JMenuItem("剪切");
	JMenuItem item8 = new JMenuItem("拷贝");
	JMenuItem item9 = new JMenuItem("展开图");
	JMenuItem item10 = new JMenuItem("分屏图");
	JMenuItem item11 = new JMenuItem("在dos下运行");
	JMenuItem item12 = new JMenuItem("在windows下运行");
	JMenuItem item13 = new JMenuItem("看图工具");
	JMenuItem item14 = new JMenuItem("快速运行工具");
	JMenuItem item15 = new JMenuItem("版本号");
	JMenuItem item16 = new JMenuItem("帮助信息");
	
	@Test public void _test1() throws InterruptedException{
		JFrame f = new JFrame("菜单的创建测试窗口");
		JRootPane rp = new JRootPane();
		f.setContentPane(rp);
		f.setSize(HEIGHT, HEIGHT);
		f.setVisible(true);
		
		JMenuBar menubar1 = new JMenuBar(); 			// 创建一个菜单条
		rp.setJMenuBar(menubar1); 						// 将菜单条加入到根面板中
		setMenuBar(menubar1);
		
		Thread.sleep(10000);
		
	}

	
	@Test public void test2() throws InterruptedException{
		JFrame f = new JFrame("菜单的创建测试窗口");
		JRootPane rp = new JRootPane();
		f.setContentPane(rp);
		f.setSize(HEIGHT, HEIGHT);
		f.setVisible(true);
		f.pack();
		JMenuBar menubar1 = new JMenuBar();
		rp.setJMenuBar(menubar1);
		setMenuBar(menubar1);
		
		setMenuItem();
				
		Thread.sleep(20000);
	}


	
	/**处理菜单事件 */
	@Test public void test3() throws InterruptedException{
		
		JFrame f = new JFrame("测试窗口");
		JRootPane rp = new JRootPane();
		f.setContentPane(rp);
		JMenuBar menubar1 = new JMenuBar();
		rp.setJMenuBar(menubar1);
		
		setMenuBar(menubar1);
		
		setMenuItem();
		
		f.setVisible(true);
		f.setSize(WIDTH, HEIGHT);
		FrameUtils.setCenter(f);
		
		setItemExit(f);
		
		Thread.sleep(20000);
	}
	
	/**处理键盘事件 */
	@Test public void test4() throws InterruptedException{
		
		JFrame f = new JFrame("测试窗口");
		JRootPane rp=new JRootPane();
		f.setContentPane(rp);
		JMenuBar menubar1 = new JMenuBar();
		rp.setJMenuBar(menubar1);
		f.pack();
		f.setSize(WIDTH,HEIGHT);
		f.setVisible(true);
		     
		setMnemonic();
		setMenuBar(menubar1);

		initMnemonic();
		
		setMenuItem();

		FrameUtils.setCenter(f);
		
		setItemExit(f);
		
		Thread.sleep(20000);
	}
	
	/**弹出式菜单 */
	@Test public void test5() throws InterruptedException{
		
		JFrame f = new JFrame("测试窗口");
		JRootPane rp=new JRootPane();
		f.setContentPane(rp);
		JMenuBar menubar1 = new JMenuBar();
		rp.setJMenuBar(menubar1);
		f.pack();
		f.setSize(WIDTH,HEIGHT);
		f.setVisible(true);
		
		setMenuBar(menubar1);
		
		setMnemonic();
		
		initMnemonic();
		
		setMenuItem();
		
		FrameUtils.setCenter(f);
		
		setItemExit(f);
		
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		
		setPopupMenu(rp);
		
		Thread.sleep(20000);
	}
	
	@Test public void test6() throws InterruptedException{
		
		JFrame f = new JFrame("测试窗口");
		JRootPane rp = new JRootPane();
		f.setContentPane(rp);
		JMenuBar menubar1 = new JMenuBar();
		rp.setJMenuBar(menubar1);
//		f.pack();
		f.setSize(WIDTH, HEIGHT);
		
		JMenuItem item1 = new JCheckBoxMenuItem("打开");// 创建菜单项组件
		JMenuItem item2 = new JCheckBoxMenuItem("保存");
		JMenuItem item3 = new JCheckBoxMenuItem("打印");
		JMenuItem item4 = new JCheckBoxMenuItem("退出");
		
		item1.setAccelerator(KeyStroke.getKeyStroke('O', java.awt.Event.CTRL_MASK, true));
		item2.setAccelerator(KeyStroke.getKeyStroke('S', java.awt.Event.CTRL_MASK, true));
		item3.setAccelerator(KeyStroke.getKeyStroke('P', java.awt.Event.CTRL_MASK, true));
		item4.setAccelerator(KeyStroke.getKeyStroke('Q', java.awt.Event.CTRL_MASK, true));
		menu1.addMenuListener(new MenuListener() {// 在菜单鼠标监听器接口中，设置当点击打开菜单项时，保存菜单项将被禁用
			public void menuSelected(MenuEvent event) {
				item2.setEnabled(!item1.isSelected());
			}
			public void menuDeselected(MenuEvent event) {
			}
			public void menuCanceled(MenuEvent event) {
			}
		});
		setMenuBar(menubar1);
		
		setMnemonic();
		
		initMnemonic();
		
		setMenuItem();
		
		FrameUtils.setCenter(f);
		
		setItemExit(f);
		
		setPopupMenu(rp);
		
		f.setVisible(true);
		
		Thread.sleep(20000);
	}
	
	@Test public void test7() throws InterruptedException{
		
		JRadioButtonMenuItem item1;
		JFrame f = new JFrame("测试窗口");
		JRootPane rp = new JRootPane();
		f.setContentPane(rp);
		f.setContentPane(rp);
		JMenuBar menubar1 = new JMenuBar();
		rp.setJMenuBar(menubar1);
		JMenu menu1 = new JMenu("文件");
		JMenu menu2 = new JMenu("编辑");
		JMenu menu3 = new JMenu("视图");
		JMenu menu4 = new JMenu("运行");
		JMenu menu5 = new JMenu("工具");
		JMenu menu6 = new JMenu("帮助");
		menu1.setMnemonic('F'); // 设置了几个菜单的快捷键
		menu2.setMnemonic('E');
		menu3.setMnemonic('V');
		menu4.setMnemonic('R');
		menu5.setMnemonic('T');
		menu6.setMnemonic('H');
		menubar1.add(menu1);
		menubar1.add(menu2);
		menubar1.add(menu3);
		menubar1.add(menu4);
		menubar1.add(menu5);
		menubar1.add(menu6);
		item1 =new JRadioButtonMenuItem("打开"); // 设置了几个菜单项的快捷键
		JMenuItem item2 = new JMenuItem("保存", 'S');
		JMenuItem item3 = new JMenuItem("打印", 'P');
		JMenuItem item4 = new JMenuItem("退出", 'Q');
		JMenuItem item5 = new JMenuItem("查找");
		JMenuItem item6 = new JMenuItem("替换");
		JMenuItem item7 = new JMenuItem("剪切");
		JMenuItem item8 = new JMenuItem("拷贝");
		JMenuItem item9 = new JMenuItem("展开图");
		JMenuItem item10 = new JMenuItem("分屏图");
		JMenuItem item11 = new JMenuItem("在dos下运行");
		JMenuItem item12 = new JMenuItem("在windows下运行");
		JMenuItem item13 = new JMenuItem("看图工具");
		JMenuItem item14 = new JMenuItem("快速运行工具");
		JMenuItem item15 = new JMenuItem("版本信息");
		JMenuItem item16 = new JMenuItem("帮助信息");
		menu1.add(item1);
		menu1.add(item2);
		menu1.addSeparator();
		menu1.add(item3);
		menu1.addSeparator();
		menu1.add(item4);
		menu2.add(item5);
		menu2.add(item6);
		menu2.addSeparator();
		menu2.add(item7);
		menu2.add(item8);
		menu3.add(item9);
		menu3.add(item10);
		menu4.add(item11);
		menu4.add(item12);
		menu5.add(item13);
		menu5.add(item14);
		menu6.add(item15);
		menu6.add(item16);
		f.setVisible(true);
		f.setSize(WIDTH, HEIGHT);
		FrameUtils.setCenter(f);
		item4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				int i = JOptionPane.showConfirmDialog(null, "是否真的需要退出系统", "退出确认对话框", JOptionPane.YES_NO_CANCEL_OPTION);
				if (i == 0) {
					f.dispose();
				}
			}
		});
		menu1.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent event) {
				item2.setEnabled(!item1.isSelected());
			}
			public void menuDeselected(MenuEvent event) {
			}
			public void menuCanceled(MenuEvent event) {
			}
		});
	      
		Thread.sleep(20000);
	}


	private void setPopupMenu(JRootPane rp) {
		JPopupMenu pop = new JPopupMenu();		//用来设置弹出式菜单
		pop.add(item4); 						// 将一些菜单项添加到弹出菜单中
		pop.addSeparator();
		pop.add(item5);
		pop.addSeparator();
		pop.add(item8);
		pop.addSeparator();
		pop.add(item16);
		
		rp.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent event) {
				if (event.isPopupTrigger())
					pop.show(event.getComponent(), event.getX(), event.getY());
			}
		});
	}

	private void setItemExit(JFrame f) {
		item4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				// 通过对话框中按钮的选择来决定结果，在这里是当单击对话框按钮中的yes按钮时，那么，窗口会直接消失。
				int i = JOptionPane.showConfirmDialog(null, "是否真的需要退出系统", "退出确认对话框", JOptionPane.YES_NO_CANCEL_OPTION);
				if (i == 0) {
					f.dispose();
				}
			}
		});
	}
	

	private void setMenuBar(JMenuBar menubar1) {
		menubar1.add(menu1);							 // 在菜单条中添加菜单
		menubar1.add(menu2);
		menubar1.add(menu3);
		menubar1.add(menu4);
		menubar1.add(menu5);
		menubar1.add(menu6);
	}
	
	private void setMenuItem() {
		menu1.add(item1); 				// 在不同的菜单组件中添加不同菜单项
		menu1.add(item2);
		menu1.addSeparator(); 			// 添加分割
		menu1.add(item3);
		menu1.addSeparator();
		menu1.add(item4);
		menu2.add(item5);
		menu2.add(item6);
		menu2.addSeparator();
		menu2.add(item7);
		menu2.add(item8);
		menu3.add(item9);
		menu3.add(item10);
		menu4.add(item11);
		menu4.add(item12);
		menu5.add(item13);
		menu5.add(item14);
		menu6.add(item15);
		menu6.add(item16);
	}
	
	private void initMnemonic() {
		item1 = new JMenuItem("打开", 'O'); // 设置了几个菜单项的快捷键
		item2 = new JMenuItem("保存", 'S');
		item3 = new JMenuItem("打印", 'P');
		item4 = new JMenuItem("退出", 'Q'); // alt+F后再按Q来激活
	}
	
	private void setMnemonic() {
		menu1.setMnemonic('F'); // 设置了几个菜单的快捷键
		menu2.setMnemonic('E');
		menu3.setMnemonic('V');
		menu4.setMnemonic('R');
		menu5.setMnemonic('T');
		menu6.setMnemonic('H');
	}
}
