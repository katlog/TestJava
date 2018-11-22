
/**  
 * @Title: CopyCode.java
 * @Package: com.dfw.test
 * @Description: TODO
 * @author: chensl
 * @date: 2017年1月24日 上午10:02:31
 * @version: V1.0  
 */ 
package org.person.dfw.swing.components;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

/**
 * @moudle: CopyCode 
 * @version:v1.0
 * @Description: TODO
 * @author: dfw
 * @date: 2017年1月24日 上午10:02:31
 *
 */
public class TestJToggleButton {
	public static void main(String[] args) {
		
		String picPath = "C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum1.jpg";
		
		JFrame jFrame = new JFrame("fw welcom");
		JToggleButton button1 = new JToggleButton("按住",true);
		JToggleButton button2 = new JToggleButton("按钮2",new ImageIcon(picPath));
		JToggleButton button3 = new JToggleButton("按钮3");
		jFrame.setLayout(new GridLayout(3, 1));
		
		jFrame.add(button1);
		jFrame.add(button2);
		jFrame.add(button3);
		
		jFrame.pack();
		jFrame.setBounds(0, 0, 100, 200);
		jFrame.setLocation(200, 200);
		jFrame.setVisible(true);
		
	}
}
