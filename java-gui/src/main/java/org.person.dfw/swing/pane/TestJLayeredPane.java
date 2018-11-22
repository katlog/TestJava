package org.person.dfw.swing.pane;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import org.junit.Test;
/**
 * @moudle: TestJLayeredPane 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月28日 下午5:44:06
 *
 */
@SuppressWarnings("javadoc")
public class TestJLayeredPane {
	
	static final int WIDTH=300;
    static final int HEIGHT=150;
    
	
	@Test public void test1() throws InterruptedException {
		// /设置顶层容器的标题
		JFrame jFrame = new JFrame("测试窗口");
		// /将新建的JLayeredPane放到顶层容器内
		JLayeredPane lp=new JLayeredPane();
		jFrame.setContentPane(lp);
		
		JButton b1=new JButton("确定");
		JButton b2=new JButton("取消");
		ActionListener listener = new ActionListener() {
			@Override	public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("确定")) { 	// 判断是哪个按钮的动作
					lp.setLayer(b1, 300); 					// 重新设置组件层数
					lp.setLayer(b2, 200);
				} else if (e.getActionCommand().equals("取消")) {
					lp.setLayer(b1, 200);
					lp.setLayer(b2, 300);
				}
			}
		};
		b1.addActionListener(listener); // 按钮事件
		b2.addActionListener(listener);
		b1.setBounds(new Rectangle(100, 100, 100, 100)); // Button出现位置
		b1.setVisible(true); // 显示
		b2.setBounds(new Rectangle(50, 50, 100, 100));
		b2.setVisible(true);
		
		lp.add(b1, new Integer(200)); // 将组件添加到JLayeredPane中，指定所在的层
		lp.add(b2, new Integer(300));
		
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(360, 260);
		jFrame.setVisible(true);
		
		Thread.sleep(20000);
	}
}
