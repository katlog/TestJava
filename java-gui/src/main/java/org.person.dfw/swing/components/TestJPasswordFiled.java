package org.person.dfw.swing.components;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPasswordField;

/**
 * @moudle: TestJPasswordFiled 
 * @version:v1.0
 * @author: fw
 * @date: 2017年1月24日 上午11:04:23
 *
 */
public class TestJPasswordFiled {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("fw");
		
		frame.setLayout( new GridLayout(2, 1));
		
		JPasswordField password1 = new JPasswordField(6);
		JPasswordField password2 = new JPasswordField("aaa", 3);
		password2.setEchoChar('`');
		
		frame.add(password1);
		frame.add(password2);
		
		frame.setVisible(true);
		frame.setBounds(0, 0, 300, 120);
		
	}
}
