
/**  
 * @Title: TestJTextArea.java
 * @Package: com.dfw.test.swing
 * @Description: TODO
 * @author: chensl
 * @date: 2017年1月24日 上午11:13:02
 * @version: V1.0  
 */ 
package org.person.dfw.swing.components;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @moudle: TestJTextArea 
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2017年1月24日 上午11:13:02
 *
 */
public class TestJTextArea {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("fw");
		
		JTextArea textArea = new JTextArea(2, 30);
		textArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.setLayout(new GridLayout(2, 1));
		frame.add(scrollPane);
		
		
		frame.setVisible(true);
		frame.setBounds(0, 0, 300, 120);
		
	}
}
