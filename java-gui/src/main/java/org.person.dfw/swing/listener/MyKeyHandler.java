/**  
 * @Title: MyKey.java
 * @Package: org.person.dfw.swing.listener
 * @Description: TODO
 * @author: chensl
 * @date: 2017年1月24日 下午3:12:26
 * @version: V1.0  
 */ 
package org.person.dfw.swing.listener;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @moudle: MyKey 
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2017年1月24日 下午3:12:26
 *
 */
public class MyKeyHandler extends JFrame implements KeyListener{
	
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(textArea);
	
	
	
	/**
	 * <p>Title: </p>
	 * <p>Description: </p>
	 * @throws HeadlessException
	 */ 
	public MyKeyHandler() throws HeadlessException {
//		Frame frame = new super();
//		FrameUtils.initFrame(new Frame());
		super.setTitle("fwe");
		scrollPane.setBounds(5, 5, 300, 200);
		textArea.addKeyListener(this);
		
		super.add(scrollPane);
		super.setVisible(true);
		super.setBounds(10, 10, 500, 300);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(1);
			}
		});
	}

	/**
	 * <p>Title: keyTyped</p>
	 * <p>author : chensl</p>
	 * <p>date : 2017年1月24日 下午3:13:10</p>
	 * @param e
	 */ 
	@Override
	public void keyTyped(KeyEvent e) {
		textArea.append(e.getKeyChar()+"\n");
	}

	/**
	 * <p>Title: keyPressed</p>
	 * <p>author : chensl</p>
	 * <p>date : 2017年1月24日 下午3:13:10</p>
	 * @param e
	 */ 
	@Override
	public void keyPressed(KeyEvent e) {
		textArea.append("按下"+KeyEvent.getKeyText(e.getKeyCode())+"\n");
	}

	/**
	 * <p>Title: keyReleased</p>
	 * <p>author : chensl</p>
	 * <p>date : 2017年1月24日 下午3:13:10</p>
	 * @param e
	 */ 
	@Override
	public void keyReleased(KeyEvent e) {
		textArea.append("松开"+KeyEvent.getKeyText(e.getKeyCode())+"\n");
	}
	public static void main(String[] args) {
		new MyKeyHandler();
	}
	
}
