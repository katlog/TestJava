package org.person.dfw.swing.components;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.junit.Test;


/**
 * @moudle: TestJColorChooser 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月30日 下午8:25:36
 *
 */
@SuppressWarnings("javadoc")
public class TestJColorChooser {
	
    JFrame f = null;
    JLabel label = null;
    JTextArea textarea = null;
    JFileChooser fileChooser = null;
    
    private JButton button, rgb, red, green, blue;
    private Color color = new Color (0, 0, 0);
    
	@Test public void test() throws InterruptedException {
		
		JPanel panel = new JPanel();
		f = new JFrame("FileChooser Example");
		Container contentPane = f.getContentPane();
		contentPane.add(panel);

		
		button = new JButton("Get Color");
		rgb = new JButton("RGB: ");
		red = new JButton("Red: ");
		green = new JButton("Green: ");
		blue = new JButton("Blue: ");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(panel, "Choose Color", color);// 创建一个颜色选择器
				panel.setBackground(color);
				button.setText("Get again");
				// /通过get方法来获取颜色库中的颜色
				rgb.setText("RGB: " + color.getRGB());
				red.setText("Red: " + color.getRed());
				green.setText("Green: " + color.getGreen());
				blue.setText("Blue: " + color.getBlue());
			}
		});
		
		panel.setPreferredSize(new Dimension(550, 250));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.setBackground(color);
		panel.add(button);
		panel.add(rgb);
		panel.add(red);
		panel.add(green);
		panel.add(blue);
		
		f.pack();
		f.setVisible(true);
        
		Thread.sleep(50000);
	}
}
