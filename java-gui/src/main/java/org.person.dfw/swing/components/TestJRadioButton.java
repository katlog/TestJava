package org.person.dfw.swing.components;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.junit.Test;
import org.person.dfw.swing.FrameUtils;

/**
 * @moudle: TestJRadioButton 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月24日 下午1:30:27
 *
 */
public class TestJRadioButton {
	
	
	@Test public void _test1() throws InterruptedException {
		
		JFrame frame = new JFrame("fw");
		JPanel panel = new JPanel();
		
		JRadioButton button1 = new JRadioButton("one");
		JRadioButton button2 = new JRadioButton("two");
		JRadioButton button3 = new JRadioButton("three");
		
		ItemListener itemListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// 为何每次点击都触发两次
				if (e.getSource() == button1) {
					System.out.println("button1");
				} else if (e.getSource() == button2) {
					System.out.println("button2");
				} else if (e.getSource() == button3) {
					System.out.println("button3");
				} else {
					System.out.println("button4");
				}
			}
		};
		FrameUtils.herizontal1RowPan(frame, button1, button2, button3);
		FrameUtils.setGroup(button1, button2, button3);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		button1.addItemListener(itemListener);
		button2.addItemListener(itemListener);
		button3.addItemListener(itemListener);
		
		
		Thread.sleep(20000);
	}
	
	static final int WIDTH=300;
    static final int HEIGHT=200;
    
    @Test public void _test2() throws InterruptedException{
        JFrame jf=new JFrame("测试程序");
        jf.setSize(WIDTH,HEIGHT);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        
        JPanel contentPane=new JPanel( );
        jf.setContentPane(contentPane);
        
        JRadioButton jr1=new JRadioButton("忽略");//创建三个单选按钮
        JRadioButton jr2=new JRadioButton("继续");
        JRadioButton jr3=new JRadioButton("跳过");
        ButtonGroup  bg=new ButtonGroup();//将三个单选按钮划分到一个按钮组中
        bg.add(jr1);
        bg.add(jr2);
        bg.add(jr3);
        contentPane.add(jr1); //将三个单选按钮添加到内容面板中
        contentPane.add(jr2);
        contentPane.add(jr3);
        
        Thread.sleep(20000);
    }
}
