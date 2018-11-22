package org.person.dfw.swing.components;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Test;

/**
 * @moudle: TestJLabel 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月24日 下午7:37:23
 *
 */
public class TestJLabel {
	
	@Test public void _test1() throws InterruptedException{
		
        JFrame jf=new JFrame("测试程序");
        jf.setLocation(100, 100);
        jf.setSize(300,200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        
        JPanel contentPane=new JPanel( );
        jf.setContentPane(contentPane);
        
        JLabel label1=new JLabel("这是一个标签测试程序");//创建两个标签组件
        JLabel label2=new JLabel("这是一个不可编辑的标签控件");
        contentPane.add(label1); //将标签组件添加到内容面板中
        contentPane.add(label2);
        
        Thread.sleep(10000);
	}
	
	@Test public void _test2() throws InterruptedException{
		
        JFrame jf=new JFrame("测试程序");
        jf.setSize(300,200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        
        JPanel contentPane=new JPanel( );
        jf.setContentPane(contentPane);
        
        JLabel label1=new JLabel();
        JLabel label2=new JLabel();
        label1.setText("标签是用来标示某个控件的控件");
        label2.setText("标签是用来标示说明性文件的控件");
        
        contentPane.add(label1);
        contentPane.add(label2);
		
		Thread.sleep(10000);
	}
}
