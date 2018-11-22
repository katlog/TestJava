package org.person.dfw.swing.pane;

import java.awt.FlowLayout;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Test;
/**
 * @moudle: TestJInternalFrame 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月31日 下午8:21:33
 *
 */
@SuppressWarnings("javadoc")
public class TestJInternalFrame {
	
	static final int WIDTH=300;
    static final int HEIGHT=150;
	
	/**创建两个JInternalFrame窗口，将这两个JInternalFrame窗口放到一个顶层容器的内容面板中*/
	@Test public void test() throws InterruptedException {
		
		JFrame jf = new JFrame("测试程序");
		jf.setSize(WIDTH, HEIGHT);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		// 创建一个中间容器，并且将之添加到顶层容器内，将之设置为流布局。
		JPanel contentPane = new JPanel();
		jf.setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout());
		
		
		// 创建一个虚拟桌面容器，将dp添加到以上创建的中间容器中
		JDesktopPane dp = new JDesktopPane();
		dp.setLayout(new FlowLayout());
		contentPane.add(dp);
		
		// 创建两个JIntenaFrame容器，并且创建六个标签组件，分别将它们添加到两个JInternaFrame容器内
		JInternalFrame jif1 = new JInternalFrame("第一个窗口", true, true, true); 
		JInternalFrame jif2 = new JInternalFrame("第二个窗口", true, true, true);
		jif1.setLayout(new FlowLayout());
		jif2.setLayout(new FlowLayout());
		
		JLabel l1 = new JLabel("这是我第一个窗口");
		JLabel l2 = new JLabel("这也是你第一个窗口");
		JLabel l3 = new JLabel("这同时是他第一个窗口");
		jif1.add(l1);
		jif1.add(l2);
		jif1.add(l3);
		JLabel l4 = new JLabel("这是我第二个窗口");
		JLabel l5 = new JLabel("这也是你第二个窗口");
		JLabel l6 = new JLabel("这同时是他第二个窗口");
		jif2.add(l4);
		jif2.add(l5);
		jif2.add(l6);
		
		dp.add(jif1);
		dp.add(jif2);
		
		jif1.setVisible(true);
		jif2.setVisible(true);
		
		Thread.sleep(20000);
	}
}
