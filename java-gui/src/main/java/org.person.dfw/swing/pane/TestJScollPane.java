package org.person.dfw.swing.pane;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.junit.Test;
/**
 * @moudle: TestJScollPane 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月28日 下午5:42:36
 *
 */
@SuppressWarnings("javadoc")
public class TestJScollPane {
	
    static final int WIDTH=300;
    static final int HEIGHT=150;
    
    /**用JScrollPane组件，让文本组件具有滚动功能*/
	@Test public void test1() throws InterruptedException {
		
		JFrame jf = new JFrame("测试程序");
		jf.setSize(WIDTH, HEIGHT);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		JTextArea ta = new JTextArea("我们是某某软件公司的骨干开发人员，我们会竭诚为您服务！！！");// 创建一个文本域组件和一个滚动条面板，并且将滚动条面板添加到顶层容器内
		JScrollPane sp = new JScrollPane(ta);
		jf.setContentPane(sp);
		Thread.sleep(20000);
	}
	
	/**自动水平和垂直方向都会出现滚动条*/
	@Test public void test2() throws InterruptedException {
        JFrame jf=new JFrame("测试程序");
        jf.setSize(WIDTH,HEIGHT);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        JTextArea ta=new JTextArea("我们是某某软件公司的骨干开发人员，我们会竭诚为您服务！！！");//创建一个文本域组件和一个滚动条面板，并且将滚动条面板添加到顶层容器内
        JScrollPane sp=new JScrollPane(ta,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS ,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS  );
        jf.setContentPane(sp);
	}
}
