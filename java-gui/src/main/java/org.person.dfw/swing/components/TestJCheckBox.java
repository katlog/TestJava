package org.person.dfw.swing.components;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.junit.Test;

/**
 * @moudle: TestJCheckBox 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月24日 下午7:53:39
 *
 */
public class TestJCheckBox {
	
	static final int WIDTH=300;
    static final int HEIGHT=200;
	
	@Test public void _test1() throws InterruptedException{
		
        JFrame jf=new JFrame("测试程序");
        jf.setSize(WIDTH,HEIGHT);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        
        JPanel contentPane=new JPanel( );
        jf.setContentPane(contentPane);
        
        JCheckBox jc1=new JCheckBox("羽毛球");//创建六个复选按钮，并且将之添加到内容面板中
        JCheckBox jc2=new JCheckBox("足球");
        JCheckBox jc3=new JCheckBox("电脑书");
        JCheckBox jc4=new JCheckBox("数学书");
        JCheckBox jc5=new JCheckBox("电影");
        JCheckBox jc6=new JCheckBox("录像");
        
        contentPane.add(jc1);
        contentPane.add(jc2);
        contentPane.add(jc3);
        contentPane.add(jc4);
        contentPane.add(jc5);
        contentPane.add(jc6);
		
		Thread.sleep(10000);
	}
	
	@Test public void _test2() throws InterruptedException{
		
    	JFrame frame=new JFrame();
    	frame.setTitle("如何使用按钮的测试程序");
    	frame.setSize(WIDTH,HEIGHT);
    	
        JPanel contentPane=new JPanel( );
        frame.setContentPane(contentPane);
    	frame.setContentPane(contentPane);
    	
    	
    	
    	JLabel name=new JLabel("王鹏");
    	JRadioButton jr1=new JRadioButton("男");
        JRadioButton jr2=new JRadioButton("女");
        ButtonGroup  bg=new ButtonGroup();
        
        bg.add(jr1); //将两个单选按钮划分到同一个单选按钮组bg中
        bg.add(jr2);
        JLabel interesting=new JLabel("兴趣爱好");
        JCheckBox jc1=new JCheckBox("羽毛球");
        JCheckBox jc2=new JCheckBox("足球");
        JCheckBox jc3=new JCheckBox("电脑书");
        JCheckBox jc4=new JCheckBox("数学书");
        JCheckBox jc5=new JCheckBox("电影");
        JCheckBox jc6=new JCheckBox("录像");
        
		contentPane.add(name);
		contentPane.add(jr1);
		contentPane.add(jr2);
		contentPane.add(interesting);
		contentPane.add(jc1);
		contentPane.add(jc2);
		contentPane.add(jc3);
		contentPane.add(jc4);
		contentPane.add(jc5);
		contentPane.add(jc6);
		frame.setVisible(true);
		
		Thread.sleep(10000);
	}
}
