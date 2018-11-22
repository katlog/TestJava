
/**  
 * @Title: MyWindowListener.java
 * @Package: org.person.dfw.swing.listener
 * @Description: TODO
 * @author: chensl
 * @date: 2017年1月24日 下午12:33:39
 * @version: V1.0  
 */ 
package org.person.dfw.swing.listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @moudle: MyWindowListener 
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2017年1月24日 下午12:33:39
 *
 */
public class MyWindowListener implements WindowListener{

	/**
	 * <p>Title: windowOpened</p>
	 * <p>author : chensl</p>
	 * <p>date : 2017年1月24日 下午12:34:35</p>
	 * @param e
	 */ 
	public void windowOpened(WindowEvent e) {
		System.out.println("open window");
	}

	/**
	 * <p>Title: windowClosing</p>
	 * <p>author : chensl</p>
	 * <p>date : 2017年1月24日 下午12:34:35</p>
	 * @param e
	 */ 
	public void windowClosing(WindowEvent e) {
		System.out.println("closing window");
	}

	/**
	 * <p>Title: windowClosed</p>
	 * <p>author : chensl</p>
	 * <p>date : 2017年1月24日 下午12:34:35</p>
	 * @param e
	 */ 
	public void windowClosed(WindowEvent e) {
		System.out.println("closed window");
	}

	/**
	 * <p>Title: windowIconified</p>
	 * <p>author : chensl</p>
	 * <p>date : 2017年1月24日 下午12:34:35</p>
	 * @param e
	 */ 
	public void windowIconified(WindowEvent e) {
		System.out.println("窗口最小化");
	}

	/**
	 * <p>Title: windowDeiconified</p>
	 * <p>author : chensl</p>
	 * <p>date : 2017年1月24日 下午12:34:35</p>
	 * @param e
	 */ 
	public void windowDeiconified(WindowEvent e) {
		System.out.println("窗口从最小化恢复");
	}

	/**
	 * <p>Title: windowActivated</p>
	 * <p>author : chensl</p>
	 * <p>date : 2017年1月24日 下午12:34:35</p>
	 * @param e
	 */ 
	public void windowActivated(WindowEvent e) {
		System.out.println("窗口被选中");
	}

	/**
	 * <p>Title: windowDeactivated</p>
	 * <p>author : chensl</p>
	 * <p>date : 2017年1月24日 下午12:34:35</p>
	 * @param e
	 */ 
	public void windowDeactivated(WindowEvent e) {
		System.out.println("取消窗口选中");
	}

}
