
/**  
 * @Title: TestWindowListener.java
 * @Package: org.person.dfw.swing.listener
 * @Description: TODO
 * @author: chensl
 * @date: 2017年1月24日 下午12:33:02
 * @version: V1.0  
 */ 
package org.person.dfw.swing.listener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.Test;

/**
 * @moudle: TestWindowListener 
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2017年1月24日 下午12:33:02
 *
 */
public class TestWindowListener {
	public static void main(String[] args) {
		JFrame frame = new JFrame("fw");
		
		JLabel label = new JLabel("窗口");
		
		frame.add(label);
		
		frame.setVisible(true);
		frame.setBounds(0, 0, 300, 120);
		frame.addWindowListener(new MyWindowListener());
		
	}
	
	@Test
	public void testWindowAdpter(){
		JFrame frame = new JFrame("fw");
		
		JLabel label = new JLabel("窗口");
		
		frame.add(label);
		
		frame.setVisible(true);
		frame.setBounds(0, 0, 300, 120);
		frame.addWindowListener(new MyWindowAdpater());
	}
}
