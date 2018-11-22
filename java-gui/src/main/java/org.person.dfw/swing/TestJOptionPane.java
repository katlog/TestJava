package org.person.dfw.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.junit.Test;

/**
 * @moudle: TestJDialog
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月30日 下午8:16:13
 *
 */
@SuppressWarnings("javadoc")
public class TestJOptionPane {

	static final int WIDTH = 700;
	static final int HEIGHT = 400;

	@Test
	public void test() throws InterruptedException {
		JFrame f = new JFrame("对话框测试窗口");
		f.setSize(WIDTH, HEIGHT);
		FrameUtils.setCenter(f);
		
		JPanel panel = new JPanel();
		
		f.setContentPane(panel);
		f.setVisible(true);
		panel.setLayout(new FlowLayout());
		JButton b1 = new JButton("showMessageDialog");// 创建四个按钮组件，这四个按钮组件引发的动作事件是显示出四个不同类型的对话框
		JButton b2 = new JButton("showConfirmDialog");
		JButton b3 = new JButton("showOptionDialog");
		JButton b4 = new JButton("showInputDialog");
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				JOptionPane.showMessageDialog(null, "这是一个消息对话框");// 这是一个showMessageDialog静态方法显示的对话框
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				JOptionPane.showConfirmDialog(null, "这是一个有关confirm的消息", "这是一个有关confirm的对话框",
						JOptionPane.YES_NO_CANCEL_OPTION); // 这是一个showConfirmDialog静态方法显示的对话框
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				Object[] options = { "OK", "CANCEL"};
				JOptionPane.showOptionDialog(null, "点击ok按钮继续", "警告", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			}// 这是一个showOptionDialog静态方法显示的对话框
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				Object[] options = { "第一", "第二", "第三"};
				JOptionPane.showInputDialog(null, "请选择一个", "输入", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
						"blue.gif"), options, options[0]);
			}// 这是一个showInputDialog静态方法显示的对话框
		});
		Thread.sleep(35000);
	}
}
