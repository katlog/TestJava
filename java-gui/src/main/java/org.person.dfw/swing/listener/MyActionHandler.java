
/**  
 * @Title: MyActionHandler.java
 * @Package: org.person.dfw.swing.listener
 * @Description: TODO
 * @author: chensl
 * @date: 2017年1月24日 下午2:02:11
 * @version: V1.0  
 */ 
package org.person.dfw.swing.listener;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.person.dfw.swing.FrameUtils;

/**
 * @moudle: MyActionHandler 
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2017年1月24日 下午2:02:11
 *
 */
public class MyActionHandler {
	private JFrame frame;
	private JButton button = new JButton("显示");
	private JLabel label = new JLabel();
	private JTextField textField = new JTextField(10);
	private JPanel panel = new JPanel();
	
	
	public  MyActionHandler(){
		frame = new JFrame();
		FrameUtils.initFrame(frame);
		Font font = new Font("微软雅黑", Font.BOLD, 12);
		label.setFont(font);
		label.setText("等待用户输入信息");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button) {
					label.setText(textField.getText());
				}
			}
		});
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(1);
			}
		});
		/*frame.setLayout(new GridLayout(3, 1));
		frame.add(button);
		frame.add(label);
		frame.add(textField);*/
		FrameUtils.vertical1ColPan(frame, new Component[]{button,label,textField});
		
	}
	public static void main(String[] args) {
		new MyActionHandler();
	}
}
