
/**  
 * @Title: MyMouseHandler.java
 * @Package: org.person.dfw.swing.listener
 * @Description: TODO
 * @author: chensl
 * @date: 2017年1月24日 下午5:29:59
 * @version: V1.0  
 */ 
package org.person.dfw.swing.listener;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @moudle: MyMouseHandler 
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2017年1月24日 下午5:29:59
 *
 */
public class MyMouseHandler extends JFrame implements MouseListener {

	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(textArea);
	private int triggerNum = 0 ;
	
	public MyMouseHandler(){
		super.setVisible(true);
		super.setBounds(100, 100, 300, 150);
		
		super.setLayout(new GridLayout(2, 1));
		super.add(scrollPane);
		
		super.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(1);
				
			}
		});
		
		super.addMouseListener(this);
		
	}
	
	public static void main(String[] args) {
		new MyMouseHandler();
	}
	
	
	
	/**
	 * <p>Title: mouseClicked</p>
	 * <p>author : fw</p>
	 * <p>date : 2017年1月24日 下午5:30:38</p>
	 * @param e
	 */ 
	@Override
	public void mouseClicked(MouseEvent e) {
		int button = e.getButton();
		switch (button) {
		case MouseEvent.BUTTON1:
			textArea.append(triggerNum+++"左键\n");
			break;
		case MouseEvent.BUTTON2:
			textArea.append(triggerNum+++"滚轮\n");
			break;
		case MouseEvent.BUTTON3:
			textArea.append(triggerNum+++"右键\n");
			break;
		default:
			break;
		}
	}

	/**
	 * <p>Title: mousePressed</p>
	 * <p>author : chensl</p>
	 * <p>date : 2017年1月24日 下午5:30:38</p>
	 * @param e
	 */ 
	@Override
	public void mousePressed(MouseEvent e) {
		textArea.append(triggerNum+++"鼠标按下\n");
	}

	/**
	 * <p>Title: mouseReleased</p>
	 * <p>author : chensl</p>
	 * <p>date : 2017年1月24日 下午5:30:38</p>
	 * @param e
	 */ 
	@Override
	public void mouseReleased(MouseEvent e) {
		textArea.append(triggerNum+++"鼠标释放\n");
		
	}

	/**
	 * <p>Title: mouseEntered</p>
	 * <p>author : chensl</p>
	 * <p>date : 2017年1月24日 下午5:30:38</p>
	 * @param e
	 */ 
	@Override
	public void mouseEntered(MouseEvent e) {
		textArea.append(triggerNum+++"鼠标进入组件\n");
		
	}

	/**
	 * <p>Title: mouseExited</p>
	 * <p>author : chensl</p>
	 * <p>date : 2017年1月24日 下午5:30:38</p>
	 * @param e
	 */ 
	@Override
	public void mouseExited(MouseEvent e) {
		textArea.append(triggerNum+++"鼠标退出组件\n");
		
	}

}
