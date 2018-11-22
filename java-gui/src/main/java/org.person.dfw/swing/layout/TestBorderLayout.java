package org.person.dfw.swing.layout;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

/**
 * @moudle: TestBorderLayout 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月24日 下午8:27:55
 *
 */
public class TestBorderLayout {
	
    static final int WIDTH=300;
    static final int HEIGHT=200;
	
	@Test public void _border1() throws InterruptedException{
		JFrame jf = new JFrame("测试程序");
		jf.setSize(WIDTH, HEIGHT);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		JPanel contentPane = new JPanel();
		jf.setContentPane(contentPane);
		
		JButton b1 = new JButton("生活");
		JButton b2 = new JButton("工作");
		JButton b3 = new JButton("睡觉");
		JButton b4 = new JButton("购物");
		JButton b5 = new JButton("饮食");
		
		BorderLayout lay = new BorderLayout();		// 创建一个布局管理器对象，将中间容器设置为此布局管理
		jf.setLayout(lay);
		contentPane.add(b1, "North");				// 将五个普通按钮组件分别按照东、南、西、北、中五个方位添加到中间容器中
		contentPane.add(b2, "South");
		contentPane.add(b3, "East");
		contentPane.add(b4, "West");
		contentPane.add(b5, "Center");
		
		Thread.sleep(10000);
	}
	
	@Test public void _border2() throws InterruptedException{
		
        JFrame jf=new JFrame("测试程序");
        jf.setSize(WIDTH,HEIGHT);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        JPanel contentPane=new JPanel();
        jf.setContentPane(contentPane);
        
		// 创建了二十五个普通按钮组件
        JButton b1=new JButton("港币");JButton b2=new JButton("人民币");JButton b3=new JButton("美元");JButton b4=new JButton("欧元"); JButton b5=new JButton("英镑");
        JButton b6=new JButton("主板");JButton b7=new JButton("内存");JButton b8=new JButton("硬盘"); JButton b9=new JButton("显示器");JButton b10=new JButton("鼠标");
        JButton b11=new JButton("大米");JButton b12=new JButton("蔬菜");JButton b13=new JButton("稻子");JButton b14=new JButton("猪肉");JButton b15=new JButton("牛肉");
        JButton b16=new JButton("面包");JButton b17=new JButton("蛋糕");JButton b18=new JButton("巧克力"); JButton b19=new JButton("奶酪");JButton b20=new JButton("苹果派");
        JButton b21=new JButton("笔记本");JButton b22=new JButton("电话");JButton b23=new JButton("办公桌");JButton b24=new JButton("钢笔");JButton b25=new JButton("文件夹");
        
		jf.setLayout(new BorderLayout());
		// 创建了五个中间容器，并且将它们的布局管理器都设置成BorderLayout方式。
        JPanel p1=new JPanel();JPanel p2=new JPanel();JPanel p3=new JPanel();JPanel p4=new JPanel();JPanel p5=new JPanel();
        p1.setLayout(new BorderLayout());p2.setLayout(new BorderLayout());p3.setLayout(new BorderLayout()); p4.setLayout(new BorderLayout());p5.setLayout(new BorderLayout());
        
		// 将五个中间容器对象分别加入到上层中间容器中，并且是按照BorderLayout的方式进行布局
        contentPane.add(p1,"North");				
        contentPane.add(p2,"South");
        contentPane.add(p3,"East");
        contentPane.add(p4,"West");
        contentPane.add(p5,"Center");
		// /将从第一个到第五个普通按钮组件按照BorderLayout方式布局到p1中间容器中
		p1.add(b1, "North");p1.add(b2, "West");	p1.add(b3, "South");p1.add(b4, "East");p1.add(b5, "Center");
		// 将从第六个到第十个普通按钮组件按照BorderLayout方式布局到p2中间容器中
		p2.add(b6, "North");p2.add(b7, "West");p2.add(b8, "South");p2.add(b9, "East");p2.add(b10, "Center");
		// 将从第十一个到第十五个普通按钮组件按照BorderLayout方式布局到p3中间容器中
		p3.add(b11, "North");p3.add(b12, "West");p3.add(b13, "South");p3.add(b14, "East");p3.add(b15, "Center");
		// 将从第十六个到第二十个普通按钮组件按照BorderLayout方式布局到p4中间容器中
		p4.add(b16, "North");p4.add(b17, "West");p4.add(b18, "South");p4.add(b19, "East");p4.add(b20, "Center");
		// 将从第二十一个到第二十五个普通按钮组件按照BorderLayout方式布局到p5中间容器中
		p5.add(b21, "North");p5.add(b22, "West");p5.add(b23, "South");p5.add(b24, "East");p5.add(b25,"Center");
		
		Thread.sleep(10000);
	}
	
	
}
